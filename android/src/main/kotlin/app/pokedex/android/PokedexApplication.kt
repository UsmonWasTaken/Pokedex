package app.pokedex.android

import android.app.Application
import android.content.pm.ApplicationInfo
import app.pokedex.shared.di.initKoin
import org.koin.android.ext.koin.androidContext

class PokedexApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin(
            debuggable = isDebuggableApp,
            declaration = {
                androidContext(applicationContext)
            }
        )
    }
}

private val Application.isDebuggableApp: Boolean
    inline get() = (applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE) != 0