package app.pokedex.shared.domain.repository

import androidx.paging.PagingData
import app.pokedex.shared.domain.model.Pokemon
import app.pokedex.shared.domain.model.PokemonInfo
import app.pokedex.shared.util.Either
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getPokemonInfo(id: Int): Either<PokemonInfo, Throwable>
    fun getPokemons(): Flow<PagingData<Pokemon>>
}
