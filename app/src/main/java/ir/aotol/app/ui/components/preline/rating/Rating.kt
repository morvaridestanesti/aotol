package ir.aotol.app.ui.components.preline.rating

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ir.aotol.app.R
import ir.aotol.app.helpers.disabledAlpha

@Composable
fun Rating(
    value: Int,
    modifier: Modifier = Modifier,
    shape: RatingTheme.Shape = RatingTheme.Shape.Star,
    height: Dp = 20.dp,
    isEnabled: Boolean = true,
    onValueChange: (Int) -> Unit,
) {
    val theme = getTheme(shape, height)
    Row(
        modifier = modifier.disabledAlpha(!isEnabled),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        for (currentValue in 1..5) {
            val isFilled = currentValue <= value
            Icon(
                painter = painterResource(
                    when (shape) {
                        RatingTheme.Shape.Heart -> R.drawable.ic_heart
                        RatingTheme.Shape.Star  -> R.drawable.ic_star
                    }
                ),
                contentDescription = null,
                modifier = Modifier
                    .height(theme.height)
                    .aspectRatio(1f)
                    .clickable(
                        enabled = isEnabled,
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                    ) { onValueChange(currentValue) },
                tint = if (isFilled) theme.filledColor else theme.emptyColor
            )
        }
    }
}

@Preview(showBackground = true, group = "Heart", name = "Active")
@Composable
private fun Heart() {
    val rating = remember { mutableIntStateOf(3) }
    Rating(rating.intValue, shape = RatingTheme.Shape.Heart) { rating.intValue = it }
}

@Preview(showBackground = true, group = "Heart", name = "Disabled")
@Composable
private fun HeartDisabled() {
    val rating = remember { mutableIntStateOf(3) }
    Rating(rating.intValue, shape = RatingTheme.Shape.Heart, isEnabled = false) { rating.intValue = it }
}

@Preview(showBackground = true, group = "Star", name = "Active")
@Composable
private fun Star() {
    val rating = remember { mutableIntStateOf(3) }
    Rating(rating.intValue) { rating.intValue = it }
}

@Preview(showBackground = true, group = "Star", name = "Disabled")
@Composable
private fun StarDisabled() {
    val rating = remember { mutableIntStateOf(3) }
    Rating(rating.intValue, isEnabled = false) { rating.intValue = it }
}
