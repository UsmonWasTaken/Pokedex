package app.pokedex.shared.database.mapper

import app.pokedex.shared.database.PokemonEntity
import app.pokedex.shared.domain.model.Pokemon

class PokemonEntityMapper {

    fun toDomain(entity: PokemonEntity): Pokemon {
        val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${entity.id}.png"
        return Pokemon(
            id = entity.id,
            name = entity.name,
            number = String.format("#%03d", entity.id),
            imageUrl = imageUrl,
        )
    }
}
