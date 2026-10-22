package ir.aotol.app.retrofit.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("id") val id: Int,
    @SerialName("mobile") val mobile: Int,
    @SerialName("first_name") val firstName: String?,
    @SerialName("last_name") val lastName: String?,
    @SerialName("full_name") val fullName: String?,
    @SerialName("national_code") val nationalCode: Int?,
    @SerialName("is_national_code_verified") val isNationalCodeVerified: Boolean,
    @SerialName("status") val status: String,
    @SerialName("is_profile_completed") val isProfileCompeleted: Boolean,
    @SerialName("member_since") val memberSince: String,
    @SerialName("member_since_label") val memberSinceLabel: String,
)
