package ir.aotol.app.retrofit.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Document(
    @SerialName("id") val id: Int,
    @SerialName("type") val type: String,
    @SerialName("version") val version: String,
    @SerialName("title") val title: String,
    @SerialName("label") val label: String,
    @SerialName("sections") val sections: List<Section>,
    @SerialName("requires_reacceptance") val requiresReacceptance: Boolean,
    @SerialName("published_at") val publishedAt: String,
) {
    @Serializable
    data class Section(
        @SerialName("body") val body: String,
        @SerialName("title") val title: String,
    )
}
