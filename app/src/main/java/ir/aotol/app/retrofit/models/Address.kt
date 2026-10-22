package ir.aotol.app.retrofit.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Address(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("icon") val icon: String,
    @SerialName("address") val address: String,
    @SerialName("lat") val lat: Double?,
    @SerialName("lng") val lng: Double?,
    @SerialName("sort") val sort: Int,
    @SerialName("default") val isDefault: Boolean,
)
