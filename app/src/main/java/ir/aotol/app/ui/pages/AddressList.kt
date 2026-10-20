package ir.aotol.app.ui.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ir.aotol.app.R
import ir.aotol.app.ui.components.page_title.PageTitle
import ir.aotol.app.ui.components.page_title.data.NavigationItem
import ir.aotol.app.ui.components.preline.AotolTheme
import ir.aotol.app.ui.components.preline.button.Button
import ir.aotol.app.ui.components.preline.button.ButtonTheme
import ir.aotol.app.ui.theme.Colors

@Composable
fun AddressList(navController: NavController) {
    Column {
        PageTitle("آدرس‌های من", NavigationItem(painterResource(R.drawable.ic_chevron_back)) { navController.popBackStack() })
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(Modifier.weight(1f))
            Button(
                placeholder = "افزودن آدرس",
                modifier = Modifier.fillMaxWidth(),
                color = ButtonTheme.Color.Blue,
                size = ButtonTheme.Size.Default,
                leadingIcon = painterResource(R.drawable.ic_add)
            ) { }
        }
    }
}

@Preview(showBackground = true, apiLevel = 36, showSystemUi = true)
@Composable
private fun Preview() = AotolTheme { AddressList(rememberNavController()) }