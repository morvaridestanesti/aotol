package ir.aotol.app.ui.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ir.aotol.app.R
import ir.aotol.app.helpers.invisibleClickable
import ir.aotol.app.ui.components.preline.AotolTheme
import ir.aotol.app.ui.components.preline.PrelineTheme
import ir.aotol.app.ui.theme.Colors

@Composable
fun MainPage(navController: NavController) {
    val currentTabIndex = remember { mutableIntStateOf(0) }
    Column(Modifier.fillMaxSize()) {
        Box(
            Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {}
        HorizontalDivider(thickness = 1.dp, color = Colors.Foreground.Normal)
        Spacer(Modifier.height(2.dp))
        Row(Modifier.fillMaxWidth()) {
            NavigationItemView("پروفایل", painterResource(R.drawable.ic_profile), currentTabIndex.intValue == 0, Modifier.weight(1f)) { currentTabIndex.intValue = 0 }
            NavigationItemView("امداد", painterResource(R.drawable.ic_assist), currentTabIndex.intValue == 1, Modifier.weight(1f)) { currentTabIndex.intValue = 1 }
            NavigationItemView("خودروی من", painterResource(R.drawable.ic_car), currentTabIndex.intValue == 2, Modifier.weight(1f)) { currentTabIndex.intValue = 2 }
            NavigationItemView("خانه", painterResource(R.drawable.ic_home), currentTabIndex.intValue == 3, Modifier.weight(1f)) { currentTabIndex.intValue = 3 }
        }
    }
}

@Composable
private fun NavigationItemView(text: String, icon: Painter, isSelected: Boolean, modifier: Modifier = Modifier, onSelect: () -> Unit) {
    Column(
        modifier = modifier.invisibleClickable { onSelect() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            tint = if (isSelected) Colors.Foreground.Focus else Colors.Foreground.Hint,
            modifier = Modifier.size(24.dp)
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = text,
            color = if (isSelected) Colors.Text.Active else Colors.Text.Hint,
            style = PrelineTheme.typography.xs
        )
    }
}

@Preview(showBackground = true, apiLevel = 36, showSystemUi = true)
@Composable
private fun Preview() = AotolTheme { MainPage(rememberNavController()) }