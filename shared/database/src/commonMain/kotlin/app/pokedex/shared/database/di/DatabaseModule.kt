package app.pokedex.shared.database.di

import app.cash.sqldelight.adapter.primitive.IntColumnAdapter
import app.pokedex.shared.database.PokedexDatabase
import app.pokedex.shared.database.PokemonEntity
import app.pokedex.shared.database.PokemonInfoEntity
import app.pokedex.shared.database.PokemonRemoteKeyEntity
import app.pokedex.shared.database.adapter.StatsColumnAdapter
import app.pokedex.shared.database.adapter.TypesColumnAdapter
import app.pokedex.shared.database.createSqlDriver
import app.pokedex.shared.database.dao.PokemonDao
import app.pokedex.shared.database.dao.PokemonInfoDao
import app.pokedex.shared.database.mapper.PokemonEntityMapper
import app.pokedex.shared.database.mapper.PokemonInfoEntityMapper
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val DatabaseModule = module {
    single {
        PokedexDatabase(
            driver = createSqlDriver(),
            pokemonEntityAdapter = PokemonEntity.Adapter(
                idAdapter = IntColumnAdapter,
            ),
            pokemonInfoEntityAdapter = PokemonInfoEntity.Adapter(
                idAdapter = IntColumnAdapter,
                heightAdapter = IntColumnAdapter,
                weightAdapter = IntColumnAdapter,
                experienceAdapter = IntColumnAdapter,
                typesAdapter = TypesColumnAdapter,
                statsAdapter = StatsColumnAdapter,
            ),
            pokemonRemoteKeyEntityAdapter = PokemonRemoteKeyEntity.Adapter(
                idAdapter = IntColumnAdapter,
                previousPageAdapter = IntColumnAdapter,
                nextPageAdapter = IntColumnAdapter,
            ),
        )
    }

    singleOf(::PokemonDao)
    singleOf(::PokemonInfoDao)

    factoryOf(::PokemonEntityMapper)
    factoryOf(::PokemonInfoEntityMapper)
}