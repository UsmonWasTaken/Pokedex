package app.pokedex.shared.network.api

import app.pokedex.shared.common.coroutines.PokedexDispatchers
import app.pokedex.shared.common.either.Either
import app.pokedex.shared.common.either.asFailure
import app.pokedex.shared.common.either.asSuccess
import app.pokedex.shared.domain.error.PokedexException
import app.pokedex.shared.domain.error.PokedexException.ClientSideException
import app.pokedex.shared.domain.error.PokedexException.NoInternetException
import app.pokedex.shared.domain.error.PokedexException.ServerSideException
import app.pokedex.shared.network.request.PokemonInfoRequest
import app.pokedex.shared.network.request.PokemonRequest
import app.pokedex.shared.network.response.PokemonInfoResponse
import app.pokedex.shared.network.response.PokemonsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.resources.get
import io.ktor.client.statement.HttpResponse
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.withContext

class PokedexApi(
    private val client: HttpClient,
    private val dispatchers: PokedexDispatchers,
) {
    suspend fun fetchPokemons(page: Int, pageSize: Int): Either<PokemonsResponse, PokedexException>{
        val request = PokemonRequest(
            limit = pageSize,
            offset = page * pageSize
        )
        return withContext(dispatchers.io) {
            safeApiCall { client.get(request) }
        }
    }

    suspend fun fetchPokemonInfo(id: Int): Either<PokemonInfoResponse, PokedexException> {
        val request = PokemonInfoRequest(id = id)
        return withContext(dispatchers.io) {
            safeApiCall { client.get(request) }
        }
    }

    private suspend inline fun <reified R> safeApiCall(call: () -> HttpResponse): Either<R, PokedexException> {
        val response = try {
            call()
        } catch (e: IOException) {
            return NoInternetException().asFailure()
        }

        when (response.status.value) {
            in 400..499 -> return ClientSideException().asFailure()
            in 500..599 -> return ServerSideException().asFailure()
        }

        return try {
            response.body<R>().asSuccess()
        } catch (cause: Exception) {
            PokedexException.UnknownException(cause).asFailure()
        }
    }
}
