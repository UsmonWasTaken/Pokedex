package app.pokedex.shared.data.remote.mapper

import app.pokedex.shared.data.remote.response.PokemonsResponse
import app.pokedex.shared.database.PokemonEntity

internal class PokemonResponseMapper {

    fun toEntity(
        response: PokemonsResponse.PokemonResponse,
        page: Int
    ): PokemonEntity {
        val pokemonId = response.url.substring(
            startIndex = response.url.lastIndexOf('/', response.url.length - 2) + 1,
            endIndex = response.url.lastIndexOf('/')
        ).toLong()

        return PokemonEntity(
            id = pokemonId,
            name = response.name,
            page = page.toLong()
        )
    }
}