package app.pokedex.desktop

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import app.pokedex.shared.ContentView
import app.pokedex.shared.Res
import app.pokedex.shared.RootComponent
import app.pokedex.shared.app_name
import app.pokedex.shared.di.initKoin
import app.pokedex.shared.theme.PokedexTheme
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.resume
import com.arkivanov.mvikotlin.core.utils.setMainThreadId
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import org.jetbrains.compose.resources.stringResource
import javax.swing.SwingUtilities

fun main(args: Array<String>) {
    initKoin(debuggable = true)

    val rootComponent = invokeOnAwtSync {
        setMainThreadId(Thread.currentThread().id)

        val lifecycle = LifecycleRegistry()

        RootComponent(
            componentContext = DefaultComponentContext(lifecycle = lifecycle),
            storeFactory = DefaultStoreFactory(),
        ).also {
            lifecycle.resume()
        }
    }

    application {
        Window(
            title = stringResource(Res.string.app_name),
            onCloseRequest = ::exitApplication
        ) {
            PokedexTheme {
                ContentView(component = rootComponent)
            }
        }
    }
}

fun <T> invokeOnAwtSync(block: () -> T): T {
    var result: T? = null
    SwingUtilities.invokeAndWait {
        result = block()
    }
    @Suppress("UNCHECKED_CAST")
    return result as T
}