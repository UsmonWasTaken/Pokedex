package app.pokedex.shared.ui.notimplemented.impl

import androidx.compose.runtime.Composable
import app.pokedex.shared.ui.notimplemented.api.NotImplementedScreenFactory
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

internal fun NotImplementedScreenFactory() = NotImplementedScreenFactory(::NotImplementedScreen)

internal class NotImplementedScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        NotImplementedContent(
            onIntent = { intent ->
                when (intent) {
                    Intent.NavigateBack -> navigator.pop()
                }
            },
        )
    }

    sealed interface Intent {
        data object NavigateBack : Intent
    }
}
