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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import ir.aotol.app.ui.components.preline.accordion.Accordion
import ir.aotol.app.ui.components.preline.accordion.AccordionTheme
import ir.aotol.app.ui.components.preline.accordion.data.AccordionItem
import ir.aotol.app.ui.components.preline.button.Button
import ir.aotol.app.ui.components.preline.button.ButtonTheme
import ir.aotol.app.ui.components.preline.AotolTheme
import ir.aotol.app.ui.theme.Colors

@Composable
fun RulePage(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        PageTitle(label = "پذیرش قوانین")
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "قوانین و حریم خصوصی",
                color = Colors.Text.Normal,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
            )
            Text(
                text = "پیش از شروع، ببینید اطلاعات شما چگونه استفاده می‌شود.",
                color = Colors.Text.Hint,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
            )
            Spacer(Modifier.height(18.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(Colors.Background.Card),
                elevation = CardDefaults.cardElevation(0.dp),
                shape = RoundedCornerShape(18.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row {
                        Icon(
                            painter = painterResource(R.drawable.ic_phone),
                            contentDescription = null,
                            tint = Colors.Foreground.Focus,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "شماره موبایل",
                                color = Colors.Text.Normal,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                            )
                            Text(
                                text = "فقط برای ورود و اطلاع رسانی های حساب",
                                color = Colors.Text.Label,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                        Spacer(Modifier.width(12.dp))
                    }
                    Row {
                        Icon(
                            painter = painterResource(R.drawable.ic_car),
                            contentDescription = null,
                            tint = Colors.Foreground.Focus,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(Modifier.width(10.dp))
                        Text(
                            text = "برای ارائه سرویس، یادآوری و امداد",
                            color = Colors.Text.Label,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = "اطلاعات خودرو",
                            color = Colors.Text.Normal,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    Row {
                        Icon(
                            painter = painterResource(R.drawable.ic_lock),
                            contentDescription = null,
                            tint = Colors.Foreground.Focus,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "تراکنش ها",
                                color = Colors.Text.Normal,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                            )
                            Text(
                                text = "به صورت امن نگهداری و بدون رضایت شما با کسی به اشتراک گذاشته نمی‌شوند",
                                color = Colors.Text.Label,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                    }
                }
            }
            Spacer(Modifier.height(18.dp))
            Accordion(
                items = listOf(
                    AccordionItem(title = "قوانین و شرایط استفاده", icon = painterResource(R.drawable.ic_rules)) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            PageTitle("قوانین و شرایط استفاده", NavigationItem(painterResource(R.drawable.ic_chevron_back)) { navController.popBackStack() })
                            Card(
                                modifier = Modifier
                                    .size(height = 30.dp, width = 160.dp)
                                    .align(Alignment.CenterHorizontally),
                                colors = CardDefaults.cardColors(Colors.Background.Active),
                                elevation = CardDefaults.cardElevation(0.dp),
                                shape = CircleShape
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Icon(
                                        painter = painterResource(R.drawable.ic_rules),
                                        contentDescription = null,
                                        tint = Colors.Foreground.Focus,
                                        modifier = Modifier.size(14.dp)
                                    )
                                    Spacer(Modifier.width(6.dp))
                                    Text(
                                        text = "نسخه ۱٫۰ · مرداد ۱۴۰۵",
                                        color = Colors.Foreground.Focus,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.SemiBold,
                                    )
                                }
                            }
                            Column(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = "۱. پذیرش شرایط",
                                    color = Colors.Text.Normal,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                )
                                Text(
                                    text = "استفاده از اپلیکیشن به منزله پذیرش این شرایط است. در صورت عدم موافقت، امکان استفاده از خدمات وجود ندارد.",
                                    color = Colors.Text.Label,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Normal,
                                )
                            }
                            Column(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = "۲. تعهدات کاربر",
                                    color = Colors.Text.Normal,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                )
                                Text(
                                    text = "کاربر متعهد است اطلاعات صحیح ثبت کند و از حساب خود برای فعالیت های غیرقانونی یا آسیب به دیگران استفاده نکند.",
                                    color = Colors.Text.Label,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Normal,
                                )
                            }
                            Column(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = "۳. خدمات",
                                    color = Colors.Text.Normal,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                )
                                Text(
                                    text = "خدمات شامل مدیریت خودرو، یادآوری سرویس و درخواست امداد است و ممکن است به مرور تغییر کند.",
                                    color = Colors.Text.Label,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Normal,
                                )
                            }
                            Column(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = "۴. تغییرات قوانین",
                                    color = Colors.Text.Normal,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                )
                                Text(
                                    text = "در صورت تغییر اساسی قوانین، از طریق اعلان اطلاع رسانی شده و در صورت لزوم، پذیرش مجدد از شما درخواست می شود.",
                                    color = Colors.Text.Label,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Normal,
                                )
                            }
                        }
                    },
                    AccordionItem(title = "سیاست حریم خصوصی", icon = painterResource(R.drawable.ic_shield)) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            PageTitle("سیاست حریم خصوصی", NavigationItem(painterResource(R.drawable.ic_chevron_back)) { navController.popBackStack() })
                            Card(
                                modifier = Modifier
                                    .size(height = 30.dp, width = 160.dp)
                                    .align(Alignment.CenterHorizontally),
                                colors = CardDefaults.cardColors(Colors.Background.Active),
                                elevation = CardDefaults.cardElevation(0.dp),
                                shape = CircleShape
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Icon(
                                        painter = painterResource(R.drawable.ic_rules),
                                        contentDescription = null,
                                        tint = Colors.Foreground.Focus,
                                        modifier = Modifier.size(14.dp)
                                    )
                                    Spacer(Modifier.width(6.dp))
                                    Text(
                                        text = "نسخه ۱٫۰ · مرداد ۱۴۰۵",
                                        color = Colors.Foreground.Focus,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.SemiBold,
                                    )
                                }
                            }
                            Column(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = "۱. اطلاعات جمع آوری شده",
                                    color = Colors.Text.Normal,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                )
                                Text(
                                    text = "شماره موبایل، نام، اطلاعات خودرو، سوابق سرویس و اطلاعات دستگاه (مدل، سیستم عامل و نسخه اپ).",
                                    color = Colors.Text.Label,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Normal,
                                )
                            }
                            Column(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = "۲. نحوه استفاده",
                                    color = Colors.Text.Normal,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                )
                                Text(
                                    text = "اطلاعات صرفاً برای ارائه خدمات، امنیت حساب و اطلاع رسانی استفاده می شود و بدون رضایت شما در اختیار اشخاص ثالث قرار نمی گیرد.",
                                    color = Colors.Text.Label,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Normal,
                                )
                            }
                            Column(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = "۳. نگهداری و امنیت",
                                    color = Colors.Text.Normal,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                )
                                Text(
                                    text = "اطلاعات روی بستر امن و رمزنگاری شده نگهداری می شود و دسترسی به آن محدود و ثبت شده است.",
                                    color = Colors.Text.Label,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Normal,
                                )
                            }
                            Column(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = "۴. حقوق کاربر",
                                    color = Colors.Text.Normal,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                )
                                Text(
                                    text = "شما می توانید در هر زمان از بخش پروفایل، حساب کاربری خود و اطلاعات مرتبط با آن را حذف کنید.",
                                    color = Colors.Text.Label,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Normal,
                                )
                            }
                        }
                    },
                ),
                style = AccordionTheme.Style.Bordered,
                toggle = AccordionTheme.Toggle.EndArrow,
                type = AccordionTheme.Type.Basic,
            )
        }
        Spacer(Modifier.weight(1f))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = "تأیید و ادامه",
            color = ButtonTheme.Color.Blue
        ) { }
    }
}

@Preview(showBackground = true, apiLevel = 36, showSystemUi = true)
@Composable
private fun Preview() {
    AotolTheme { RulePage(rememberNavController()) }
}