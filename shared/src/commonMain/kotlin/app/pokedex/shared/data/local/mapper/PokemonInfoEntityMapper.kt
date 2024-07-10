package app.pokedex.shared.data.local.mapper

import app.pokedex.shared.database.PokemonInfoEntity
import app.pokedex.shared.domain.model.PokemonInfo
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

internal class PokemonInfoEntityMapper {

    fun toDomain(entity: PokemonInfoEntity): PokemonInfo {
        val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${entity.id}.png"
        return PokemonInfo(
            name = entity.name,
            number = String.format("#%03d", entity.id),
            imageUrl = imageUrl,
            height = entity.height.toInt(),
            weight = entity.weight.toInt(),
            experience = entity.experience.toInt(),
            types = Json.decodeFromString(
                deserializer = ListSerializer(PokemonInfo.Type.serializer()),
                string = entity.types,
            ),
            stats = Json.decodeFromString(
                deserializer = ListSerializer(PokemonInfo.Stats.serializer()),
                string = entity.stats,
            ),
            isFavorite = entity.isFavorite == 1L,
        )
    }
}