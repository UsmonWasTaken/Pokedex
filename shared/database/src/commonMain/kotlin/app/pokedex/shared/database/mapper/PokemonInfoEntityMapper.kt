package app.pokedex.shared.database.mapper

import app.pokedex.shared.database.PokemonInfoEntity
import app.pokedex.shared.domain.model.PokemonInfo
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

class PokemonInfoEntityMapper {

    fun toDomain(entity: PokemonInfoEntity): PokemonInfo {
        val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${entity.id}.png"
        return PokemonInfo(
            name = entity.name,
            number = String.format("#%03d", entity.id),
            imageUrl = imageUrl,
            height = entity.height,
            weight = entity.weight,
            experience = entity.experience,
            types = entity.types,
            stats = entity.stats,
            isFavorite = entity.isFavorite,
        )
    }
}
