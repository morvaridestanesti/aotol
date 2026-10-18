package ir.aotol.app.ui.components.page_title

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.aotol.app.R
import ir.aotol.app.helpers.invisibleClickable
import ir.aotol.app.ui.components.page_title.data.NavigationItem
import ir.aotol.app.ui.components.preline.AotolTheme
import ir.aotol.app.ui.theme.Colors

@Composable
fun PageTitle(
    label: String? = null,
    previous: NavigationItem? = null,
    next: NavigationItem? = null
) {
    Row(
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        previous?.let {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .invisibleClickable { it.onClick() }) {
                Icon(
                    painter = it.icon,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    tint = Colors.Foreground.Icon
                )
            }
        } ?: Spacer(Modifier.size(40.dp))
        label?.let {
            Text(
                text = it,
                textAlign = TextAlign.Center,
                color = Colors.Text.Title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
        } ?: Spacer(Modifier.weight(1f))
        next?.let {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .invisibleClickable { it.onClick() }) {
                Icon(
                    painter = it.icon,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    tint = Colors.Foreground.Icon
                )
            }
        } ?: Spacer(Modifier.size(40.dp))
    }
}

@Preview(showBackground = true, apiLevel = 36)
@Composable
private fun Preview() = AotolTheme { PageTitle("صفحه اصلی", NavigationItem(painterResource(R.drawable.ic_chevron_back)) { }) }