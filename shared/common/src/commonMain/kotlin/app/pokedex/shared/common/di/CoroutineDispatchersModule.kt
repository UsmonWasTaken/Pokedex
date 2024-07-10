package app.pokedex.shared.common.di

import app.pokedex.shared.common.coroutines.PokedexDispatchers
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val CoroutineDispatcherModule = module {
    singleOf(::PokedexDispatchers)
}