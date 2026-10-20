package ir.ncis.preline.ui.components.accordion.data

import ir.ncis.preline.ui.components.badge.BadgeTheme

data class AccordionBadge(
    val title: String,
    val type: BadgeTheme.Type = BadgeTheme.Type.Soft,
    val color: BadgeTheme.Color = BadgeTheme.Color.Blue,
    val size: BadgeTheme.Size = BadgeTheme.Size.Default,
    val shape: BadgeTheme.Shape = BadgeTheme.Shape.Pilled
)
