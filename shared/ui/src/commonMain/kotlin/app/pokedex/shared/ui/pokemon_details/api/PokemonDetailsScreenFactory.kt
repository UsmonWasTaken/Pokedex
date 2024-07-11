package app.pokedex.shared.ui.pokemon_details.api

import app.pokedex.shared.domain.model.Pokemon
import cafe.adriel.voyager.core.screen.Screen

fun interface PokemonDetailsScreenFactory {
    fun create(pokemon: Pokemon): Screen
}