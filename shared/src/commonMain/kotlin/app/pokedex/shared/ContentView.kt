package app.pokedex.shared

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.itemKey
import app.cash.paging.compose.collectAsLazyPagingItems
import app.pokedex.shared.domain.model.Pokemon
import app.pokedex.shared.presentation.root.RootComponent
import app.pokedex.shared.util.items

@Composable
fun ContentView(component: RootComponent) {
    val pagingItems = component.pokemons.collectAsLazyPagingItems()

    Scaffold {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(
                items = pagingItems,
                key = pagingItems.itemKey(Pokemon::name)
            ) { pokemon ->
                Text(
                    text = pokemon.name,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}