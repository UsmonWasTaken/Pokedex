package app.pokedex.shared.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(
    debuggable: Boolean,
    declaration: KoinAppDeclaration? = null,
) {
    startKoin {
        declaration?.invoke(this)
        modules()
    }
}