package ir.aotol.app.retrofit.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Acceptance(
    @SerialName("next_step") val nextStep: String,
)
