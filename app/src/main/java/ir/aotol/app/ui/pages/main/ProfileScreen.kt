package ir.aotol.app.ui.pages.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
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
import ir.aotol.app.ui.components.preline.accordion.Accordion
import ir.aotol.app.ui.components.preline.avatar.Avatar
import ir.aotol.app.ui.components.preline.avatar.AvatarTheme
import ir.aotol.app.ui.components.preline.badge.Badge
import ir.aotol.app.ui.components.preline.badge.BadgeTheme
import ir.aotol.app.ui.components.preline.button.Button
import ir.aotol.app.ui.components.preline.button.ButtonTheme
import ir.aotol.app.ui.theme.AotolTheme
import ir.aotol.app.ui.theme.Colors

@Composable
fun ProfileScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        PageTitle(label = "پروفایل")
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Avatar(
                type = AvatarTheme.Type.Soft,
                color = AvatarTheme.Color.Blue,
                shape = AvatarTheme.Shape.Circular,
                size = AvatarTheme.Size.Large,
                imagePainter = painterResource(R.drawable.ic_profile)
            )
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(Colors.Background.Card),
                border = BorderStroke(1.dp, color = Colors.Foreground.Normal),
                elevation = CardDefaults.cardElevation(0.dp),
                shape = RoundedCornerShape(18.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_phone),
                            contentDescription = null,
                            tint = Colors.Text.Label,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(Modifier.width(12.dp))
                        Text(
                            text = "نام و نام خانوادگی",
                            color = Colors.Text.Hint,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Spacer(Modifier.weight(1f))
                        Icon(
                            painter = painterResource(R.drawable.ic_edit),
                            contentDescription = null,
                            tint = Colors.Text.Hint,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(Modifier.width(6.dp))
                        Text(
                            text = "علی رضایی",
                            color = Colors.Text.Normal,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    HorizontalDivider(thickness = 1.dp, color = Colors.Foreground.Normal)
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_phone),
                            contentDescription = null,
                            tint = Colors.Text.Label,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(Modifier.width(12.dp))
                        Text(
                            text = "شماره موبایل",
                            color = Colors.Text.Hint,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Spacer(Modifier.weight(1f))
                        Icon(
                            painter = painterResource(R.drawable.ic_edit),
                            contentDescription = null,
                            tint = Colors.Text.Hint,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(Modifier.width(6.dp))
                        Text(
                            text = "09123456789",
                            color = Colors.Text.Normal,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    HorizontalDivider(thickness = 1.dp, color = Colors.Foreground.Normal)
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_national_code),
                            contentDescription = null,
                            tint = Colors.Text.Label,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(Modifier.width(12.dp))
                        Text(
                            text = "کد ملی",
                            color = Colors.Text.Hint,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Spacer(Modifier.weight(1f))
                        Column(horizontalAlignment = Alignment.End) {
                            Row {
                                Icon(
                                    painter = painterResource(R.drawable.ic_lock),
                                    contentDescription = null,
                                    tint = Colors.Text.Hint,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(Modifier.width(6.dp))
                                Text(
                                    text = "23 ••••• 001",
                                    color = Colors.Text.Normal,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                            Badge(
                                placeholder = "تأیید شده با شاهکار",
                                trailingIcon = painterResource(R.drawable.ic_verified),
                                shape = BadgeTheme.Shape.Pilled,
                                size = BadgeTheme.Size.Small,
                            )
                        }
                    }
                }
            }
            Accordion(
                items = listOf()
            )
        }
        Spacer(Modifier.weight(1f))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = "خروج از حساب کاربری",
            leadingIcon = painterResource(R.drawable.ic_logout),
            color = ButtonTheme.Color.Red,
            style = ButtonTheme.Style.Soft,
            isFilled = true
        ) { }
        HorizontalDivider(thickness = 1.dp, color = Colors.Foreground.Normal)
    }
}

@Preview(showBackground = true, apiLevel = 36, showSystemUi = true)
@Composable
private fun Preview() {
    AotolTheme { ProfileScreen(rememberNavController()) }
}