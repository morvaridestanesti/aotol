package ir.aotol.app.retrofit.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Otp(
    @SerialName("mobile") val mobile: Int,
    @SerialName("expires_in") val expiresIn: Int,
)
