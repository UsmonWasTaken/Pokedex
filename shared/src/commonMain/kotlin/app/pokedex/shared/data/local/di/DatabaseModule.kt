package app.pokedex.shared.data.local.di

import app.pokedex.shared.data.local.createSqlDriver
import app.pokedex.shared.data.local.dao.PokemonDao
import app.pokedex.shared.data.local.dao.PokemonInfoDao
import app.pokedex.shared.data.local.mapper.PokemonEntityMapper
import app.pokedex.shared.data.local.mapper.PokemonInfoEntityMapper
import app.pokedex.shared.database.PokedexDatabase
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val databaseModule = module {
    single {
        PokedexDatabase(driver = createSqlDriver())
    }
    factoryOf(PokedexDatabase::pokemonEntityQueries)
    factoryOf(PokedexDatabase::pokemonInfoEntityQueries)

    singleOf(::PokemonDao)
    singleOf(::PokemonInfoDao)

    factoryOf(::PokemonEntityMapper)
    factoryOf(::PokemonInfoEntityMapper)
}