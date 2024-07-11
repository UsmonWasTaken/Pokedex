package app.pokedex.shared.ui

import androidx.compose.runtime.Composable
import app.pokedex.shared.ui.home.api.HomeScreenFactory
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import org.koin.compose.koinInject

@Composable
fun ContentView() {
    val homeScreenFactory = koinInject<HomeScreenFactory>()
    Navigator(screen = homeScreenFactory.create()) { navigator ->
        SlideTransition(navigator)
    }
}