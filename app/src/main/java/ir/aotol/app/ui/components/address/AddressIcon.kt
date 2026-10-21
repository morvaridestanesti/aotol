package ir.aotol.app.ui.components.address

import androidx.annotation.DrawableRes
import ir.aotol.app.R

enum class AddressIcon(val key: String, @DrawableRes val res: Int) {
    Home("ic_home", R.drawable.ic_home),
    Work("ic_work", R.drawable.ic_eye),
    Shop("ic_shop", R.drawable.ic_car),
    Location("ic_location", R.drawable.ic_location);

    companion object {
        fun fromKey(key: String?): AddressIcon =
            entries.firstOrNull { it.key == key } ?: Location
    }
}
