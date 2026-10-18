package ir.aotol.app.ui.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ir.aotol.app.ui.components.preline.AotolTheme
import ir.aotol.app.ui.theme.Colors

@Composable
fun MainPage(navController: NavController) {
    Column(Modifier.fillMaxSize()) {
        Box(Modifier
            .weight(1f)
            .fillMaxWidth()) {}
        HorizontalDivider(thickness = 1.dp, color = Colors.Foreground.Normal)
        Row(Modifier.fillMaxWidth()) { }
    }
}

@Preview(showBackground = true, apiLevel = 36, showSystemUi = true)
@Composable
private fun Preview() = AotolTheme { MainPage(rememberNavController()) }