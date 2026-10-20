package ir.ncis.preline.ui.components.stepper

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ir.ncis.preline.ui.theme.PrelineTheme
import ir.ncis.preline.ui.theme.medium

data class StepperTheme(
    val titleColor: Color,
    val descriptionColor: Color,
    val cardSize: Dp,
    val iconSize: Dp,
    val shape: Shape,
    val titleStyle: TextStyle,
    val descriptionStyle: TextStyle,
    val stepStyle: TextStyle,
) {
    sealed interface ItemAlignment {
        object Center : ItemAlignment
        object End : ItemAlignment
        object Start : ItemAlignment
    }

    sealed interface ItemDirection {
        object Horizontal : ItemDirection
        object Vertical : ItemDirection
    }

    sealed interface ItemPosition {
        object End : ItemPosition
        object Middle : ItemPosition
        object Start : ItemPosition
    }

    sealed interface ItemType {
        object ActiveCheck : ItemType
        object ActiveNumber : ItemType
        object Default : ItemType
        object Invalid : ItemType
        object Success : ItemType
    }
}

@Composable
@ReadOnlyComposable
internal fun getTheme(itemType: StepperTheme.ItemType): StepperTheme = StepperTheme(
    titleColor = PrelineTheme.colors.foreground,
    descriptionColor = PrelineTheme.colors.mutedForeground,
    cardSize = 28.dp,
    iconSize = 16.dp,
    shape = CircleShape,
    titleStyle = PrelineTheme.typography.sm.medium,
    descriptionStyle = PrelineTheme.typography.xs,
    stepStyle = PrelineTheme.typography.xs.medium
)

@Composable
@ReadOnlyComposable
internal fun getDividerColor(itemType: StepperTheme.ItemType): Color = when (itemType) {
    StepperTheme.ItemType.Default, StepperTheme.ItemType.Invalid -> PrelineTheme.colors.border
    StepperTheme.ItemType.Success                                -> PrelineTheme.colors.green.solid
    else                                                         -> PrelineTheme.colors.primaryActive
}

@Composable
@ReadOnlyComposable
internal fun getCardBackgroundColor(itemType: StepperTheme.ItemType): Color = when (itemType) {
    StepperTheme.ItemType.ActiveCheck, StepperTheme.ItemType.ActiveNumber -> PrelineTheme.colors.primaryActive
    StepperTheme.ItemType.Default                                         -> PrelineTheme.colors.surface
    StepperTheme.ItemType.Invalid                                         -> PrelineTheme.colors.destructive
    StepperTheme.ItemType.Success                                         -> PrelineTheme.colors.green.solid
}

@Composable
@ReadOnlyComposable
internal fun getCardContentColor(itemType: StepperTheme.ItemType): Color = when (itemType) {
    StepperTheme.ItemType.ActiveCheck, StepperTheme.ItemType.Invalid, StepperTheme.ItemType.Success -> PrelineTheme.colors.primaryForeground
    StepperTheme.ItemType.ActiveNumber                                                              -> PrelineTheme.colors.primaryForeground
    StepperTheme.ItemType.Default                                                                   -> PrelineTheme.colors.surfaceForeground
}
