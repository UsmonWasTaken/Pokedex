package app.pokedex.shared.ui.pokemons.impl

import androidx.compose.runtime.Composable
import app.cash.paging.compose.collectAsLazyPagingItems
import app.pokedex.shared.domain.model.Pokemon
import app.pokedex.shared.ui.pokemon_details.api.PokemonDetailsScreenFactory
import app.pokedex.shared.ui.pokemons.api.PokemonsScreenFactory
import app.pokedex.shared.ui.util.safePop
import app.pokedex.shared.ui.util.safePush
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.compose.koinInject

internal fun PokemonsScreenFactory() = PokemonsScreenFactory(::PokemonsScreen)

internal class PokemonsScreen : Screen {

    override val key: ScreenKey = "PokemonsScreen"

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val pokemonDetailsScreen = koinInject<PokemonDetailsScreenFactory>()

        val screenModel: PokemonsScreenModel = koinScreenModel()
        val pokemons = screenModel.pokemons.collectAsLazyPagingItems()

        PokemonsContent(
            pokemons = pokemons,
            onIntent = { intent ->
                when (intent) {
                    Intent.NavigateBack -> navigator.safePop()
                    is Intent.NavigateToPokemonDetails -> {
                        navigator.safePush(pokemonDetailsScreen.create(pokemon = intent.pokemon))
                    }
                }
            }
        )
    }

    sealed interface Intent {
        data object NavigateBack : Intent
        data class NavigateToPokemonDetails(val pokemon: Pokemon) : Intent
    }
}

