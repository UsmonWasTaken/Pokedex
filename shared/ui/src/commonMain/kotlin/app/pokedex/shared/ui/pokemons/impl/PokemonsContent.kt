package app.pokedex.shared.ui.pokemons.impl

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.itemKey
import app.cash.paging.compose.LazyPagingItems
import app.pokedex.shared.domain.model.Pokemon
import app.pokedex.shared.ui.pokemons.impl.PokemonsScreen.Intent
import app.pokedex.shared.ui.pokemons.impl.component.PokemonGridItem
import app.pokedex.shared.ui.pokemons.impl.component.PokemonsTopBar
import app.pokedex.shared.ui.util.items

@Composable
internal fun PokemonsContent(
    pokemons: LazyPagingItems<Pokemon>,
    onIntent: (Intent) -> Unit,
) {
    Scaffold(
        topBar = {
            PokemonsTopBar(onIntent = onIntent)
        },
    ) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 195.dp),
            contentPadding = PaddingValues(horizontal = 10.dp),
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            items(
                items = pokemons,
                key = pokemons.itemKey(Pokemon::number),
            ) { pokemon ->
                PokemonGridItem(
                    pokemon = pokemon,
                    onClick = { onIntent(Intent.NavigateToPokemonDetails(pokemon)) },
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}
