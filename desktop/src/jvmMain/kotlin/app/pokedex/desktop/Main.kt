package app.pokedex.desktop

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import app.pokedex.shared.data.di.initKoin
import app.pokedex.shared.ui.ContentView
import app.pokedex.shared.ui.Res
import app.pokedex.shared.ui.app_name
import app.pokedex.shared.ui.di.uiModules
import app.pokedex.shared.ui.theme.PokedexTheme
import org.jetbrains.compose.resources.stringResource

fun main(args: Array<String>) {
    initKoin(
        debuggable = true,
        declaration = {
            uiModules()
        }
    )

    application {
        Window(
            title = stringResource(Res.string.app_name),
            onCloseRequest = ::exitApplication
        ) {
            PokedexTheme {
                ContentView()
            }
        }
    }
}
