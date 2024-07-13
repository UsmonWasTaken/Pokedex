package app.pokedex.shared.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonInfoResponse(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    @SerialName("base_experience")
    val baseExperience: Int,
    val types: List<TypeResponse>,
    val stats: List<StatsResponse>,
) {

    @Serializable
    data class TypeResponse(
        val slot: Int,
        val type: TypeInfo,
    ) {
        val name: String
            inline get() = type.name


        @Serializable
        data class TypeInfo(
            val name: String,
        )
    }

    @Serializable
    data class StatsResponse(
        @SerialName("base_stat") val baseStat: Int,
        val stat: StatInfo,
    ) {
        val name: String
            inline get() = stat.name


        @Serializable
        data class StatInfo(
            val name: String,
        )
    }
}
