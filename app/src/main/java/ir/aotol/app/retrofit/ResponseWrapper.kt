package ir.aotol.app.retrofit

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseWrapper<T>(
    @SerialName("result") val result: T?,
    @SerialName("meta") val meta: Meta?,
    @SerialName("message") val message: String?,
    @SerialName("errors") val errors: String?,
)