package ir.aotol.app.retrofit.interfaces

import ir.aotol.app.retrofit.ResponseWrapper
import ir.aotol.app.retrofit.models.Acceptance
import ir.aotol.app.retrofit.models.Document
import ir.aotol.app.retrofit.models.Geocode
import ir.aotol.app.retrofit.models.LegalStatus
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LegalInterface {
    @POST("acceptances")
    suspend fun acceptances(@Field("accepted") accepted: Boolean, @Field("version_app") versionApp: String?):Response<ResponseWrapper<Acceptance>>

    @GET("documents")
    suspend fun documents():Response<ResponseWrapper<Document>>

    @GET("status")
    suspend fun status():Response<ResponseWrapper<LegalStatus>>
}