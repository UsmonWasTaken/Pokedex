package app.pokedex.shared.di

import app.pokedex.shared.data.local.di.databaseModule
import app.pokedex.shared.data.remote.di.networkModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(
    debuggable: Boolean,
    declaration: KoinAppDeclaration? = null,
) {
    startKoin {
        declaration?.invoke(this)
        modules(
            coroutineDispatcherModule,
            databaseModule,
            networkModule(debuggable),
        )
    }
}