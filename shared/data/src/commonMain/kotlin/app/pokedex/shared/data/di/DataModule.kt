package app.pokedex.shared.data.di

import app.pokedex.shared.data.mapper.PokemonInfoResponseMapper
import app.pokedex.shared.data.mapper.PokemonResponseMapper
import app.pokedex.shared.data.mediator.PokemonRemoteMediator
import app.pokedex.shared.data.repository.DefaultPokemonRepository
import app.pokedex.shared.domain.repository.PokemonRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val DataModule = module {
    factoryOf(::PokemonRemoteMediator)
    singleOf(::DefaultPokemonRepository).bind<PokemonRepository>()

    factoryOf(::PokemonResponseMapper)
    factoryOf(::PokemonInfoResponseMapper)

}