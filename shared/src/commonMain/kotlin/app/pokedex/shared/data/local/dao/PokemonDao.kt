package app.pokedex.shared.data.local.dao

import androidx.paging.PagingSource
import app.cash.sqldelight.paging3.QueryPagingSource
import app.pokedex.shared.database.PokemonEntity
import app.pokedex.shared.database.PokemonEntityQueries
import app.pokedex.shared.util.PokedexDispatchers
import kotlinx.coroutines.withContext

internal class PokemonDao(
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