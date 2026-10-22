package ir.aotol.app.retrofit.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Info(
    @SerialName("user") val user: User,
    @SerialName("next_step") val nextStep: String,
)
