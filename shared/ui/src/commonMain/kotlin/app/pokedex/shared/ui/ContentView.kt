package app.pokedex.shared.ui

import androidx.compose.runtime.Composable
import app.pokedex.shared.ui.home.api.HomeScreenFactory
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.request.CachePolicy
import coil3.request.crossfade
import coil3.util.DebugLogger
import org.koin.compose.koinInject

@Composable
fun ContentView() {
    SetupCoil()

    val homeScreenFactory = koinInject<HomeScreenFactory>()
    Navigator(
        screen = homeScreenFactory.create(),

    ) { navigator ->
        SlideTransition(navigator)
    }
}

@Composable
internal fun SetupCoil() {
    setSingletonImageLoaderFactory { context ->
        ImageLoader.Builder(context)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .crossfade(true)
            .logger(DebugLogger())
            .build()
    }
}