package ir.aotol.app.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ir.aotol.app.R
import ir.aotol.app.ui.components.preline.spinner.Spinner
import ir.aotol.app.ui.components.preline.spinner.SpinnerTheme
import ir.aotol.app.ui.components.preline.AotolTheme

@Composable
fun SplashPage(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.mipmap.bg_splash),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(bottom = 64.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.aotol),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(100.dp)
            )
            Text(
                text = "اوتول",
                color = Color.White,
                fontSize = 48.sp,
                fontWeight = FontWeight.Black,
                modifier = Modifier.offset(y = (-16).dp)
            )
            Text(
                text = "AOTOL",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 8.sp,
                modifier = Modifier.offset(y = (-16).dp)
            )
            Text(
                text = "خدمات هوشمند خودرو",
                color = Color.White,
                fontSize = 14.sp,
            )
        }
        Spinner(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 128.dp),
            size = SpinnerTheme.Size.Default,
            circularLine = true
        )
    }
}

@Preview(showBackground = true, apiLevel = 36)
@Composable
private fun Preview() = AotolTheme { SplashPage(rememberNavController()) }