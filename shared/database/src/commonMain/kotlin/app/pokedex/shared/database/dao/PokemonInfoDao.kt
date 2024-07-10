package app.pokedex.shared.database.dao

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.pokedex.shared.common.coroutines.PokedexDispatchers
import app.pokedex.shared.database.PokemonInfoEntity
import app.pokedex.shared.database.PokemonInfoEntityQueries
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class PokemonInfoDao(
    private val queries: PokemonInfoEntityQueries,
    private val dispatchers: PokedexDispatchers,
) {
    suspend fun upsertPokemon(entity: PokemonInfoEntity) = withContext(dispatchers.io) {
        queries.upsertPokemon(entity)
    }

    suspend fun updateIsFavorite(id: Int, isFavorite: Boolean) = withContext(dispatchers.io) {
        queries.updateIsFavorite(
            id = id,
            isFavorite = isFavorite
        )
    }

    suspend fun getPokemon(id: Int): PokemonInfoEntity? = withContext(dispatchers.io) {
        queries.getPokemon(id = id).executeAsOneOrNull()
    }

    fun getFavoritePokemons(): Flow<List<PokemonInfoEntity>> {
        return queries.getFavoritePokemons()
            .asFlow()
            .mapToList(dispatchers.io)
    }
}