package app.pokedex.shared.data.mapper

import app.pokedex.shared.database.PokemonEntity
import app.pokedex.shared.network.response.PokemonsResponse

internal class PokemonResponseMapper {

    fun toEntity(response: PokemonsResponse.PokemonResponse): PokemonEntity {
        val pokemonId = response.url.substring(
            startIndex = response.url.lastIndexOf('/', response.url.length - 2) + 1,
            endIndex = response.url.lastIndexOf('/')
        )

        return PokemonEntity(
            id = pokemonId.toInt(),
            name = response.name,
        )
    }
}