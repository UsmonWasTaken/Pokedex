package app.pokedex.shared.ui.root

import androidx.compose.runtime.Stable
import androidx.paging.cachedIn
import app.pokedex.shared.domain.repository.PokemonRepository
import app.pokedex.shared.common.coroutines.PokedexDispatchers
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.store.StoreFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@Stable
fun RootComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory
): RootComponent = DefaultRootComponent(componentContext, storeFactory)

@Stable
internal class DefaultRootComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
) : ComponentContext by componentContext, RootComponent, KoinComponent {

    private val repository: PokemonRepository by inject()
    private val dispatchers: PokedexDispatchers by inject()
    private val componentScope = CoroutineScope(SupervisorJob() + dispatchers.default)

    override val pokemons = repository
        .getPokemons()
        .cachedIn(componentScope)
}