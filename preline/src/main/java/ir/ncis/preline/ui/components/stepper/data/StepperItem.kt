package ir.ncis.preline.ui.components.stepper.data

import androidx.compose.ui.graphics.painter.Painter
import ir.ncis.preline.ui.components.stepper.StepperTheme

data class StepperItem(
    val step: Int,
    val title: String? = null,
    val description: String? = null,
    val icon: Painter? = null,
    val avatar: Painter? = null,
    val loading: Boolean = false,
    val alignment: StepperTheme.ItemAlignment = StepperTheme.ItemAlignment.Center,
    val direction: StepperTheme.ItemDirection = StepperTheme.ItemDirection.Vertical,
    val type: StepperTheme.ItemType = StepperTheme.ItemType.Default,
    val position: StepperTheme.ItemPosition = StepperTheme.ItemPosition.Middle,
)
