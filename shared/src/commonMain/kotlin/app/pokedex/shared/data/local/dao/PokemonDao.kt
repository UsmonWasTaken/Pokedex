package app.pokedex.shared.data.local.dao

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.pokedex.shared.database.PokemonEntity
import app.pokedex.shared.database.PokemonEntityQueries
import app.pokedex.shared.util.PokedexDispatchers
import kotlinx.coroutines.flow.Flow
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

    fun getPokemonsByPage(page: Int): Flow<List<PokemonEntity>> {
        return queries.getPokemonsByPage(page = page.toLong())
            .asFlow()
            .mapToList(dispatchers.io)
    }
}