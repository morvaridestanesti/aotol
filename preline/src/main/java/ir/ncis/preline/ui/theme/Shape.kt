package ir.ncis.preline.ui.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Immutable
data class PrelineShapes(
    val sm: Shape = RoundedCornerShape(4.dp),
    val md: Shape = RoundedCornerShape(6.dp),
    val lg: Shape = RoundedCornerShape(8.dp),
    val xl: Shape = RoundedCornerShape(12.dp),
    val xl2: Shape = RoundedCornerShape(16.dp),
    val full: Shape = CircleShape,
)
