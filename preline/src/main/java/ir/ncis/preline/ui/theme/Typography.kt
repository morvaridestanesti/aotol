package ir.ncis.preline.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import ir.ncis.preline.R
import androidx.compose.material3.Typography as MaterialTypography

private fun fontFamily(regular: Int, medium: Int, semiBold: Int, bold: Int) = FontFamily(
    Font(regular, FontWeight.Normal),
    Font(medium, FontWeight.Medium),
    Font(semiBold, FontWeight.SemiBold),
    Font(bold, FontWeight.Bold),
)

object PrelineFonts {
    val Inter: FontFamily = fontFamily(R.font.inter_regular, R.font.inter_medium, R.font.inter_semibold, R.font.inter_bold)

    val Vazirmatn: FontFamily = fontFamily(R.font.vazirmatn_regular, R.font.vazirmatn_medium, R.font.vazirmatn_semibold, R.font.vazirmatn_bold)
}

@Immutable
data class PrelineTypography(
    val xs: TextStyle,
    val sm: TextStyle,
    val base: TextStyle,
    val lg: TextStyle,
    val xl: TextStyle,
    val xl2: TextStyle,
    val xl3: TextStyle,
)

private fun textStyle(fontFamily: FontFamily, fontSize: TextUnit, lineHeight: TextUnit) = TextStyle(
    fontFamily = fontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = fontSize,
    lineHeight = lineHeight,
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    )
)

fun prelineTypography(fontFamily: FontFamily, lineHeightScale: Float = 1f) = PrelineTypography(
    xs = textStyle(fontFamily, 12.sp, 16.sp * lineHeightScale),
    sm = textStyle(fontFamily, 14.sp, 20.sp * lineHeightScale),
    base = textStyle(fontFamily, 16.sp, 24.sp * lineHeightScale),
    lg = textStyle(fontFamily, 18.sp, 28.sp * lineHeightScale),
    xl = textStyle(fontFamily, 20.sp, 28.sp * lineHeightScale),
    xl2 = textStyle(fontFamily, 24.sp, 32.sp * lineHeightScale),
    xl3 = textStyle(fontFamily, 30.sp, 36.sp * lineHeightScale),
)

val DefaultPrelineTypography = prelineTypography(PrelineFonts.Inter)

val PersianPrelineTypography = prelineTypography(PrelineFonts.Vazirmatn, lineHeightScale = 1.25f)

val TextStyle.medium: TextStyle get() = copy(fontWeight = FontWeight.Medium)

val TextStyle.semiBold: TextStyle get() = copy(fontWeight = FontWeight.SemiBold)

val TextStyle.bold: TextStyle get() = copy(fontWeight = FontWeight.Bold)

internal fun PrelineTypography.toMaterialTypography(): MaterialTypography = MaterialTypography(
    displayLarge = xl3,
    displayMedium = xl3,
    displaySmall = xl2,
    headlineLarge = xl2,
    headlineMedium = xl,
    headlineSmall = lg,
    titleLarge = xl,
    titleMedium = base,
    titleSmall = sm,
    bodyLarge = base,
    bodyMedium = sm,
    bodySmall = xs,
    labelLarge = sm,
    labelMedium = xs,
    labelSmall = xs,
)
