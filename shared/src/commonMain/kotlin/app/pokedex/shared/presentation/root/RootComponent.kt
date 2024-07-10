package app.pokedex.shared.presentation.root

import androidx.compose.runtime.Stable
import app.cash.paging.PagingData
import app.pokedex.shared.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

@Stable
interface RootComponent {
    val pokemons: Flow<PagingData<Pokemon>>
}
