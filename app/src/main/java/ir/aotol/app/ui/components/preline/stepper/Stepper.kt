package ir.aotol.app.ui.components.preline.stepper

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ir.aotol.app.ui.components.preline.stepper.data.StepperItem

@Composable
fun Stepper(
    items: List<StepperItem>,
    modifier: Modifier = Modifier,
    alignment: StepperTheme.ItemAlignment = StepperTheme.ItemAlignment.Center,
    direction: StepperTheme.ItemDirection = StepperTheme.ItemDirection.Vertical,
) {
    val displayItems = items.mapIndexed { index, item ->
        StepperItem(
            step = index + 1,
            title = item.title,
            description = item.description,
            alignment = alignment,
            direction = direction,
            type = item.type,
            position = when (index) {
                0               -> StepperTheme.ItemPosition.Start
                items.lastIndex -> StepperTheme.ItemPosition.End
                else            -> StepperTheme.ItemPosition.Middle
            }
        )
    }
    Row(modifier = modifier.fillMaxWidth()) { displayItems.forEach { Step(it, Modifier.weight(1f)) } }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    val item = StepperItem(
        step = 1,
        title = "Step",
        description = "This is a very long description for sample reasons.",
        alignment = StepperTheme.ItemAlignment.Center,
        direction = StepperTheme.ItemDirection.Horizontal,
        type = StepperTheme.ItemType.Default,
        position = StepperTheme.ItemPosition.Middle
    )
    Stepper(
        items = listOf(
            item.copy(type = StepperTheme.ItemType.ActiveCheck),
            item.copy(type = StepperTheme.ItemType.ActiveNumber),
            item
        ),
        alignment = StepperTheme.ItemAlignment.Center,
        direction = StepperTheme.ItemDirection.Vertical
    )
}