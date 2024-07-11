package app.pokedex.shared.ui.pokemons.api

import cafe.adriel.voyager.core.screen.Screen

fun interface PokemonsScreenFactory {
    fun create(): Screen
}
