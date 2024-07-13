package app.pokedex.shared.ui.notimplemented.impl

import androidx.compose.runtime.Composable
import app.pokedex.shared.ui.notimplemented.api.NotImplementedScreenFactory
import app.pokedex.shared.ui.util.safePop
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

internal fun NotImplementedScreenFactory() = NotImplementedScreenFactory(::NotImplementedScreen)

internal class NotImplementedScreen : Screen {

    override val key: ScreenKey = "NotImplementedScreen"

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        NotImplementedContent(
            onIntent = { intent ->
                when (intent) {
                    Intent.NavigateBack -> navigator.safePop()
                }
            },
        )
    }

    sealed interface Intent {
        data object NavigateBack : Intent
    }
}
