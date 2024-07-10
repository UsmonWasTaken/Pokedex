package app.pokedex.desktop

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import app.pokedex.shared.ContentView
import app.pokedex.shared.Res
import app.pokedex.shared.app_name
import app.pokedex.shared.di.initKoin
import app.pokedex.shared.presentation.root.RootComponent
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

    val rootComponent = runOnUiThread {
        @Suppress("DEPRECATION")
        setMainThreadId(Thread.currentThread().id)

        val lifecycle = LifecycleRegistry()
        RootComponent(
            componentContext = DefaultComponentContext(lifecycle),
            storeFactory = DefaultStoreFactory(),
        ).also { lifecycle.resume() }
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

private fun <T> runOnUiThread(block: () -> T): T {
    if (SwingUtilities.isEventDispatchThread()) {
        return block()
    }

    var error: Throwable? = null
    var result: T? = null

    SwingUtilities.invokeAndWait {
        try {
            result = block()
        } catch (e: Throwable) {
            error = e
        }
    }

    error?.let { throw it }

    return requireNotNull(result)
}