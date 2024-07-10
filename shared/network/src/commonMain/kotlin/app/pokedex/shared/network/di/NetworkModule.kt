@file:Suppress("FunctionName")

package app.pokedex.shared.network.di

import app.pokedex.shared.network.api.PokedexApi
import app.pokedex.shared.network.createHttpClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun NetworkModule(debuggable: Boolean) = module {
    single { createHttpClient(debuggable) }
    singleOf(::PokedexApi)
}