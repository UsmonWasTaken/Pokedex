package app.pokedex.shared.data.remote.mapper

import app.pokedex.shared.data.remote.response.PokemonInfoResponse
import app.pokedex.shared.database.PokemonInfoEntity
import app.pokedex.shared.domain.model.PokemonInfo.Stats
import app.pokedex.shared.domain.model.PokemonInfo.Type
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

internal class PokemonInfoResponseMapper {

    fun toEntity(response: PokemonInfoResponse): PokemonInfoEntity {
        val types = response.types.map { type ->
            Type(
                slot = type.slot,
                name = type.name
            )
        }
        val stats = response.stats.map { stat ->
            Stats(
                value = stat.baseStat,
                name = stat.name
            )
        }

        return PokemonInfoEntity(
            id = response.id.toLong(),
            name = response.name,
            height = response.height.toLong(),
            weight = response.weight.toLong(),
            experience = response.baseExperience.toLong(),
            types = Json.encodeToString(
                serializer = ListSerializer(Type.serializer()),
                value = types,
            ),
            stats = Json.encodeToString(
                serializer = ListSerializer(Stats.serializer()),
                value = stats
            ),
            isFavorite = 0
        )
    }
}