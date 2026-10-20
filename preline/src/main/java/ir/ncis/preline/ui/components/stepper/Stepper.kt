package ir.ncis.preline.ui.components.stepper

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ir.ncis.preline.ui.components.stepper.data.StepperItem

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
            icon = item.icon,
            avatar = item.avatar,
            loading = item.loading,
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
    Row(modifier = modifier.fillMaxWidth()) {
        displayItems.forEachIndexed { index, item -> Step(item, Modifier.weight(1f), previousType = displayItems.getOrNull(index - 1)?.type) }
    }
}
