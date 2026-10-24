package ir.aotol.app.retrofit.interfaces

import ir.aotol.app.retrofit.ResponseWrapper
import ir.aotol.app.retrofit.models.AuthInfo
import ir.aotol.app.retrofit.models.Geocode
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BaseInterface {
    @GET("geocode/{reverse}")
    suspend fun getGeocode(@Query("latitude") latitude: Int,@Query("longitude") longitude: Int):Response<ResponseWrapper<Geocode>>
}