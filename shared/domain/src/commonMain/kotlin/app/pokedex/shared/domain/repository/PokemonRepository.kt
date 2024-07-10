package app.pokedex.shared.domain.repository

import app.cash.paging.PagingData
import app.pokedex.shared.common.either.Either
import app.pokedex.shared.domain.error.GetPokemonInfoError
import app.pokedex.shared.domain.model.Pokemon
import app.pokedex.shared.domain.model.PokemonInfo
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for accessing Pokemon data.
 */
interface PokemonRepository {

    /**
     * Fetches Pokemon information for the given [id].
     *
     * @param id The ID of the Pokemon to fetch.
     * @return Either a [PokemonInfo] object containing the Pokemon's information, or a [GetPokemonInfoError] object if an error occurs.
     */
    suspend fun getPokemonInfo(id: Int): Either<PokemonInfo, GetPokemonInfoError>


    /**
     * Returns a Flow of PagingData objects containing a list of Pokemon objects.
     *
     * The PagingData object provides methods to access the current list of Pokemon,
     * load more items, and observe changes to the data.
     *
     * @return a Flow of PagingData<Pokemon> objects
     */
    fun getPokemons(): Flow<PagingData<Pokemon>>
}
