package ir.ncis.preline.ui.components.rating

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ir.ncis.preline.R
import ir.ncis.preline.ui.components.button.Button
import ir.ncis.preline.ui.components.button.ButtonTheme

@Composable
fun ThumbRating(
    helpful: Boolean?,
    modifier: Modifier = Modifier,
    yesLabel: String = "Yes",
    noLabel: String = "No",
    onHelpfulChange: (Boolean?) -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        listOf(true to yesLabel, false to noLabel).forEach { (thumb, label) ->
            val selected = helpful == thumb
            Button(
                placeholder = label,
                leadingIcon = painterResource(if (thumb) R.drawable.ic_thumb_up else R.drawable.ic_thumb_down),
                style = if (selected) ButtonTheme.Style.Soft else ButtonTheme.Style.White,
                color = if (selected) ButtonTheme.Color.Blue else ButtonTheme.Color.Dark,
            ) { onHelpfulChange(if (selected) null else thumb) }
        }
    }
}
