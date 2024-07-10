package app.pokedex.shared.data.remote.api

import app.pokedex.shared.data.remote.request.PokemonInfoRequest
import app.pokedex.shared.data.remote.request.PokemonRequest
import app.pokedex.shared.data.remote.response.PokemonInfoResponse
import app.pokedex.shared.data.remote.response.PokemonsResponse
import app.pokedex.shared.domain.common.Either
import app.pokedex.shared.util.PokedexDispatchers
import app.pokedex.shared.domain.common.catch
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.resources.get
import kotlinx.coroutines.withContext

internal class PokedexApi(
    private val client: HttpClient,
    private val dispatchers: PokedexDispatchers,
) {

    suspend fun fetchPokemons(
        page: Int,
        pageSize: Int
    ): Either<PokemonsResponse, Throwable> {
        val request = PokemonRequest(
            limit = pageSize,
            offset = page * pageSize
        )
        return withContext(dispatchers.io) {
            catch { client.get(request).body() }
        }
    }

    suspend fun fetchPokemonInfo(id: Int): Either<PokemonInfoResponse, Throwable> {
        val request = PokemonInfoRequest(id = id)
        return withContext(dispatchers.io) {
            catch { client.get(request).body() }
        }
    }
}
