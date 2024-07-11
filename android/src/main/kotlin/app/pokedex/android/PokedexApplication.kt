package app.pokedex.android

import android.app.Application
import android.content.pm.ApplicationInfo
import app.pokedex.shared.data.di.initKoin
import app.pokedex.shared.ui.di.uiModules
import org.koin.android.ext.koin.androidContext

class PokedexApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin(
            debuggable = isDebuggableApp,
            declaration = {
                androidContext(applicationContext)
                uiModules()
            }
        )
    }
}

private val Application.isDebuggableApp: Boolean
    inline get() = (applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE) != 0