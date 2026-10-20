package ir.aotol.app.ui.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import ir.aotol.app.ui.components.preline.button.Button
import ir.aotol.app.ui.components.preline.button.ButtonTheme
import ir.aotol.app.ui.theme.Colors
import ir.ncis.preline.ui.components.input.Input
import ir.ncis.preline.ui.components.input.InputTheme

@Composable
fun EditeNamePage(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        PageTitle("اطلاعات شخصی", NavigationItem(painterResource(R.drawable.ic_chevron_back)) { navController.popBackStack() })
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "ویرایش نام",
                color = Colors.Text.Normal,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
            )
            Text(
                text = "این نام روی فاکتورهای شما ثبت می‌شود",
                color = Colors.Text.Hint,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
            )
            Text(
                text = "نام",
                color = Colors.Text.Hint,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
            )
            Input(
                value = "",
                onValueChange = {},
                placeholder = "علی",
                type = InputTheme.Type.Bordered,
                validationState = InputTheme.ValidationState.Invalid,
                feedbackMessage = "نام باید حداقل ۲ حرف باشد."
            )
            Text(
                text = "نام خانوادگی",
                color = Colors.Text.Hint,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
            )
            Input(
                value = "",
                onValueChange = {},
                placeholder = "رضایی",
                type = InputTheme.Type.Bordered,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_error),
                    contentDescription = null,
                    tint = Colors.Foreground.Hint,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "نام و نام خانوادگی هر کدام ۲ تا ۴۰ حرف فارسی",
                    color = Colors.Text.Hint,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(Modifier.weight(1f))
            Button(
                placeholder = "ذخیره",
                modifier = Modifier.fillMaxWidth(),
                color = ButtonTheme.Color.Gray,
                state = ButtonTheme.State.Disabled,
            ) {}
        }
    }
}

@Preview(showBackground = true, apiLevel = 36, showSystemUi = true)
@Composable
private fun Preview() = AotolTheme { EditeNamePage(rememberNavController()) }