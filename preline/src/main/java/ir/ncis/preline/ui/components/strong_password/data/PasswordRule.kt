package ir.ncis.preline.ui.components.strong_password.data

data class PasswordRule(
    val label: String,
    val isMet: (String) -> Boolean,
)
