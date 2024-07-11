package app.pokedex.shared.ui.pokemons.impl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import app.cash.paging.compose.collectAsLazyPagingItems
import app.pokedex.shared.domain.model.Pokemon
import app.pokedex.shared.ui.pokemon_details.api.PokemonDetailsScreenFactory
import app.pokedex.shared.ui.pokemons.api.PokemonsScreenFactory
import app.pokedex.shared.ui.util.viewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.compose.koinInject

internal fun PokemonsScreenFactory() = PokemonsScreenFactory(::PokemonsScreen)

internal class PokemonsScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val pokemonDetailsScreenFactory = koinInject<PokemonDetailsScreenFactory>()

        val viewModel: PokemonsViewModel = viewModel()
        val pokemons = viewModel.pokemons.collectAsLazyPagingItems()

        PokemonsContent(
            pokemons = pokemons,
            onIntent = { intent ->
                when (intent) {
                    Intent.NavigateBack -> navigator.pop()
                    is Intent.NavigateToPokemonDetails -> {
                        navigator.push(pokemonDetailsScreenFactory.create(pokemon = intent.pokemon))
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

