package app.pokedex.shared

import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import app.pokedex.shared.RootComponent.Child
import app.pokedex.shared.comingsoon.ComingSoonScreen
import app.pokedex.shared.details.DetailsScreen
import app.pokedex.shared.favorite.FavoriteScreen
import app.pokedex.shared.main.MainScreen
import app.pokedex.shared.pokedex.PokedexScreen
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation

@Composable
fun ContentView(component: RootComponent) {
    Children(
        stack = component.childStack,
        animation = stackAnimation(
            animator = slide(
                animationSpec = tween(durationMillis = 200)
            )
        ),
    ) {
        when (val child = it.instance) {
            is Child.Main -> MainScreen(child.component)
            is Child.Pokedex -> PokedexScreen(child.component)
            is Child.Favorite -> FavoriteScreen(child.component)
            is Child.Details -> DetailsScreen(child.component)
            is Child.ComingSoon -> ComingSoonScreen(child.component)
        }
    }
}