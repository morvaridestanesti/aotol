package ir.aotol.app.ui.components.preline

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import ir.aotol.app.R
import androidx.compose.material3.Typography as MaterialTypography

val PrelineFontFamily: FontFamily = FontFamily(
    Font(R.font.black, weight = FontWeight.Black, style = FontStyle.Normal),
    Font(R.font.bold, weight = FontWeight.Bold, style = FontStyle.Normal),
    Font(R.font.extra_bold, weight = FontWeight.ExtraBold, style = FontStyle.Normal),
    Font(R.font.extra_light, weight = FontWeight.ExtraLight, style = FontStyle.Normal),
    Font(R.font.light, weight = FontWeight.Light, style = FontStyle.Normal),
    Font(R.font.medium, weight = FontWeight.Medium, style = FontStyle.Normal),
    Font(R.font.normal, weight = FontWeight.Normal, style = FontStyle.Normal),
    Font(R.font.semi_bold, weight = FontWeight.SemiBold, style = FontStyle.Normal),
    Font(R.font.thin, weight = FontWeight.Thin, style = FontStyle.Normal),
)

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

private fun textStyle(fontSize: TextUnit, lineHeight: TextUnit) = TextStyle(
    fontFamily = PrelineFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = fontSize,
    lineHeight = lineHeight,
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    )
)

val DefaultPrelineTypography = PrelineTypography(
    xs = textStyle(12.sp, 16.sp),
    sm = textStyle(14.sp, 20.sp),
    base = textStyle(16.sp, 24.sp),
    lg = textStyle(18.sp, 28.sp),
    xl = textStyle(20.sp, 28.sp),
    xl2 = textStyle(24.sp, 32.sp),
    xl3 = textStyle(30.sp, 36.sp),
)

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
