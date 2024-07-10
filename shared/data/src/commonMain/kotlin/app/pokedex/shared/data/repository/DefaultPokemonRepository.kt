package app.pokedex.shared.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import app.pokedex.shared.common.either.Either
import app.pokedex.shared.common.either.asSuccess
import app.pokedex.shared.common.either.flatMap
import app.pokedex.shared.data.mapper.PokemonInfoResponseMapper
import app.pokedex.shared.data.mediator.PokemonRemoteMediator
import app.pokedex.shared.database.PokemonEntity
import app.pokedex.shared.database.dao.PokemonDao
import app.pokedex.shared.database.dao.PokemonInfoDao
import app.pokedex.shared.database.mapper.PokemonEntityMapper
import app.pokedex.shared.database.mapper.PokemonInfoEntityMapper
import app.pokedex.shared.domain.error.PokedexException
import app.pokedex.shared.domain.model.Pokemon
import app.pokedex.shared.domain.model.PokemonInfo
import app.pokedex.shared.domain.repository.PokemonRepository
import app.pokedex.shared.network.api.PokedexApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalPagingApi::class)
internal class DefaultPokemonRepository(
    pokemonRemoteMediator: PokemonRemoteMediator,
    pokemonDao: PokemonDao,
    private val pokemonInfoDao: PokemonInfoDao,
    private val pokedexApi: PokedexApi,
    private val pokemonEntityMapper: PokemonEntityMapper,
    private val pokemonInfoResponseMapper: PokemonInfoResponseMapper,
    private val pokemonInfoEntityMapper: PokemonInfoEntityMapper
) : PokemonRepository {

    private val pager: Pager<Int, PokemonEntity> = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false,
        ),
        remoteMediator = pokemonRemoteMediator,
        pagingSourceFactory = { pokemonDao.getPokemons() }
    )

    override suspend fun getPokemonInfo(id: Int): Either<PokemonInfo, PokedexException> {
        pokemonInfoDao.getPokemon(id)?.let { entity ->
            return pokemonInfoEntityMapper.toDomain(entity).asSuccess()
        }
        return pokedexApi.fetchPokemonInfo(id).flatMap { response ->
            val entity = pokemonInfoResponseMapper.toEntity(response)
            pokemonInfoDao.upsertPokemon(entity)
            pokemonInfoEntityMapper.toDomain(entity).asSuccess()
        }
    }

    override fun getPokemons(): Flow<PagingData<Pokemon>> = pager
        .flow.map { pagingData -> pagingData.map(pokemonEntityMapper::toDomain) }
}