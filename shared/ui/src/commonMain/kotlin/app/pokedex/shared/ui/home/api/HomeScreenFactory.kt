package app.pokedex.shared.ui.home.api

import cafe.adriel.voyager.core.screen.Screen

fun interface HomeScreenFactory {
    fun create(): Screen
}