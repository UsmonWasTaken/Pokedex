package app.pokedex.shared.data.mapper

import app.pokedex.shared.database.PokemonInfoEntity
import app.pokedex.shared.domain.model.PokemonInfo.Stat
import app.pokedex.shared.domain.model.PokemonInfo.Type
import app.pokedex.shared.network.response.PokemonInfoResponse

internal class PokemonInfoResponseMapper {

    fun toEntity(response: PokemonInfoResponse): PokemonInfoEntity {
        val types = response.types.map { type ->
            Type(
                slot = type.slot,
                name = type.name
            )
        }
        val stats = response.stats.map { stat ->
            Stat(
                value = stat.baseStat,
                name = stat.name
            )
        }

        return PokemonInfoEntity(
            id = response.id,
            name = response.name,
            height = response.height,
            weight = response.weight,
            experience = response.baseExperience,
            types = types,
            stats = stats,
            isFavorite = false
        )
    }
}