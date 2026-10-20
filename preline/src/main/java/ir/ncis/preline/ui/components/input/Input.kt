package ir.ncis.preline.ui.components.input

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ir.ncis.preline.R
import ir.ncis.preline.helpers.disabledAlpha
import ir.ncis.preline.ui.components.avatar.Avatar
import ir.ncis.preline.ui.components.avatar.AvatarTheme
import ir.ncis.preline.ui.components.badge.Badge
import ir.ncis.preline.ui.components.badge.BadgeTheme
import ir.ncis.preline.ui.components.input.data.InputContent
import ir.ncis.preline.ui.components.input.data.InputTag
import ir.ncis.preline.ui.theme.PrelineTheme
import ir.ncis.preline.ui.theme.medium
import ir.ncis.preline.ui.theme.semiBold

@Composable
fun Input(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    secondaryLabel: String? = null,
    placeholder: String? = null,
    feedbackMessage: String? = null,
    tags: List<InputTag> = emptyList(),
    isTagMode: Boolean = false,
    onNewTag: ((String) -> Unit)? = null,
    leadingContent: InputContent? = null,
    leadingDivider: Boolean = false,
    trailingContent: InputContent? = null,
    trailingDivider: Boolean = false,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    validationState: InputTheme.ValidationState = InputTheme.ValidationState.None,
    size: InputTheme.Size = InputTheme.Size.Default,
    type: InputTheme.Type = InputTheme.Type.Bordered,
    shape: InputTheme.Shape = InputTheme.Shape.Rounded,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()
    val isFilled = value.isNotEmpty()

    val interactionState = when {
        !enabled -> InputTheme.InteractionState.Disabled
        isFocused && isFilled -> InputTheme.InteractionState.FocusFilled
        isFocused -> InputTheme.InteractionState.Focus
        isHovered -> InputTheme.InteractionState.Hover
        else -> InputTheme.InteractionState.Default
    }

    val theme = getTheme(type, interactionState, validationState, size, shape)

    Column(modifier = modifier.fillMaxWidth().disabledAlpha(!enabled)) {
        label?.let { labelText ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = labelText,
                    color = theme.labelColor,
                    style = PrelineTheme.typography.sm.semiBold,
                )
                Spacer(Modifier.weight(1f))
                secondaryLabel?.let { secondaryLabelText ->
                    Text(
                        text = secondaryLabelText,
                        color = theme.secondaryLabelColor,
                        style = PrelineTheme.typography.sm,
                    )
                }
            }
            Spacer(Modifier.height(10.dp))
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .then(if (singleLine) Modifier.height(theme.containerHeight) else Modifier.heightIn(min = theme.containerHeight * 2)),
            colors = CardDefaults.cardColors(containerColor = theme.backgroundColor),
            border = if (type == InputTheme.Type.Underline) null else BorderStroke(theme.borderWidth, theme.borderColor),
            elevation = CardDefaults.cardElevation(0.dp),
            shape = theme.shape
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .let {
                        if (type == InputTheme.Type.Underline) {
                            it.drawBehind {
                                val strokeWidthPx = theme.borderWidth.toPx()
                                val offsetY = this.size.height - strokeWidthPx / 2
                                drawLine(
                                    color = theme.borderColor,
                                    start = Offset(0f, offsetY),
                                    end = Offset(this.size.width, offsetY),
                                    strokeWidth = strokeWidthPx
                                )
                            }
                        } else {
                            it
                        }
                    }
                    .padding(horizontal = theme.containerPadding, vertical = if (singleLine) 0.dp else theme.containerPadding),
                verticalAlignment = if (singleLine) Alignment.CenterVertically else Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(theme.contentSpacing)
            ) {
                leadingContent?.let { InputContentSlot(it, size, theme) }
                if (leadingDivider) InputDivider()
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(theme.contentSpacing)
                ) {
                    tags.forEach { tag -> InputTagChip(tag = tag) }
                    Box(modifier = Modifier.weight(1f)) {
                        if (value.isEmpty() && placeholder != null) {
                            Text(
                                text = placeholder,
                                style = theme.textStyle,
                                color = theme.placeholderColor,
                                maxLines = if (singleLine) 1 else Int.MAX_VALUE,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        BasicTextField(
                            value = value,
                            onValueChange = onValueChange,
                            enabled = enabled,
                            singleLine = singleLine,
                            interactionSource = interactionSource,
                            textStyle = theme.textStyle.copy(color = theme.textColor),
                            visualTransformation = visualTransformation,
                            keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = ImeAction.Done),
                            keyboardActions = KeyboardActions(onDone = {
                                if (isTagMode) {
                                    onNewTag?.invoke(value)
                                    onValueChange("")
                                }
                            }),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                validationIcon(validationState)?.let {
                    Icon(painter = painterResource(it), contentDescription = null, modifier = Modifier.size(theme.iconSize), tint = theme.feedbackTextColor)
                }
                if (trailingDivider) InputDivider()
                trailingContent?.let { InputContentSlot(it, size, theme) }
            }
        }
        feedbackMessage?.let { InputFeedbackMessage(message = it, theme = theme) }
    }
}

@Composable
internal fun InputContentSlot(content: InputContent, size: InputTheme.Size, theme: InputTheme) {
    Row(
        modifier = content.onClick?.let { Modifier.clickable(role = Role.Button, onClick = it) } ?: Modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        content.iconStart?.let {
            Icon(
                painter = it,
                contentDescription = null,
                modifier = Modifier.size(theme.iconSize),
                tint = theme.placeholderColor
            )
        }
        content.avatarUrl?.let {
            Avatar(
                imageUrl = it,
                shape = AvatarTheme.Shape.Circular
            )
        } ?: content.avatarPainter?.let {
            Avatar(
                imagePainter = it,
                shape = AvatarTheme.Shape.Circular
            )
        }
        content.text?.let {
            Text(
                text = it,
                color = theme.placeholderColor,
                style = PrelineTheme.typography.sm.medium,
            )
        }
        content.iconEnd?.let {
            Icon(
                painter = it,
                contentDescription = null,
                modifier = Modifier.size(theme.iconSize),
                tint = theme.placeholderColor
            )
        }
    }
}

@Composable
internal fun InputDivider() {
    VerticalDivider(
        modifier = Modifier.fillMaxHeight(),
        thickness = 1.dp,
        color = PrelineTheme.colors.layerBorder
    )
}

@Composable
internal fun InputTagChip(tag: InputTag) {
    Badge(
        placeholder = tag.title,
        avatar = tag.avatar,
        onCloseClick = { tag.onRemove?.invoke(tag) },
        type = BadgeTheme.Type.White,
        color = BadgeTheme.Color.White,
        shape = BadgeTheme.Shape.Pilled
    )
}

@Composable
internal fun InputFeedbackMessage(message: String, theme: InputTheme) {
    Text(
        text = message,
        style = PrelineTheme.typography.xs,
        color = theme.feedbackTextColor,
        modifier = Modifier.padding(top = 4.dp)
    )
}

private fun validationIcon(state: InputTheme.ValidationState): Int? = when (state) {
    InputTheme.ValidationState.Invalid -> R.drawable.ic_info
    InputTheme.ValidationState.Success -> R.drawable.ic_check
    InputTheme.ValidationState.None    -> null
}
