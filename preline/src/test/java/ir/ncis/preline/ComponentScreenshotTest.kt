package ir.ncis.preline

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import ir.ncis.preline.ui.components.accordion.Accordion
import ir.ncis.preline.ui.components.accordion.AccordionTheme
import ir.ncis.preline.ui.components.accordion.data.AccordionItem
import ir.ncis.preline.ui.components.alert.Alert
import ir.ncis.preline.ui.components.alert.AlertTheme
import ir.ncis.preline.ui.components.alert.data.AlertButton
import ir.ncis.preline.ui.components.avatar.Avatar
import ir.ncis.preline.ui.components.avatar.AvatarGroup
import ir.ncis.preline.ui.components.avatar.AvatarTheme
import ir.ncis.preline.ui.components.avatar.data.AvatarGroupItem
import ir.ncis.preline.ui.components.badge.Badge
import ir.ncis.preline.ui.components.badge.BadgeTheme
import ir.ncis.preline.ui.components.blockquote.Blockquote
import ir.ncis.preline.ui.components.blockquote.BlockquoteTheme
import ir.ncis.preline.ui.components.breadcrumb.Breadcrumb
import ir.ncis.preline.ui.components.breadcrumb.BreadcrumbTheme
import ir.ncis.preline.ui.components.breadcrumb.data.BreadcrumbItem
import ir.ncis.preline.ui.components.button.Button
import ir.ncis.preline.ui.components.button.ButtonTheme
import ir.ncis.preline.ui.components.button_group.ButtonGroup
import ir.ncis.preline.ui.components.button_group.ButtonGroupTheme
import ir.ncis.preline.ui.components.button_group.data.ButtonGroupItem
import ir.ncis.preline.ui.components.button_icon.ButtonIcon
import ir.ncis.preline.ui.components.button_icon.ButtonIconTheme
import ir.ncis.preline.ui.components.card.Card
import ir.ncis.preline.ui.components.card.CardCaption
import ir.ncis.preline.ui.components.card.CardLink
import ir.ncis.preline.ui.components.card.CardText
import ir.ncis.preline.ui.components.card.CardTitle
import ir.ncis.preline.ui.components.chat_bubble.ChatBubble
import ir.ncis.preline.ui.components.chat_bubble.ChatBubbleTheme
import ir.ncis.preline.ui.components.chat_bubble.data.ChatBubbleItem
import ir.ncis.preline.ui.components.checkbox.Checkbox
import ir.ncis.preline.ui.components.input.Input
import ir.ncis.preline.ui.components.input.InputTheme
import ir.ncis.preline.ui.components.input_group.InputGroup
import ir.ncis.preline.ui.components.input_group.InputGroupTheme
import ir.ncis.preline.ui.components.legend.Legend
import ir.ncis.preline.ui.components.legend.LegendTheme
import ir.ncis.preline.ui.components.list.List
import ir.ncis.preline.ui.components.list.ListTheme
import ir.ncis.preline.ui.components.list.data.ListItem
import ir.ncis.preline.ui.components.list_group.ListGroup
import ir.ncis.preline.ui.components.list_group.ListGroupTheme
import ir.ncis.preline.ui.components.list_group.data.ListGroupItem
import ir.ncis.preline.ui.components.number_input.NumberInput
import ir.ncis.preline.ui.components.number_input.NumberInputTheme
import ir.ncis.preline.ui.components.pagination.Pagination
import ir.ncis.preline.ui.components.pin_input.PinInput
import ir.ncis.preline.ui.components.pin_input.PinInputTheme
import ir.ncis.preline.ui.components.progress.ActivityIndicator
import ir.ncis.preline.ui.components.progress.Progress
import ir.ncis.preline.ui.components.progress.ProgressTheme
import ir.ncis.preline.ui.components.progress.SignalBars
import ir.ncis.preline.ui.components.radio.Radio
import ir.ncis.preline.ui.components.rating.EmojiRating
import ir.ncis.preline.ui.components.rating.Rating
import ir.ncis.preline.ui.components.rating.ThumbRating
import ir.ncis.preline.ui.components.stepper.Stepper
import ir.ncis.preline.ui.components.stepper.StepperTheme
import ir.ncis.preline.ui.components.stepper.data.StepperItem
import ir.ncis.preline.ui.components.styled_icon.StyledIcon
import ir.ncis.preline.ui.components.styled_icon.StyledIconTheme
import ir.ncis.preline.ui.components.switch.Switch
import ir.ncis.preline.ui.components.tab.Tab
import ir.ncis.preline.ui.components.tab.TabTheme
import ir.ncis.preline.ui.components.table.Table
import ir.ncis.preline.ui.components.table.TableText
import ir.ncis.preline.ui.components.table.TableTheme
import ir.ncis.preline.ui.components.table.data.TableColumn
import ir.ncis.preline.ui.components.timeline.Timeline
import ir.ncis.preline.ui.components.timeline.TimelineTheme
import ir.ncis.preline.ui.components.timeline.data.TimelineAuthor
import ir.ncis.preline.ui.components.timeline.data.TimelineItem
import ir.ncis.preline.ui.components.tree_view.TreeView
import ir.ncis.preline.ui.components.tree_view.data.TreeNode
import ir.ncis.preline.ui.components.tab.data.TabItem
import ir.ncis.preline.ui.theme.DefaultPrelineTypography
import ir.ncis.preline.ui.theme.PersianPrelineTypography
import ir.ncis.preline.ui.theme.PrelineTheme
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = "w360dp-h640dp-mdpi")
class ComponentScreenshotTest {

    private enum class Design(val dark: Boolean, val persian: Boolean) {
        Light(dark = false, persian = false),
        Dark(dark = true, persian = false),
        PersianLight(dark = false, persian = true),
        PersianDark(dark = true, persian = true),
    }

    private fun snapshot(name: String, content: @Composable () -> Unit) {
        Design.entries.forEach { design ->
            captureRoboImage("src/test/screenshots/$name/${design.name}.png") {
                PrelineTheme(
                    darkTheme = design.dark,
                    typography = if (design.persian) PersianPrelineTypography else DefaultPrelineTypography,
                    layoutDirection = if (design.persian) LayoutDirection.Rtl else LayoutDirection.Ltr,
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(PrelineTheme.colors.background)
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) { content() }
                }
            }
        }
    }

    @Composable
    private fun t(english: String, persian: String) = if (LocalLayoutDirection.current == LayoutDirection.Rtl) persian else english

    @Composable
    private fun Wrap(content: @Composable () -> Unit) {
        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) { content() }
    }

    @Test
    fun accordion() = snapshot("accordion") {
        Accordion(
            items = listOf(
                AccordionItem(t("Is Preline UI free?", "آیا پرلاین رایگان است؟"), { Text(t("Yes, it is open source.", "بله، متن‌باز است.")) }, isExpanded = true),
                AccordionItem(t("What does lifetime access mean?", "دسترسی مادام‌العمر یعنی چه؟"), { Text("") }),
            ),
            style = AccordionTheme.Style.Bordered
        )
    }

    @Test
    fun alert() = snapshot("alert") {
        Alert(
            leadingIcon = painterResource(R.drawable.ic_info),
            title = t("New version published", "نسخه جدید منتشر شد"),
            messageContent = t("Refresh to see the changes.", "برای دیدن تغییرات صفحه را تازه کنید."),
            button1 = AlertButton(t("Learn more", "بیشتر بدانید")) {},
            type = AlertTheme.Type.Soft, color = AlertTheme.Color.Blue,
            onClose = {}
        )
        Alert(title = t("Saved", "ذخیره شد"), type = AlertTheme.Type.Solid, color = AlertTheme.Color.Green)
    }

    @Test
    fun avatar() = snapshot("avatar") {
        Wrap {
            Avatar(placeholder = t("Amanda Cole", "سارا کاظمی"), type = AvatarTheme.Type.Solid, color = AvatarTheme.Color.Blue, size = AvatarTheme.Size.Default, status = AvatarTheme.Status.Online)
            Avatar(placeholder = t("Amanda Cole", "سارا کاظمی"), type = AvatarTheme.Type.Soft, color = AvatarTheme.Color.Green, size = AvatarTheme.Size.Default, shape = AvatarTheme.Shape.Circular)
            Avatar(color = AvatarTheme.Color.Placeholder, size = AvatarTheme.Size.Default, shape = AvatarTheme.Shape.Circular)
        }
        AvatarGroup(items = List(3) { AvatarGroupItem(placeholder = t("Amanda Cole", "سارا کاظمی"), type = AvatarTheme.Type.Solid, color = AvatarTheme.Color.Gray) })
    }

    @Test
    fun badge() = snapshot("badge") {
        Wrap {
            Badge(placeholder = t("New", "جدید"), type = BadgeTheme.Type.Solid, color = BadgeTheme.Color.Blue)
            Badge(placeholder = t("Active", "فعال"), status = true)
            Badge(placeholder = t("Tag", "برچسب"), type = BadgeTheme.Type.White, onCloseClick = {})
            Badge(placeholder = "5", type = BadgeTheme.Type.Solid, color = BadgeTheme.Color.Red, shape = BadgeTheme.Shape.Pilled)
        }
    }

    @Test
    fun blockquote() = snapshot("blockquote") {
        Blockquote(
            placeholder = t("The documentation is outstanding - clear and detailed.", "مستندات عالی است؛ روشن و کامل."),
            authorTitle = t("Mark Villiams", "مریم ویلیامز"),
            sourceTitle = t("Product Manager", "مدیر محصول"),
            type = BlockquoteTheme.Type.Quote,
            size = BlockquoteTheme.Size.Default
        )
    }

    @Test
    fun breadcrumb() = snapshot("breadcrumb") {
        Breadcrumb(
            items = listOf(BreadcrumbItem(t("Home", "خانه"), painterResource(R.drawable.ic_user), onClick = {}), BreadcrumbItem(t("Application", "برنامه"))),
            separatorType = BreadcrumbTheme.SeparatorType.Chevron
        )
        Breadcrumb(
            items = listOf(BreadcrumbItem(t("Home", "خانه"), onClick = {}), BreadcrumbItem(t("Application", "برنامه"))),
            type = BreadcrumbTheme.Type.Bordered
        )
    }

    @Test
    fun button() = snapshot("button") {
        Wrap {
            Button(placeholder = t("Save", "ذخیره"), color = ButtonTheme.Color.Blue) {}
            Button(placeholder = t("Next", "بعدی"), style = ButtonTheme.Style.Outlined, trailingIcon = painterResource(R.drawable.ic_chevron_forward)) {}
            Button(placeholder = t("Inbox", "صندوق"), style = ButtonTheme.Style.Soft, color = ButtonTheme.Color.Blue, badge = 3) {}
            ButtonIcon(icon = painterResource(R.drawable.ic_star), contentDescription = "Notifications", type = ButtonIconTheme.Type.Outlined) {}
        }
    }

    @Test
    fun buttonGroup() = snapshot("button_group") {
        val labels = listOf(t("Years", "سال"), t("Month", "ماه"), t("Date", "روز"))
        listOf(ButtonGroupTheme.Type.Solid, ButtonGroupTheme.Type.Outline, ButtonGroupTheme.Type.Soft).forEach { type ->
            ButtonGroup(items = labels.map { ButtonGroupItem(placeholder = it) {} }, type = type, size = ButtonGroupTheme.Size.Small)
        }
        ButtonGroup(
            items = labels.mapIndexed { index, label ->
                ButtonGroupItem(placeholder = label, state = if (index == 1) ButtonGroupTheme.State.Hover else ButtonGroupTheme.State.Default) {}
            },
            shape = ButtonGroupTheme.Shape.Pilled
        )
        ButtonGroup(
            items = listOf(
                ButtonGroupItem(placeholder = t("Item 1", "مورد ۱"), leadingIcon = painterResource(R.drawable.ic_user)) {},
                ButtonGroupItem(placeholder = t("Item 2", "مورد ۲"), state = ButtonGroupTheme.State.Disabled) {},
            ),
            direction = ButtonGroupTheme.Direction.Vertical
        )
    }

    @Test
    fun card() = snapshot("card") {
        Card(footer = { CardCaption(t("Last updated 5 mins ago", "آخرین به‌روزرسانی ۵ دقیقه پیش")) }) {
            CardTitle(t("Card title", "عنوان کارت"))
            CardText(t("Some quick example text.", "یک متن نمونه کوتاه."))
            CardLink(t("Card link", "پیوند کارت")) {}
        }
    }

    @Test
    fun chatBubble() = snapshot("chat_bubble") {
        ChatBubble(
            items = listOf(
                ChatBubbleItem(t("How can we help?", "چطور می‌توانیم کمک کنیم؟"), avatarPlaceholder = t("Support", "پشتیبانی")),
                ChatBubbleItem(t("What's Preline UI?", "پرلاین چیست؟"), isReply = true, status = ChatBubbleTheme.ItemStatus.Sent),
            )
        )
    }

    @Test
    fun selection() = snapshot("selection") {
        Checkbox(label = t("Remember me", "مرا به خاطر بسپار"), checked = true)
        Radio(label = t("Monthly", "ماهانه"), checked = true)
        Radio(label = t("Yearly", "سالانه"), checked = false)
        Switch(checked = true, onCheckedChange = {}, onLabel = t("On", "روشن"))
    }

    @Test
    fun input() = snapshot("input") {
        Input(value = "", onValueChange = {}, label = t("Email", "ایمیل"), placeholder = "you@site.com")
        Input(
            value = "hello@site", onValueChange = {}, label = t("Email", "ایمیل"),
            feedbackMessage = t("Please enter a valid email address.", "لطفاً یک ایمیل معتبر وارد کنید."),
            validationState = InputTheme.ValidationState.Invalid
        )
    }

    @Test
    fun legend() = snapshot("legend") {
        listOf(LegendTheme.Type.Circled, LegendTheme.Type.Solid, LegendTheme.Type.Striped).forEach { type ->
            Wrap {
                Legend(label = t("Dark", "تیره"), type = type, color = LegendTheme.Color.Dark)
                Legend(label = t("Green", "سبز"), type = type, color = LegendTheme.Color.Green)
                Legend(label = t("Red", "قرمز"), type = type, color = LegendTheme.Color.Red)
            }
        }
    }

    @Test
    fun list() = snapshot("list") {
        List(items = listOf(ListItem(t("Profile", "نمایه")), ListItem(t("Settings", "تنظیمات")), ListItem(t("Team", "تیم"))), type = ListTheme.Type.Decimal)
        List(items = listOf(ListItem("FAQ", featuredIcon = painterResource(R.drawable.ic_check))), style = ListTheme.Style.Soft, color = ListTheme.Color.Blue)
    }

    @Test
    fun listGroup() = snapshot("list_group") {
        val items = listOf(
            ListGroupItem(t("Profile", "پروفایل"), icon = painterResource(R.drawable.ic_user)),
            ListGroupItem(t("Active", "فعال"), state = ListGroupTheme.State.Active),
            ListGroupItem(t("Hover", "شناور"), state = ListGroupTheme.State.Hover),
            ListGroupItem(t("Disabled", "غیرفعال"), state = ListGroupTheme.State.Disabled, badge = { Badge(placeholder = "5", size = BadgeTheme.Size.Small) }),
        )
        ListGroup(items = items, type = ListGroupTheme.Type.Flush)
        ListGroup(items = items, type = ListGroupTheme.Type.StripedBordered, badgePosition = ListGroupTheme.BadgePosition.End)
        ListGroup(items = items.take(3), direction = ListGroupTheme.Direction.Horizontal)
    }

    @Test
    fun pagination() = snapshot("pagination") {
        Pagination(
            totalPageCount = 5, currentPage = 2,
            nextIcon = painterResource(R.drawable.ic_chevron_forward)
        ) {}
    }

    @Test
    fun progress() = snapshot("progress") {
        Progress(title = t("Progress title", "عنوان پیشرفت"), value = 50, type = ProgressTheme.Type.TitleLabel)
        Progress(value = 70, type = ProgressTheme.Type.TopFloatingLabel)
        Progress(value = 50, type = ProgressTheme.Type.Steps, steps = 4)
        ActivityIndicator(value = 76, title = t("Content quality score", "امتیاز کیفیت محتوا"), change = "3.4%")
        Wrap {
            SignalBars(signal = ProgressTheme.Signal.Good, label = t("Good", "خوب"))
            SignalBars(signal = ProgressTheme.Signal.Bad, label = t("Bad", "بد"))
        }
    }

    @Test
    fun rating() = snapshot("rating") {
        Rating(value = 3) {}
        EmojiRating(selectedIndex = 1) {}
        ThumbRating(helpful = true, yesLabel = t("Yes", "بله"), noLabel = t("No", "خیر")) {}
    }

    @Test
    fun stepper() = snapshot("stepper") {
        Stepper(
            items = listOf(
                StepperItem(1, t("Cart", "سبد خرید"), type = StepperTheme.ItemType.Success),
                StepperItem(2, t("Payment", "پرداخت"), type = StepperTheme.ItemType.ActiveNumber),
                StepperItem(3, t("Delivery", "تحویل")),
            ),
            alignment = StepperTheme.ItemAlignment.Start,
            direction = StepperTheme.ItemDirection.Horizontal
        )
    }

    @Test
    fun styledIcon() = snapshot("styled_icon") {
        Wrap {
            listOf(StyledIconTheme.Type.Solid, StyledIconTheme.Type.Soft, StyledIconTheme.Type.SoftOutlined, StyledIconTheme.Type.Outlined).forEach {
                StyledIcon(icon = painterResource(R.drawable.ic_check), type = it, color = StyledIconTheme.Color.Blue, shape = StyledIconTheme.Shape.Circular)
            }
        }
    }

    @Test
    fun tab() = snapshot("tab") {
        val items = listOf(TabItem(0, t("Home", "خانه")), TabItem(1, t("Inbox", "صندوق"), badge = 3), TabItem(2, t("Settings", "تنظیمات")))
        Tab(items = items, selectedIndex = 0, onIndexChanged = {}, type = TabTheme.Type.Bordered)
        Tab(items = items, selectedIndex = 1, onIndexChanged = {}, type = TabTheme.Type.Segment)
    }

    @Test
    fun timeline() = snapshot("timeline") {
        Timeline(
            items = listOf(
                TimelineItem(
                    heading = t("1 Aug, 2023", "۱ مرداد ۱۴۰۲"),
                    title = t("Created a task", "وظیفه‌ای ساخته شد"),
                    description = t("Find more detailed instructions here.", "دستورالعمل‌های دقیق‌تر را اینجا ببینید."),
                    author = TimelineAuthor(t("James Collins", "جیمز کالینز"), painterResource(R.drawable.ic_user))
                ),
                TimelineItem(marker = TimelineTheme.Marker.Initial("A"), title = t("Release v5.2.0", "انتشار نسخه 5.2.0"), highlighted = true),
                TimelineItem(marker = TimelineTheme.Marker.Number(3), title = t("Take a break", "کمی استراحت")),
            )
        )
        Timeline(
            items = listOf(TimelineItem(time = "12:05PM", title = t("Untitled task", "وظیفه بی‌عنوان"), marker = TimelineTheme.Marker.Ring)),
            timePosition = TimelineTheme.TimePosition.Start
        )
    }

    @Test
    fun treeView() = snapshot("tree_view") {
        val nodes = listOf(
            TreeNode(
                "assets", t("assets", "دارایی‌ها"), children = listOf(
                    TreeNode("css", "css", children = listOf(TreeNode("main.css", "main.css"), TreeNode("readme", "README.txt", enabled = false))),
                    TreeNode("img", "img", children = listOf(TreeNode("logo", "logo.svg"))),
                )
            ),
            TreeNode("scripts", t("scripts", "اسکریپت‌ها"), children = listOf(TreeNode("build", "build.sh"))),
        )
        TreeView(nodes = nodes, expanded = setOf("assets", "css"), onExpandedChange = {}, selected = "css")
        TreeView(nodes = nodes, expanded = setOf("assets", "css"), onExpandedChange = {}, checked = setOf("css", "main.css"))
    }

    @Test
    fun table() = snapshot("table") {
        val rows = listOf(t("John Brown", "جان براون") to 45, t("Jim Green", "جیم گرین") to 27, t("Joe Black", "جو بلک") to 31)
        val columns = listOf(
            TableColumn<Pair<String, Int>>(t("Name", "نام")) { TableText(it.first) },
            TableColumn(t("Age", "سن"), highlight = { if (it.second == 27) TableTheme.Highlight.Yellow else null }) { TableText(it.second.toString()) },
        )
        Table(rows = rows, columns = columns, striped = true)
        Table(rows = rows, columns = columns, border = TableTheme.Border.Rounded, headerBackground = true, headerDividers = true, selected = setOf(1), onSelectionChange = {})
    }

    @Test
    fun inputGroup() = snapshot("input_group") {
        InputGroup(
            segments = listOf(
                InputGroupTheme.Segment.AddOn(t("Full name", "نام کامل")),
                InputGroupTheme.Segment.Field("", {}, t("First", "نام")),
                InputGroupTheme.Segment.Field(t("Smith", "اسمیت"), {}),
            )
        )
        InputGroup(
            segments = listOf(
                InputGroupTheme.Segment.Field("", {}, t("Search", "جستجو"), painterResource(R.drawable.ic_info)),
                InputGroupTheme.Segment.Button(t("Button", "دکمه"), style = InputGroupTheme.ButtonStyle.White) {},
                InputGroupTheme.Segment.Button(t("Button", "دکمه")) {},
            ),
            size = InputGroupTheme.Size.Small
        )
    }

    @Test
    fun numberInput() = snapshot("number_input") {
        NumberInput(value = 1, onValueChange = {})
        NumberInput(value = 1, onValueChange = {}, type = NumberInputTheme.Type.VerticalButtons, label = t("Select quantity", "انتخاب تعداد"))
        NumberInput(value = 1, onValueChange = {}, type = NumberInputTheme.Type.HorizontalButtons)
        NumberInput(value = 0, onValueChange = {}, type = NumberInputTheme.Type.Mini)
        NumberInput(value = 7, onValueChange = {}, invalid = true, feedbackMessage = t("Out of limit", "خارج از محدوده"))
    }

    @Test
    fun pinInput() = snapshot("pin_input") {
        PinInput(value = "12", onValueChange = {})
        PinInput(value = "1", onValueChange = {}, shape = PinInputTheme.Shape.Underline, size = PinInputTheme.Size.Small)
        PinInput(value = "", onValueChange = {}, shape = PinInputTheme.Shape.Light, placeholder = "○")
        PinInput(value = "123", onValueChange = {}, shape = PinInputTheme.Shape.Circle, aspectRatio = 0.75f, mask = true)
        PinInput(value = "1235", onValueChange = {}, invalid = true)
    }
}
