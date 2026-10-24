package ir.aotol.app.retrofit.interfaces

import ir.aotol.app.retrofit.ResponseWrapper
import ir.aotol.app.retrofit.models.AuthInfo
import ir.aotol.app.retrofit.models.Otp
import ir.aotol.app.retrofit.models.SessionFull
import ir.aotol.app.retrofit.models.User
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface AuthInterface {
    @GET("info")
    suspend fun info(): Response<ResponseWrapper<AuthInfo>>

    @DELETE("logout")
    suspend fun logout(): Response<ResponseWrapper<Unit>>

    @FormUrlEncoded
    @POST("login")
    suspend fun login(@Field("mobile") mobile: String, @Field("code") code: String, @Field("device_uid") deviceUid: String, @Field("version_app") versionApp: String, @Field("os") os: String, @Field("version_os") versionOs: String, @Field("device") device: String?, @Field("version_device") versionDevice: String?, @Field("push_token") pushToken: String?): Response<ResponseWrapper<User>>

    @FormUrlEncoded
    @PUT("refresh")
    suspend fun refresh(@Field("version_app") versionApp: String): Response<ResponseWrapper<SessionFull>>

    @FormUrlEncoded
    @POST("register")
    suspend fun register(@Field("mobile") mobile: String, @Field("device_uid") deviceUid: String?): Response<ResponseWrapper<Otp>>

}