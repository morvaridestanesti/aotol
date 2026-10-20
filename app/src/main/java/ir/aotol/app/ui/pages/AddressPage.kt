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
fun AddressPage(navController: NavController) {
    Column {
        PageTitle("آدرس‌های من", NavigationItem(painterResource(R.drawable.ic_chevron_back)) { navController.popBackStack() })
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(Modifier.height(32.dp))
            Card(
                modifier = Modifier.size(96.dp),
                colors = CardDefaults.cardColors(Colors.Background.Activelight),
                elevation = CardDefaults.cardElevation(0.dp),
                shape = CircleShape
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    colors = CardDefaults.cardColors(Colors.Background.Active),
                    elevation = CardDefaults.cardElevation(0.dp),
                    shape = CircleShape
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_location),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        tint = Colors.Foreground.Active
                    )
                }
            }
            Text(
                text = "هنوز آدرسی ثبت نکرده‌اید",
                color = Colors.Text.Title,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
            )
            Text(
                text = "آدرس خانه یا محل کار را با موقعیت دقیق روی نقشه ذخیره کنید تا در سفارش بعدی فقط انتخابش کنید.",
                color = Colors.Text.Label,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center
            )
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
private fun Preview() = AotolTheme { AddressPage(rememberNavController()) }