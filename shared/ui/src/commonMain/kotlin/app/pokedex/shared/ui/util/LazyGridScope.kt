package app.pokedex.shared.ui.util

import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridItemSpanScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems

inline fun <T : Any> LazyGridScope.items(
    items: LazyPagingItems<T>,
    noinline key: ((index: Int) -> Any)? = null,
    noinline span: (LazyGridItemSpanScope.(index: Int) -> GridItemSpan)? = null,
    noinline contentType: (index: Int) -> Any? = { null },
    crossinline itemContent: @Composable LazyGridItemScope.(item: T) -> Unit
) {
    items(count = items.itemCount, key, span, contentType) {
        val item = requireNotNull(items[it])
        itemContent(item)
    }
}
