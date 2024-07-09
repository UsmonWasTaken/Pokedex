package app.pokedex.shared.di

import app.pokedex.shared.util.PokedexDispatchers
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val coroutineDispatcherModule = module {
    singleOf(::PokedexDispatchers)
}