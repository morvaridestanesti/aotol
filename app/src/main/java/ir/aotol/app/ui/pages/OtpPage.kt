package ir.aotol.app.ui.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ir.aotol.app.R
import ir.aotol.app.ui.components.page_title.PageTitle
import ir.aotol.app.ui.components.page_title.data.NavigationItem
import ir.aotol.app.ui.components.preline.AotolTheme
import ir.aotol.app.ui.components.preline.alert.Alert
import ir.aotol.app.ui.components.preline.alert.AlertTheme
import ir.aotol.app.ui.components.preline.alert.data.AlertButton
import ir.aotol.app.ui.components.preline.spinner.Spinner
import ir.aotol.app.ui.components.preline.spinner.SpinnerTheme
import ir.aotol.app.ui.theme.Colors
import ir.ncis.preline.ui.components.pin_input.PinInput
import ir.ncis.preline.ui.components.pin_input.PinInputTheme

@Composable
fun OtpPage(navController: NavController) {
    Column(modifier = Modifier.padding(16.dp)) {
        PageTitle("تأیید شماره موبایل", NavigationItem(painterResource(R.drawable.ic_chevron_back)) { navController.popBackStack() })
        Text(
            text = "کد تایید را وارد کنید",
            color = Colors.Text.Normal,
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "کد 6 رقمی به شماره 365****0912 پیامک شد.",
            color = Colors.Text.Hint,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
        )
        Spacer(Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(R.drawable.ic_edit),
                contentDescription = null,
                tint = Colors.Foreground.Focus,
                modifier = Modifier.size(16.dp)
            )
            Spacer(Modifier.width(6.dp))
            Text(
                text = "ویرایش شماره",
                color = Colors.Text.Active,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
            )
        }
        Spacer(Modifier.height(18.dp))
        PinInput("", {}, Modifier.align(Alignment.CenterHorizontally), 5, aspectRatio = 0.75f, size = PinInputTheme.Size.Large)
        Spacer(Modifier.height(18.dp))
        Alert(
            leadingIcon = painterResource(R.drawable.ic_no_wifi),
            color = AlertTheme.Color.Red,
            type = AlertTheme.Type.Soft,
            messageContent = "مشکلی پیش آمد. لطفا دوباره تلاش کنید.",
            button1 = AlertButton("تلاش مجدد") { }
        )
        Spacer(Modifier.height(18.dp))
        Card(
            modifier = Modifier
                .size(height = 34.dp, width = 80.dp)
                .align(Alignment.CenterHorizontally),
            colors = CardDefaults.cardColors(Colors.Background.Error),
            elevation = CardDefaults.cardElevation(0.dp),
            shape = CircleShape
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_time),
                    contentDescription = null,
                    tint = Colors.Text.Label,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(Modifier.width(6.dp))
                Text(
                    text = "01:59",
                    color = Colors.Text.Label,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }
        Spacer(Modifier.height(8.dp))
        Text(
            text = "ارسال مجدد کد",
            color = Colors.Text.Hint,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(18.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "در حال بررسی کد...",
                color = Colors.Text.Hint,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(Modifier.width(4.dp))
            Spinner(
                modifier = Modifier.size(18.dp),
                size = SpinnerTheme.Size.Small,
                circularLine = true,
                progress = 100
            )
        }
    }
}

@Preview(showBackground = true, apiLevel = 36, showSystemUi = true)
@Composable
private fun Preview() {
    AotolTheme { OtpPage(rememberNavController()) }
}
