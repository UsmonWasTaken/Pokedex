package app.pokedex.shared.data.remote.api

import app.pokedex.shared.data.remote.error.PokedexError
import app.pokedex.shared.data.remote.error.PokedexError.ClientError
import app.pokedex.shared.data.remote.error.PokedexError.ServerError
import app.pokedex.shared.data.remote.error.PokedexError.ServiceUnavailable
import app.pokedex.shared.data.remote.error.PokedexError.UnknownError
import app.pokedex.shared.data.remote.request.PokemonInfoRequest
import app.pokedex.shared.data.remote.request.PokemonRequest
import app.pokedex.shared.data.remote.response.PokemonInfoResponse
import app.pokedex.shared.data.remote.response.PokemonsResponse
import app.pokedex.shared.util.Either
import app.pokedex.shared.util.PokedexDispatchers
import app.pokedex.shared.util.asFailure
import app.pokedex.shared.util.asSuccess
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.resources.get
import io.ktor.client.statement.HttpResponse
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.withContext

internal class PokedexApi(
    private val client: HttpClient,
    private val dispatchers: PokedexDispatchers,
) {

    suspend fun fetchPokemons(page: Int): Either<List<PokemonsResponse>, PokedexError> {
        return withContext(dispatchers.io) {
            val request = PokemonRequest(
                limit = PAGE_SIZE,
                offset = page * PAGE_SIZE
            )
            handleErrors {
                client.get(request).body()
            }
        }
    }

    suspend fun fetchPokemonInfo(name: String): Either<PokemonInfoResponse, PokedexError> {
        return withContext(dispatchers.io) {
            val request = PokemonInfoRequest(name = name)
            handleErrors {
                client.get(request).body()
            }
        }
    }

    private suspend inline fun <reified T> handleErrors(request: () -> HttpResponse): Either<T, PokedexError> {
        val response = try {
            request.invoke()
        } catch (e: IOException) {
            return ServiceUnavailable.asFailure()
        }

        return when (response.status.value) {
            in 200..299 -> response.body<T>().asSuccess()
            in 400..499 -> ClientError.asFailure()
            500 -> ServerError.asFailure()
            else -> UnknownError.asFailure()
        }
    }

    companion object {
        private const val PAGE_SIZE = 20
    }
}