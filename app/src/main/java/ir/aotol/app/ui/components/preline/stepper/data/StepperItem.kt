package ir.aotol.app.ui.components.preline.stepper.data

import ir.aotol.app.ui.components.preline.stepper.StepperTheme

data class StepperItem(
    val step: Int,
    val title: String? = null,
    val description: String? = null,
    val alignment: StepperTheme.ItemAlignment = StepperTheme.ItemAlignment.Center,
    val direction: StepperTheme.ItemDirection = StepperTheme.ItemDirection.Vertical,
    val type: StepperTheme.ItemType = StepperTheme.ItemType.Default,
    val position: StepperTheme.ItemPosition = StepperTheme.ItemPosition.Middle,
)
