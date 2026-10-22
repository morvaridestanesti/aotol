package ir.aotol.app.retrofit

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseWrapper<T>(
    @SerialName("data") val data: T?,
    @SerialName("pagination") val pagination: Pagination?,
    @SerialName("message") val message: String?,
    @SerialName("errors") val errors: String?,
)