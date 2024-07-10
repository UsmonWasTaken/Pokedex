package app.pokedex.shared.data.di

import app.pokedex.shared.common.di.CoroutineDispatcherModule
import app.pokedex.shared.database.di.DatabaseModule
import app.pokedex.shared.network.di.NetworkModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(
    debuggable: Boolean,
    declaration: KoinAppDeclaration? = null,
) {
    startKoin {
        declaration?.invoke(this)
        modules(
            CoroutineDispatcherModule,
            DatabaseModule,
            NetworkModule(debuggable),
            DataModule,
        )
    }
}