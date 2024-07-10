package app.pokedex.shared.database.dao

import app.cash.paging.PagingSource
import app.cash.sqldelight.paging3.QueryPagingSource
import app.pokedex.shared.common.coroutines.PokedexDispatchers
import app.pokedex.shared.database.PokemonEntity
import app.pokedex.shared.database.PokemonEntityQueries
import kotlinx.coroutines.withContext

class PokemonDao(
    private val queries: PokemonEntityQueries,
    private val dispatchers: PokedexDispatchers,
) {
    suspend fun upsertPokemon(pokemonEntities: List<PokemonEntity>) {
        withContext(dispatchers.io) {
            queries.transaction {
                pokemonEntities.forEach(queries::upsertPokemon)
            }
        }
    }

    suspend fun clearAndInsertPokemon(pokemonEntities: List<PokemonEntity>) {
        withContext(dispatchers.io) {
            queries.transaction {
                queries.clear()
                pokemonEntities.forEach(queries::upsertPokemon)
            }
        }
    }

    fun getPokemons(): PagingSource<Int, PokemonEntity> = QueryPagingSource(
        countQuery = queries.getPokemonsCount(),
        transacter = queries,
        context = dispatchers.io,
        queryProvider = queries::getPokemons,
    )
}