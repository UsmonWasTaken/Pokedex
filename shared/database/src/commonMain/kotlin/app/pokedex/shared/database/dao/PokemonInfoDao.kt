package app.pokedex.shared.database.dao

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.pokedex.shared.common.coroutines.PokedexDispatchers
import app.pokedex.shared.database.PokedexDatabase
import app.pokedex.shared.database.PokemonInfoEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class PokemonInfoDao(
    database: PokedexDatabase,
    private val dispatchers: PokedexDispatchers,
) {
    private val pokemonInfoQueries = database.pokemonInfoEntityQueries

    suspend fun upsertPokemon(entity: PokemonInfoEntity) = withContext(dispatchers.io) {
        pokemonInfoQueries.upsertPokemon(entity)
    }

    suspend fun updateIsFavorite(id: Int, isFavorite: Boolean) = withContext(dispatchers.io) {
        pokemonInfoQueries.updateIsFavorite(
            id = id,
            isFavorite = isFavorite
        )
    }

    suspend fun getPokemon(id: Int): PokemonInfoEntity? = withContext(dispatchers.io) {
        pokemonInfoQueries.getPokemon(id = id).executeAsOneOrNull()
    }

    fun getFavoritePokemons(): Flow<List<PokemonInfoEntity>> {
        return pokemonInfoQueries.getFavoritePokemons()
            .asFlow()
            .mapToList(dispatchers.io)
    }
}