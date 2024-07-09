package app.pokedex.desktop

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import app.pokedex.shared.AppContent
import app.pokedex.shared.Res
import app.pokedex.shared.app_name
import org.jetbrains.compose.resources.stringResource

fun main() {
    application {
        Window(onCloseRequest = ::exitApplication) {
            window.title = stringResource(Res.string.app_name)
            AppContent()
        }
    }
}