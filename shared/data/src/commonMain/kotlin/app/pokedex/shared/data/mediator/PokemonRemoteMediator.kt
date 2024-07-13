package app.pokedex.shared.data.mediator

import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import app.pokedex.shared.common.either.fold
import app.pokedex.shared.data.mapper.PokemonResponseMapper
import app.pokedex.shared.database.PokemonEntity
import app.pokedex.shared.database.PokemonRemoteKeyEntity
import app.pokedex.shared.database.dao.PokemonDao
import app.pokedex.shared.network.api.PokedexApi

internal class PokemonRemoteMediator(
    private val pokemonDao: PokemonDao,
    private val pokedexApi: PokedexApi,
    private val pokemonResponseMapper: PokemonResponseMapper,
) : RemoteMediator<Int, PokemonEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonEntity>,
    ): MediatorResult {
        val currentPage = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextPage?.minus(1) ?: 1
            }

            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                remoteKeys?.previousPage ?: return MediatorResult.Success(
                    endOfPaginationReached = remoteKeys != null
                )
            }

            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                remoteKeys?.nextPage ?: return MediatorResult.Success(
                    endOfPaginationReached = remoteKeys != null
                )
            }
        }

        return pokedexApi.fetchPokemons(page = currentPage, pageSize = state.config.pageSize)
            .fold(
                onSuccess = { response ->
                    val endOfPaginationReached = response.next == null
                    val prevPage = if (currentPage == 1) null else currentPage - 1
                    val nextPage = if (endOfPaginationReached) null else currentPage + 1

                    val pokemonEntities = response.results.map(pokemonResponseMapper::toEntity)
                    val pokemonRemoteKeys = pokemonEntities.map { pokemonEntity ->
                        PokemonRemoteKeyEntity(
                            id = pokemonEntity.id,
                            previousPage = prevPage,
                            nextPage = nextPage
                        )
                    }

                    if (loadType == LoadType.REFRESH) {
                        pokemonDao.clearAndInsertPokemonsWithRemoteKeys(
                            pokemons = pokemonEntities,
                            remoteKeys = pokemonRemoteKeys
                        )
                    } else {
                        pokemonDao.insertPokemonsWithRemoteKeys(
                            pokemons = pokemonEntities,
                            remoteKeys = pokemonRemoteKeys
                        )
                    }
                    MediatorResult.Success(endOfPaginationReached)
                },
                onFailure = MediatorResult::Error
            )
    }


    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, PokemonEntity>,
    ): PokemonRemoteKeyEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { pokemonDao.getRemoteKey(it) }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, PokemonEntity>,
    ): PokemonRemoteKeyEntity? = state.pages
        .firstOrNull { it.data.isNotEmpty() }
        ?.data
        ?.firstOrNull()
        ?.let { pokemonDao.getRemoteKey(it.id) }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, PokemonEntity>,
    ): PokemonRemoteKeyEntity? = state.pages
        .lastOrNull { it.data.isNotEmpty() }
        ?.data
        ?.lastOrNull()
        ?.let { pokemonDao.getRemoteKey(it.id) }
}
