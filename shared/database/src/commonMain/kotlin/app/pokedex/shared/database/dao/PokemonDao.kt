package app.pokedex.shared.database.dao

import app.cash.paging.PagingSource
import app.cash.sqldelight.paging3.QueryPagingSource
import app.pokedex.shared.common.coroutines.PokedexDispatchers
import app.pokedex.shared.database.PokedexDatabase
import app.pokedex.shared.database.PokemonEntity
import app.pokedex.shared.database.PokemonRemoteKeyEntity
import kotlinx.coroutines.withContext

class PokemonDao(
    database: PokedexDatabase,
    private val dispatchers: PokedexDispatchers,
) {
    private val pokemonQueries = database.pokemonEntityQueries
    private val remoteKeyQueries = database.pokemonRemoteKeyEntityQueries

    suspend fun insertPokemonsWithRemoteKeys(
        pokemons: List<PokemonEntity>,
        remoteKeys: List<PokemonRemoteKeyEntity>,
    ) = withContext(dispatchers.io) {
        pokemonQueries.transaction {
            pokemons.forEach(pokemonQueries::upsertPokemon)
            remoteKeys.forEach(remoteKeyQueries::upsertRemoteKey)
        }
    }

    suspend fun clearAndInsertPokemonsWithRemoteKeys(
        pokemons: List<PokemonEntity>,
        remoteKeys: List<PokemonRemoteKeyEntity>,
    ) = withContext(dispatchers.io) {
        pokemonQueries.transaction {
            pokemonQueries.clearPokemons()
            remoteKeyQueries.clearRemoteKeys()
            pokemons.forEach(pokemonQueries::upsertPokemon)
            remoteKeys.forEach(remoteKeyQueries::upsertRemoteKey)
        }
    }

    suspend fun getRemoteKey(id: Int): PokemonRemoteKeyEntity? = withContext(dispatchers.io) {
        remoteKeyQueries.getRemoteKey(id = id).executeAsOneOrNull()
    }

    fun getPokemons(): PagingSource<Int, PokemonEntity> = QueryPagingSource(
        countQuery = pokemonQueries.getPokemonsCount(),
        transacter = pokemonQueries,
        context = dispatchers.io,
        queryProvider = pokemonQueries::getPokemons,
    )
}