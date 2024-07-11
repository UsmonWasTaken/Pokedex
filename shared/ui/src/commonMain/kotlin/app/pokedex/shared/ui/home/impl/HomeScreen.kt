package app.pokedex.shared.ui.home.impl

import androidx.compose.runtime.Composable
import app.pokedex.shared.domain.model.Video
import app.pokedex.shared.ui.home.api.HomeScreenFactory
import app.pokedex.shared.ui.notimplemented.api.NotImplementedScreenFactory
import app.pokedex.shared.ui.pokemons.api.PokemonsScreenFactory
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.compose.koinInject

internal fun HomeScreenFactory() = HomeScreenFactory(::HomeScreen)

internal class HomeScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val pokemonsScreenFactory = koinInject<PokemonsScreenFactory>()
        val notImplementedScreenFactory = koinInject<NotImplementedScreenFactory>()

        HomeContent(
            onIntent = { intent ->
                when (intent) {
                    Intent.NavigateToPokemons -> navigator.push(pokemonsScreenFactory.create())
                    Intent.NavigateToFavorites,
                    Intent.NavigateToAbout,
                    Intent.NavigateToSearch,
                    Intent.NavigateToEvolution,
                    Intent.NavigateToLocations,
                    Intent.NavigateToMoves,
                    is Intent.PlayVideo -> navigator.push(notImplementedScreenFactory.create())
                }
            },
        )
    }

    sealed interface Intent {
        data object NavigateToFavorites : Intent
        data object NavigateToAbout : Intent
        data object NavigateToSearch : Intent
        data object NavigateToPokemons : Intent
        data object NavigateToMoves : Intent
        data object NavigateToEvolution : Intent
        data object NavigateToLocations : Intent
        data class PlayVideo(val video: Video) : Intent
    }
}
