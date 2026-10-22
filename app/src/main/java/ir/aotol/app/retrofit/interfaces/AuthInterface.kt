package ir.aotol.app.retrofit.interfaces

import ir.aotol.app.retrofit.ResponseWrapper
import ir.aotol.app.retrofit.models.AuthInfo
import ir.aotol.app.retrofit.models.SessionFull
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.PUT

interface AuthInterface {
    @GET("info")
    suspend fun info(): Response<ResponseWrapper<AuthInfo>>

    @FormUrlEncoded
    @PUT("refresh")
    suspend fun refresh(@Field("version_app") versionApp: String): Response<ResponseWrapper<SessionFull>>
}