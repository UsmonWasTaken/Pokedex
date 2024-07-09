package app.pokedex.shared.data.local.mapper

import app.pokedex.shared.database.PokemonEntity
import app.pokedex.shared.domain.model.Pokemon

internal class PokemonEntityMapper {

    fun toDomain(entity: PokemonEntity): Pokemon {
        val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${entity.id}.png"
        return Pokemon(
            page = entity.page.toInt(),
            name = entity.name,
            number = String.format("#%03d", entity.id),
            imageUrl = imageUrl,
        )
    }
}