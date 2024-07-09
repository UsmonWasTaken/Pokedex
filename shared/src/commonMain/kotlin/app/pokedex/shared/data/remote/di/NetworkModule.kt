package app.pokedex.shared.data.remote.di

import app.pokedex.shared.data.remote.api.PokedexApi
import app.pokedex.shared.data.remote.createHttpClient
import app.pokedex.shared.data.remote.mapper.PokemonInfoResponseMapper
import app.pokedex.shared.data.remote.mapper.PokemonResponseMapper
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal fun networkModule(debuggable: Boolean) = module {
    single { createHttpClient(debuggable) }
    singleOf(::PokedexApi)
    factoryOf(::PokemonResponseMapper)
    factoryOf(::PokemonInfoResponseMapper)
}