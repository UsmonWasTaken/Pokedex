package app.pokedex.shared.ui.pokemon_details.impl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import app.pokedex.shared.domain.model.Pokemon
import app.pokedex.shared.ui.pokemon_details.api.PokemonDetailsScreenFactory
import app.pokedex.shared.ui.pokemon_details.impl.PokemonDetailsScreen.Intent.NavigateBack
import app.pokedex.shared.ui.util.safePop
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.core.parameter.parametersOf

internal fun PokemonDetailsScreenFactory() = PokemonDetailsScreenFactory(::PokemonDetailsScreen)

internal class PokemonDetailsScreen(
    private val pokemon: Pokemon,
) : Screen {

    override val key: ScreenKey = "PokemonDetailsScreen"

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val screenModel: PokemonDetailsScreenModel = koinScreenModel { parametersOf(pokemon) }
        val state by screenModel.state.collectAsState()

        PokemonDetailsContent(
            state = state,
            onIntent = { intent ->
                when (intent) {
                    NavigateBack -> navigator.safePop()
                    else -> screenModel.onIntent(intent)
                }
            },
        )
    }

    sealed interface Intent {
        data object NavigateBack : Intent
        data object Favorite : Intent
        data object UnFavorite : Intent
    }
}