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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.aotol.app.R
import ir.aotol.app.retrofit.models.Address
import ir.aotol.app.ui.theme.Colors
import ir.ncis.preline.ui.components.avatar.Avatar
import ir.ncis.preline.ui.components.avatar.AvatarTheme
import ir.ncis.preline.ui.components.badge.Badge
import ir.ncis.preline.ui.components.badge.BadgeTheme

@Composable
fun AddressItem(address: Address) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Avatar(
            imagePainter = painterResource(AddressIcon.fromKey(address.icon).res),
            color = AvatarTheme.Color.Blue
        )
        Column(modifier = Modifier.weight(1f)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = address.name,
                    color = Colors.Text.Label,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
                if (address.isDefault) {
                    Badge(
                        placeholder = "پیش‌فرض",
                        color = BadgeTheme.Color.Blue,
                        shape = BadgeTheme.Shape.Pilled,
                        size = BadgeTheme.Size.Small,
                    )
                }
            }
            Text(
                text = address.address,
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