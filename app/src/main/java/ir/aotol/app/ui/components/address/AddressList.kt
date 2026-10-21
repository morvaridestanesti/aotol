package ir.aotol.app.ui.components.address

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import ir.aotol.app.retrofit.models.Address
import ir.aotol.app.ui.components.preline.AotolTheme
import ir.aotol.app.ui.theme.Colors
import ir.ncis.preline.ui.components.avatar.Avatar
import ir.ncis.preline.ui.components.avatar.AvatarTheme
import ir.ncis.preline.ui.components.badge.Badge
import ir.ncis.preline.ui.components.badge.BadgeTheme

@Composable
fun AddressList(
    address: Address,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Avatar(
            imageUrl = address.icon,
            color = AvatarTheme.Color.Blue
        )
        Column(modifier = Modifier.weight(1f)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "خانه",
                    color = Colors.Text.Label,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
                Badge(
                    placeholder = "پیش‌فرض",
                    color = BadgeTheme.Color.Blue,
                    shape = BadgeTheme.Shape.Pilled,
                    size = BadgeTheme.Size.Small,
                )
            }
            Text(
                text = "تهران، سعادت آباد، خیابان سرو غربی، کوچه نهم، پلاک ۱۲، واحد ۴",
                color = Colors.Text.Label,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
            )
        }
        Box(Modifier.size(32.dp)) {
            Icon(
                painterResource(R.drawable.ic_more_vertical),
                contentDescription = null,
                tint = Colors.Foreground.Hint,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(6.dp)
            )
        }
    }
}

@Preview(showBackground = true, apiLevel = 36)
@Composable
private fun Preview() = AotolTheme {
    AddressList(
        address = Address(
            id = 1,
            name = "Shahraki",
            icon = "",
            address = "janat abad",
            lat = 42.0,
            lng = 100.0,
            sort = 5,
            is_default = true,
        ),
        navController = rememberNavController()
    )
}