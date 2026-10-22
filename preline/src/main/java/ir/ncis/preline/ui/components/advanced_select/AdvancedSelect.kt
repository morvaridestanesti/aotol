package ir.ncis.preline.ui.components.advanced_select

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ir.ncis.preline.R
import ir.ncis.preline.helpers.disabledAlpha
import ir.ncis.preline.ui.components.advanced_select.data.SelectOption
import ir.ncis.preline.ui.components.badge.Badge
import ir.ncis.preline.ui.components.badge.BadgeTheme
import ir.ncis.preline.ui.theme.PrelineTheme
import ir.ncis.preline.ui.theme.medium
import ir.ncis.preline.ui.theme.semiBold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdvancedSelect(
    options: List<SelectOption>,
    selected: Set<String>,
    onSelectedChange: (Set<String>) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    multiple: Boolean = false,
    display: AdvancedSelectTheme.Display = AdvancedSelectTheme.Display.Text,
    search: AdvancedSelectTheme.Search = AdvancedSelectTheme.Search.None,
    searchPlaceholder: String? = null,
    leadingIcon: Painter? = null,
    enabled: Boolean = true,
    maxMenuHeight: Dp = 280.dp,
) {
    val theme = getTheme()
    var expanded by remember { mutableStateOf(false) }
    var query by remember { mutableStateOf("") }
    val chosen = options.filter { it.value in selected }
    val visible = options.filter { query.isBlank() || it.label.contains(query, ignoreCase = true) }

    fun choose(option: SelectOption) {
        if (multiple) {
            onSelectedChange(if (option.value in selected) selected - option.value else selected + option.value)
        } else {
            onSelectedChange(setOf(option.value))
            expanded = false
            query = ""
        }
    }

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        label?.let { Text(text = it, color = theme.text, style = PrelineTheme.typography.sm.medium) }
        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { if (enabled) expanded = it }) {
            val anchorType = if (search == AdvancedSelectTheme.Search.InField) ExposedDropdownMenuAnchorType.PrimaryEditable else ExposedDropdownMenuAnchorType.PrimaryNotEditable
            Row(
                modifier = Modifier
                    .menuAnchor(anchorType, enabled)
                    .fillMaxWidth()
                    .heightIn(min = 44.dp)
                    .disabledAlpha(!enabled)
                    .clip(theme.shape)
                    .background(theme.background)
                    .border(if (expanded) 2.dp else 1.dp, if (expanded) theme.focusedStroke else theme.stroke, theme.shape)
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                leadingIcon?.let { Icon(painter = it, contentDescription = null, tint = theme.description, modifier = Modifier.size(16.dp)) }
                if (!multiple) chosen.firstOrNull()?.icon?.let { OptionImage(it) }
                Box(modifier = Modifier.weight(1f)) {
                    when {
                        search == AdvancedSelectTheme.Search.InField && expanded -> QueryField(query, { query = it }, placeholder, theme)
                        chosen.isEmpty()                                         -> Text(text = placeholder.orEmpty(), color = theme.placeholder, style = PrelineTheme.typography.sm, maxLines = 1)
                        display == AdvancedSelectTheme.Display.Badges            -> Row(
                            modifier = Modifier.horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            chosen.forEach { option ->
                                Badge(
                                    placeholder = option.label,
                                    avatar = option.icon,
                                    type = BadgeTheme.Type.White,
                                    color = BadgeTheme.Color.Gray,
                                    shape = BadgeTheme.Shape.Pilled,
                                    onCloseClick = { if (enabled) onSelectedChange(selected - option.value) }
                                )
                            }
                        }

                        else                                                     -> Text(
                            text = chosen.joinToString(", ") { it.label },
                            color = theme.text,
                            style = PrelineTheme.typography.sm,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
                Icon(painter = painterResource(R.drawable.ic_selector), contentDescription = null, tint = theme.description, modifier = Modifier.size(16.dp))
            }
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false; query = "" },
                modifier = Modifier.heightIn(max = maxMenuHeight),
                shape = theme.shape,
                containerColor = theme.menuBackground,
                shadowElevation = 4.dp,
                border = BorderStroke(1.dp, theme.stroke)
            ) {
                if (search == AdvancedSelectTheme.Search.InMenu) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(painter = painterResource(R.drawable.ic_search), contentDescription = null, tint = theme.description, modifier = Modifier.size(16.dp))
                        Box(Modifier.weight(1f)) { QueryField(query, { query = it }, searchPlaceholder, theme) }
                        if (query.isNotEmpty()) {
                            Icon(
                                painter = painterResource(R.drawable.ic_clear),
                                contentDescription = null,
                                tint = theme.description,
                                modifier = Modifier.size(16.dp).clickable(role = Role.Button) { query = "" }
                            )
                        }
                    }
                    HorizontalDivider(thickness = 1.dp, color = theme.stroke, modifier = Modifier.padding(vertical = 4.dp))
                }
                visible.forEachIndexed { index, option ->
                    if (option.group != null && option.group != visible.getOrNull(index - 1)?.group) {
                        Text(
                            text = option.group.uppercase(),
                            color = theme.group,
                            style = PrelineTheme.typography.xs.semiBold,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    }
                    OptionRow(option, option.value in selected, theme) { choose(option) }
                }
            }
        }
    }
}

@Composable
private fun QueryField(query: String, onQueryChange: (String) -> Unit, placeholder: String?, theme: AdvancedSelectTheme) {
    Box(contentAlignment = Alignment.CenterStart) {
        if (query.isEmpty()) placeholder?.let { Text(text = it, color = theme.placeholder, style = PrelineTheme.typography.sm, maxLines = 1) }
        BasicTextField(
            value = query,
            onValueChange = onQueryChange,
            singleLine = true,
            textStyle = PrelineTheme.typography.sm.copy(color = theme.text),
            cursorBrush = SolidColor(theme.focusedStroke),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun OptionRow(option: SelectOption, isSelected: Boolean, theme: AdvancedSelectTheme, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp)
            .clip(RoundedCornerShape(8.dp))
            .disabledAlpha(!option.enabled)
            .clickable(enabled = option.enabled, role = Role.Checkbox, onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        option.status?.let { Box(Modifier.size(6.dp).background(getStatusColor(it), CircleShape)) }
        option.icon?.let { OptionImage(it) }
        Text(text = option.label, color = theme.text, style = PrelineTheme.typography.sm.medium)
        option.description?.let { Text(text = it, color = theme.description, style = PrelineTheme.typography.xs) }
        Spacer(Modifier.weight(1f))
        if (isSelected) Icon(painter = painterResource(R.drawable.ic_check), contentDescription = null, tint = theme.check, modifier = Modifier.size(14.dp))
    }
}

@Composable
private fun OptionImage(painter: Painter) {
    Image(painter = painter, contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.size(20.dp).clip(CircleShape))
}
