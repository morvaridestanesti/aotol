package ir.aotol.app.ui.pages

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ir.aotol.app.R
import ir.aotol.app.ui.components.preline.button.Button
import ir.aotol.app.ui.components.preline.button.ButtonTheme
import ir.aotol.app.ui.components.preline.AotolTheme
import ir.aotol.app.ui.theme.Colors

@Composable
fun BanPage(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(32.dp))
        Card(
            modifier = Modifier.size(96.dp),
            colors = CardDefaults.cardColors(Colors.Background.Error),
            elevation = CardDefaults.cardElevation(0.dp),
            shape = CircleShape
        ) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(Colors.Background.ErrorDark),
                elevation = CardDefaults.cardElevation(0.dp),
                shape = CircleShape
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_ban),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    tint = Colors.Foreground.Error
                )
            }
        }
        Text(
            text = "حساب شما مسدود شده است",
            color = Colors.Text.Normal,
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 20.dp)
        )
        Text(
            text = "امکان استفاده از اپلیکیشن با این حساب وجود ندارد. برای پیگیری با پشتیبانی تماس بگیرید.",
            color = Colors.Text.Hint,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 14.dp)
        )
        Spacer(Modifier.weight(1f))
        Button(
            modifier = Modifier.fillMaxWidth(),
            placeholder = "ارتباط با پشتیبانی",
            leadingIcon = painterResource(R.drawable.ic_support),
            color = ButtonTheme.Color.Blue
        ) { }
        Spacer(Modifier.height(8.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            placeholder = "ورود با شماره دیگر",
            style = ButtonTheme.Style.Outlined,
            color = ButtonTheme.Color.Gray
        ) { }
    }
}

@Preview(showBackground = true, apiLevel = 36, showSystemUi = true)
@Composable
private fun Preview() = AotolTheme { BanPage(rememberNavController()) }