package ir.aotol.app.retrofit.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Address(
    @SerialName("id") val id: Int,
    val name: String,
    val icon: String,
    val address: String,
    val lat: Double?,
    val lng: Double?,
    val sort: Int,
    val is_default: Boolean,
)
