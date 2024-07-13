package app.pokedex.shared.ui.pokemons.impl

import androidx.compose.runtime.Composable
import app.cash.paging.compose.collectAsLazyPagingItems
import app.pokedex.shared.domain.model.Pokemon
import app.pokedex.shared.ui.pokemon_details.api.PokemonDetailsScreenFactory
import app.pokedex.shared.ui.pokemons.api.PokemonsScreenFactory
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.compose.koinInject

internal fun PokemonsScreenFactory() = PokemonsScreenFactory(::PokemonsScreen)

internal class PokemonsScreen : Screen {

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
                    Intent.NavigateBack -> navigator.pop()
                    is Intent.NavigateToPokemonDetails -> {
                        navigator.push(pokemonDetailsScreen.create(pokemon = intent.pokemon))
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

