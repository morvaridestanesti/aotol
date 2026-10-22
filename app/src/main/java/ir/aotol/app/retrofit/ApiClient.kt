package ir.aotol.app.retrofit

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import ir.aotol.app.BuildConfig
import ir.aotol.app.App
import ir.aotol.app.NavigationManager
import ir.aotol.app.enums.SpKeys
import ir.aotol.app.helpers.SpHelper
import ir.aotol.app.retrofit.interfaces.AuthInterface
import ir.aotol.app.ui.managers.LoadingManager
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import okhttp3.Authenticator
import okhttp3.ConnectionPool
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Route
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException
import java.net.SocketException
import java.util.concurrent.TimeUnit

object ApiClient {
    private val BASE_URL: String = "${App.SITE_URL}/api"

    private const val TIMEOUT_SECONDS = 120L
    const val MAX_RETRIES = 3

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
        isLenient = true
        encodeDefaults = true
    }

    private val connectionPool = ConnectionPool(5, 5, TimeUnit.MINUTES)

    // Main client with token refresh capability
    private val mainClient = OkHttpClient.Builder()
        .callTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .connectionPool(connectionPool)
        .retryOnConnectionFailure(false)
        .addInterceptor(createRetryInterceptor())
        .addInterceptor(ChuckerInterceptor(App.CONTEXT))
        .addInterceptor(createHeaderInterceptor())
        .addInterceptor(createForbiddenInterceptor())
        .authenticator(TokenAuthenticator())
        .build()

    // Separate client for refresh token (without token refresh interceptor)
    private val refreshClient = OkHttpClient.Builder()
        .callTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .connectionPool(connectionPool)
        .retryOnConnectionFailure(true)
        .addInterceptor(createHeaderInterceptor())
        .addInterceptor(ChuckerInterceptor(App.CONTEXT))
        .build()

    fun apiAuth(): AuthInterface = createRetrofit(mainClient, "auth").create(AuthInterface::class.java)

    private fun createRetrofit(client: OkHttpClient, prefix: String): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl("${BASE_URL}/${prefix}/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    private fun createHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val builder = chain.request()
                .newBuilder()
                .header("Accept", "application/json")
                .header("Accept-Language", App.LANGUAGE)

            val token = SpHelper.get(SpKeys.TOKEN, "")
            if (token.isNotEmpty()) builder.header("Authorization", "Bearer $token")

            chain.proceed(builder.build())
        }
    }

    private fun createRetryInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
            var attempt = 0
            var response: okhttp3.Response? = null
            while (response == null) {
                try {
                    response = chain.proceed(request)
                } catch (e: IOException) {
                    val isTransient = e is SocketException || e.message?.contains("Software caused connection abort", ignoreCase = true) == true
                    if (request.method != "GET" || !isTransient || attempt >= MAX_RETRIES) throw e
                    attempt++
                    Thread.sleep(500L * attempt) // Exponential backoff
                }
            }
            response
        }
    }

    private fun createForbiddenInterceptor(): Interceptor {
        return Interceptor { chain ->
            val response = chain.proceed(chain.request())
            val requestToken = chain.request().header("Authorization")?.removePrefix("Bearer ")
            if (response.code != 403 || requestToken == null) return@Interceptor response

            val newToken = renewSession(requestToken) ?: return@Interceptor response
            response.close()
            chain.proceed(chain.request().newBuilder().header("Authorization", "Bearer $newToken").build())
        }
    }

    @Synchronized
    private fun renewSession(requestToken: String?): String? {
        if (requestToken.isNullOrEmpty()) return null

        val currentToken = SpHelper.get(SpKeys.TOKEN, "")
        val refreshToken = SpHelper.get(SpKeys.REFRESH, "")

        if (requestToken != currentToken && currentToken.isNotEmpty()) return currentToken

        if (refreshToken.isEmpty()) {
            NavigationManager.signOut()
            return null
        }

        val newToken = try {
            SpHelper.put(SpKeys.TOKEN, refreshToken)
            refreshTokenSync()
        } catch (_: IOException) {
            SpHelper.put(SpKeys.TOKEN, currentToken)
            NavigationManager.showSessionRenewal()
            return null
        }
        if (newToken == null) NavigationManager.signOut() else NavigationManager.hideSessionRenewal()
        return newToken
    }

    fun retrySessionRenewal() {
        renewSession(SpHelper.get(SpKeys.TOKEN, ""))
    }

    private fun refreshTokenSync(): String? {
        return try {
            val response = runBlocking { createRetrofit(refreshClient, "auth").create(AuthInterface::class.java).refresh(BuildConfig.VERSION_NAME) }

            if (response.isSuccessful && response.body()?.result != null) {
                val session = response.body()!!.result!!
                SpHelper.put(SpKeys.TOKEN, session.accessToken)
                SpHelper.put(SpKeys.REFRESH, session.refreshToken)
                session.accessToken
            } else {
                // Clear tokens on refresh failure
                SpHelper.put(SpKeys.TOKEN, "")
                SpHelper.put(SpKeys.REFRESH, "")
                null
            }
        } catch (e: IOException) {
            throw e
        } catch (_: Exception) {
            SpHelper.put(SpKeys.TOKEN, "")
            SpHelper.put(SpKeys.REFRESH, "")
            null
        }
    }

    /**
     * TokenAuthenticator handles 401 responses automatically
     */
    private class TokenAuthenticator : Authenticator {
        override fun authenticate(route: Route?, response: okhttp3.Response): Request? {
            // Don't retry if we've already tried MAX_RETRIES times
            if (responseCount(response) >= MAX_RETRIES) {
                return null
            }

            val requestToken = response.request.header("Authorization")?.removePrefix("Bearer ")
            val newToken = renewSession(requestToken) ?: return null
            return response.request.newBuilder()
                .header("Authorization", "Bearer $newToken")
                .build()
        }

        private fun responseCount(response: okhttp3.Response): Int {
            var result = 1
            var currentResponse = response.priorResponse
            while (currentResponse != null) {
                result++
                currentResponse = currentResponse.priorResponse
            }
            return result
        }
    }

    /**
     * Parse error from response
     */
    fun parseError(response: Response<*>): Exception {
        return try {
            when (response.code()) {
                403                     -> ApiException(403, "Forbidden Access")
                404                     -> ApiException(404, "Not Found")
                401, 500, 502, 503, 504 -> ApiException(response.code(), "Server Error")
                else                    -> parseErrorBody(response)
            }
        } catch (_: Exception) {
            ApiException(response.code(), "Unknown Error")
        }
    }

    private fun parseErrorBody(response: Response<*>): Exception {
        val errorJson = response.errorBody()?.string()
        if (errorJson.isNullOrBlank()) {
            return ApiException(response.code(), "Unknown Error")
        }

        return try {
            // Try to parse as ResponseWrapper first
            val wrapper = json.decodeFromString<ResponseWrapper<Nothing>>(errorJson)
            ApiException(response.code(), wrapper.message ?: "Unknown Error")
        } catch (_: Exception) {
            try {
                // Try to parse as ValidationErrorResponse
                val validation = json.decodeFromString<ValidationErrorResponse>(errorJson)
                val firstError = validation.errors.values.firstOrNull()?.firstOrNull()
                ApiException(response.code(), firstError ?: validation.message)
            } catch (_: Exception) {
                ApiException(response.code(), "Unknown Error")
            }
        }
    }

    /**
     * Safe API call with loading management
     */
    suspend fun <T> safeCall(
        requestId: String,
        loadingMessage: String?,
        block: suspend () -> T,
    ): T {
        if (loadingMessage != null) LoadingManager.enterLoading(requestId, loadingMessage)
        try {
            return block()
        } catch (e: CancellationException) {
            throw e
        } catch (e: ApiException) {
            throw e
        } catch (_: IOException) {
            throw ApiException(0, "Unknown Error", isNoAnswer = true)
        } catch (_: Exception) {
            throw ApiException(0, "Unknown Error")
        } finally {
            if (loadingMessage != null) LoadingManager.exitLoading(requestId)
        }
    }
}