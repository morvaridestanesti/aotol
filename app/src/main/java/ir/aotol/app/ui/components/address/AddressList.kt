package ir.aotol.app.ui.components.address

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ir.aotol.app.retrofit.models.Address
import ir.aotol.app.ui.components.preline.AotolTheme

@Composable
fun AddressList(
    addresses: List<Address>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = addresses,
            key = { it.id }
        ) { AddressItem(it) }
    }
}

@Preview(showBackground = true, apiLevel = 36)
@Composable
private fun Preview() = AotolTheme {
    AddressList(
        addresses = listOf(
            Address(
                id = 1,
                name = "خانه",
                icon = "ic_home",
                address = "جنت آباد",
                lat = 42.0,
                lng = 100.0,
                sort = 1,
                isDefault = true,
            ),
            Address(
                id = 2,
                name = "محل کار",
                icon = "ic_work",
                address = "جوانمرد",
                lat = 35.0,
                lng = 51.0,
                sort = 2,
                isDefault = false,
            )
        ),
    )
}