package ir.aotol.app.retrofit.interfaces

import ir.aotol.app.retrofit.ResponseWrapper
import ir.aotol.app.retrofit.models.Session
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.PUT

interface AuthInterface {
    @FormUrlEncoded
    @PUT("refresh")
    suspend fun refresh(@Field("version_app") versionApp: String): Response<ResponseWrapper<Session>>
}