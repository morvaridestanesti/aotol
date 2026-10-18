package ir.aotol.app.ui.components.preline

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class AccentColors(
    val solid: Color,
    val solidHover: Color,
    val outlineHover: Color,
    val soft: Color,
    val softHover: Color,
    val softForeground: Color,
)

@Immutable
data class PrelineColors(
    val isDark: Boolean,

    val background: Color,
    val foreground: Color,

    val border: Color,
    val borderLine5: Color,
    val borderLine8: Color,

    val layer: Color,
    val layerHover: Color,
    val layerFocus: Color,
    val layerBorder: Color,
    val layerForeground: Color,

    val surface: Color,
    val surface1: Color,
    val surface2: Color,
    val surface3: Color,
    val surface4: Color,
    val surface5: Color,
    val surfaceHover: Color,
    val surfaceFocus: Color,
    val surfaceActive: Color,
    val surfaceForeground: Color,

    val muted: Color,
    val mutedHover: Color,
    val mutedFocus: Color,
    val mutedForeground: Color,
    val mutedForeground1: Color,
    val mutedForeground2: Color,

    val secondary: Color,
    val secondaryHover: Color,
    val secondaryFocus: Color,
    val secondaryForeground: Color,

    val primary: Color,
    val primaryHover: Color,
    val primaryFocus: Color,
    val primaryActive: Color,
    val primaryForeground: Color,

    val card: Color,
    val cardBorder: Color,
    val destructive: Color,
    val switch: Color,

    val blue: AccentColors,
    val green: AccentColors,
    val red: AccentColors,
    val yellow: AccentColors,
)

val LightPrelineColors = PrelineColors(
    isDark = false,

    background = Palette.White,
    foreground = Palette.Gray.Gray800,

    border = Palette.Gray.Gray200,
    borderLine5 = Palette.Gray.Gray500,
    borderLine8 = Palette.Gray.Gray800,

    layer = Palette.White,
    layerHover = Palette.Gray.Gray50,
    layerFocus = Palette.Gray.Gray50,
    layerBorder = Palette.Gray.Gray200,
    layerForeground = Palette.Gray.Gray800,

    surface = Palette.Gray.Gray100,
    surface1 = Palette.Gray.Gray200,
    surface2 = Palette.Gray.Gray300,
    surface3 = Palette.Gray.Gray400,
    surface4 = Palette.Gray.Gray500,
    surface5 = Palette.Gray.Gray600,
    surfaceHover = Palette.Gray.Gray200,
    surfaceFocus = Palette.Gray.Gray200,
    surfaceActive = Palette.Gray.Gray200,
    surfaceForeground = Palette.Gray.Gray800,

    muted = Palette.Gray.Gray50,
    mutedHover = Palette.Gray.Gray100,
    mutedFocus = Palette.Gray.Gray100,
    mutedForeground = Palette.Gray.Gray400,
    mutedForeground1 = Palette.Gray.Gray500,
    mutedForeground2 = Color(0xFFE3E5E7),

    secondary = Palette.Gray.Gray900,
    secondaryHover = Palette.Gray.Gray800,
    secondaryFocus = Palette.Gray.Gray800,
    secondaryForeground = Palette.White,

    primary = Palette.Blue.Blue600,
    primaryHover = Palette.Blue.Blue700,
    primaryFocus = Palette.Blue.Blue700,
    primaryActive = Palette.Blue.Blue700,
    primaryForeground = Palette.White,

    card = Palette.White,
    cardBorder = Palette.Gray.Gray200,
    destructive = Palette.Red.Red500,
    switch = Palette.White,

    blue = AccentColors(
        solid = Palette.Blue.Blue600,
        solidHover = Palette.Blue.Blue700,
        outlineHover = Palette.Blue.Blue700,
        soft = Palette.Blue.Blue100,
        softHover = Palette.Blue.Blue200,
        softForeground = Palette.Blue.Blue800
    ),
    green = AccentColors(
        solid = Palette.Teal.Teal500,
        solidHover = Palette.Teal.Teal600,
        outlineHover = Palette.Teal.Teal400,
        soft = Palette.Teal.Teal100,
        softHover = Palette.Teal.Teal200,
        softForeground = Palette.Teal.Teal800
    ),
    red = AccentColors(
        solid = Palette.Red.Red500,
        solidHover = Palette.Red.Red600,
        outlineHover = Palette.Red.Red400,
        soft = Palette.Red.Red100,
        softHover = Palette.Red.Red200,
        softForeground = Palette.Red.Red800
    ),
    yellow = AccentColors(
        solid = Palette.Yellow.Yellow500,
        solidHover = Palette.Yellow.Yellow600,
        outlineHover = Palette.Yellow.Yellow400,
        soft = Palette.Yellow.Yellow100,
        softHover = Palette.Yellow.Yellow200,
        softForeground = Palette.Yellow.Yellow800
    ),
)

val DarkPrelineColors = PrelineColors(
    isDark = true,

    background = Palette.Neutral.Neutral900,
    foreground = Palette.Neutral.Neutral200,

    border = Palette.Neutral.Neutral700,
    borderLine5 = Palette.Neutral.Neutral500,
    borderLine8 = Palette.Neutral.Neutral200,

    layer = Palette.Neutral.Neutral800,
    layerHover = Palette.Neutral.Neutral700,
    layerFocus = Palette.Neutral.Neutral700,
    layerBorder = Palette.Neutral.Neutral700,
    layerForeground = Palette.White,

    surface = Palette.Neutral.Neutral700,
    surface1 = Palette.Neutral.Neutral600,
    surface2 = Palette.Neutral.Neutral500,
    surface3 = Palette.Neutral.Neutral500,
    surface4 = Palette.Neutral.Neutral500,
    surface5 = Palette.Neutral.Neutral400,
    surfaceHover = Palette.Neutral.Neutral600,
    surfaceFocus = Palette.Neutral.Neutral600,
    surfaceActive = Palette.Neutral.Neutral600,
    surfaceForeground = Palette.Neutral.Neutral200,

    muted = Palette.Neutral.Neutral800,
    mutedHover = Palette.Neutral.Neutral700,
    mutedFocus = Palette.Neutral.Neutral700,
    mutedForeground = Palette.Neutral.Neutral500,
    mutedForeground1 = Palette.Neutral.Neutral400,
    mutedForeground2 = Palette.Neutral.Neutral700,

    secondary = Palette.White,
    secondaryHover = Palette.Neutral.Neutral100,
    secondaryFocus = Palette.Neutral.Neutral100,
    secondaryForeground = Palette.Neutral.Neutral800,

    primary = Palette.Blue.Blue600,
    primaryHover = Palette.Blue.Blue700,
    primaryFocus = Palette.Blue.Blue700,
    primaryActive = Palette.Blue.Blue700,
    primaryForeground = Palette.White,

    card = Palette.Neutral.Neutral900,
    cardBorder = Palette.Neutral.Neutral700,
    destructive = Palette.Red.Red500,
    switch = Palette.White,

    blue = AccentColors(
        solid = Palette.Blue.Blue600,
        solidHover = Palette.Blue.Blue700,
        outlineHover = Palette.Blue.Blue500,
        soft = Palette.Blue.Blue800.copy(alpha = 0.3f),
        softHover = Palette.Blue.Blue800.copy(alpha = 0.2f),
        softForeground = Palette.Blue.Blue400
    ),
    green = AccentColors(
        solid = Palette.Teal.Teal500,
        solidHover = Palette.Teal.Teal600,
        outlineHover = Palette.Teal.Teal400,
        soft = Palette.Teal.Teal800.copy(alpha = 0.3f),
        softHover = Palette.Teal.Teal800.copy(alpha = 0.2f),
        softForeground = Palette.Teal.Teal500
    ),
    red = AccentColors(
        solid = Palette.Red.Red500,
        solidHover = Palette.Red.Red600,
        outlineHover = Palette.Red.Red400,
        soft = Palette.Red.Red800.copy(alpha = 0.3f),
        softHover = Palette.Red.Red800.copy(alpha = 0.2f),
        softForeground = Palette.Red.Red500
    ),
    yellow = AccentColors(
        solid = Palette.Yellow.Yellow500,
        solidHover = Palette.Yellow.Yellow600,
        outlineHover = Palette.Yellow.Yellow400,
        soft = Palette.Yellow.Yellow800.copy(alpha = 0.3f),
        softHover = Palette.Yellow.Yellow800.copy(alpha = 0.2f),
        softForeground = Palette.Yellow.Yellow500
    ),
)
