package app.pokedex.shared.ui.pokemon_details.impl

import androidx.compose.runtime.Composable
import app.pokedex.shared.domain.model.Pokemon
import app.pokedex.shared.ui.pokemon_details.api.PokemonDetailsScreenFactory
import cafe.adriel.voyager.core.screen.Screen

internal fun PokemonDetailsScreenFactory() = PokemonDetailsScreenFactory(::PokemonDetailsScreen)

internal class PokemonDetailsScreen(
    private val pokemon: Pokemon
) : Screen {

    @Composable
    override fun Content() {
        TODO("Not yet implemented")
    }
}