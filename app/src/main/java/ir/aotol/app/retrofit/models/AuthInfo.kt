package ir.aotol.app.retrofit.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthInfo(
    @SerialName("user") val user: User,
    @SerialName("next_step") val nextStep: String,
)
