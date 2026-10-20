package ir.ncis.preline.ui.components.input_group

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import ir.ncis.preline.ui.theme.PrelineTheme
import ir.ncis.preline.ui.theme.medium

@Composable
fun InputGroup(
    segments: List<InputGroupTheme.Segment>,
    modifier: Modifier = Modifier,
    size: InputGroupTheme.Size = InputGroupTheme.Size.Default,
) {
    val theme = getTheme(size)

    Row(
        modifier = modifier
            .height(theme.height)
            .clip(theme.shape)
            .background(theme.background)
            .border(1.dp, theme.stroke, theme.shape)
    ) {
        segments.forEachIndexed { index, segment ->
            if (index > 0) VerticalDivider(thickness = 1.dp, color = theme.stroke)
            when (segment) {
                is InputGroupTheme.Segment.AddOn     -> AddOn(theme) {
                    Text(text = segment.text, color = theme.addOnForeground, style = theme.textStyle)
                }

                is InputGroupTheme.Segment.IconAddOn -> AddOn(theme, segment.onClick) {
                    Icon(painter = segment.icon, contentDescription = segment.contentDescription, tint = theme.addOnForeground, modifier = Modifier.size(16.dp))
                }

                is InputGroupTheme.Segment.Field     -> Field(segment, theme)
                is InputGroupTheme.Segment.Button    -> Button(segment, theme)
            }
        }
    }
}

@Composable
private fun AddOn(theme: InputGroupTheme, onClick: (() -> Unit)? = null, content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .background(theme.addOnBackground)
            .then(onClick?.let { Modifier.clickable(role = Role.Button, onClick = it) } ?: Modifier)
            .padding(horizontal = theme.horizontalPadding),
        contentAlignment = Alignment.Center
    ) { content() }
}

@Composable
private fun RowScope.Field(field: InputGroupTheme.Segment.Field, theme: InputGroupTheme) {
    Row(
        modifier = Modifier
            .weight(field.weight)
            .fillMaxHeight()
            .padding(horizontal = theme.horizontalPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        field.leadingIcon?.let { Icon(painter = it, contentDescription = null, tint = theme.placeholder, modifier = Modifier.size(16.dp)) }
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterStart) {
            if (field.value.isEmpty()) field.placeholder?.let { Text(text = it, color = theme.placeholder, style = theme.textStyle, maxLines = 1) }
            BasicTextField(
                value = field.value,
                onValueChange = field.onValueChange,
                singleLine = true,
                textStyle = theme.textStyle.copy(color = theme.text),
                cursorBrush = SolidColor(PrelineTheme.colors.primary),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun Button(button: InputGroupTheme.Segment.Button, theme: InputGroupTheme) {
    val colors = PrelineTheme.colors
    val solid = button.style == InputGroupTheme.ButtonStyle.Solid
    val foreground = if (solid) colors.primaryForeground else colors.layerForeground

    Row(
        modifier = Modifier
            .fillMaxHeight()
            .background(if (solid) colors.primary else colors.layer)
            .clickable(enabled = !button.loading, role = Role.Button, onClick = button.onClick)
            .padding(horizontal = theme.horizontalPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (button.loading) {
            CircularProgressIndicator(modifier = Modifier.size(14.dp), color = foreground, strokeWidth = 2.dp)
        } else {
            button.icon?.let {
                Icon(
                    painter = it,
                    contentDescription = button.contentDescription.takeIf { button.label == null },
                    tint = foreground,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
        button.label?.let { Text(text = it, color = foreground, style = theme.textStyle.medium) }
    }
}
