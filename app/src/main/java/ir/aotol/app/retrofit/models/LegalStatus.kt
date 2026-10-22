package ir.aotol.app.retrofit.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LegalStatus(
    @SerialName("is_required") val isRequired: Boolean,
    @SerialName("is_update") val isUpdate: Boolean,
    @SerialName("next_step") val nextStep: String,
)
