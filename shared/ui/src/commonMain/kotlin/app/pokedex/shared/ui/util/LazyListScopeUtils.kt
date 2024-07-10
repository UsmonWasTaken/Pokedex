package app.pokedex.shared.ui.util

import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems

fun <T : Any> LazyListScope.items(
    items: LazyPagingItems<T>,
    key: ((index: Int) -> Any)? = null,
    contentType: (index: Int) -> Any? = { null },
    itemContent: @Composable LazyItemScope.(item: T) -> Unit
) {
    items(count = items.itemCount, key, contentType) {
        val item = requireNotNull(items[it])
        itemContent(item)
    }
}
