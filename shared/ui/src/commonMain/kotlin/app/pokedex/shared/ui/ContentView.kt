package app.pokedex.shared.ui

import androidx.compose.runtime.Composable
import app.pokedex.shared.ui.home.api.HomeScreenFactory
import app.pokedex.shared.ui.util.SetupCoilImageLoader
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import org.koin.compose.koinInject

@Composable
fun ContentView() {
    SetupCoilImageLoader()

    val homeScreen = koinInject<HomeScreenFactory>()
    Navigator(
        screen = homeScreen.create(),
    ) { navigator ->
        SlideTransition(
            navigator = navigator,
            disposeScreenAfterTransitionEnd = true
        )
    }
}
