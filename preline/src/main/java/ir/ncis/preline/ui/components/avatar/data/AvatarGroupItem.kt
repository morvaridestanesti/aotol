package ir.ncis.preline.ui.components.avatar.data

import androidx.compose.ui.graphics.painter.Painter
import ir.ncis.preline.ui.components.avatar.AvatarTheme

data class AvatarGroupItem(
    val imagePainter: Painter? = null,
    val imageUrl: String? = null,
    val placeholder: String? = null,
    val type: AvatarTheme.Type = AvatarTheme.Type.Image,
    val color: AvatarTheme.Color = AvatarTheme.Color.Image,
)
