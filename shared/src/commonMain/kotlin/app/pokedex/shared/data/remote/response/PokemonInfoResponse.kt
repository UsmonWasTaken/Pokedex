package app.pokedex.shared.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PokemonInfoResponse(
    val id: Long,
    val name: String,
    val height: Long,
    val weight: Long,
    @SerialName("base_experience")
    val baseExperience: Long,
    val types: List<TypeResponse>,
    val stats: List<StatsResponse>,
) {

    @Serializable
    data class TypeResponse(
        val slot: Int,
        val type: TypeInfo
    ) {
        val name: String
            inline get() = type.name

        @JvmInline
        @Serializable
        value class TypeInfo(
            val name: String
        )
    }

    @Serializable
    data class StatsResponse(
        @SerialName("base_stat") val baseStat: Int,
        val stat: StatInfo
    ) {
        val name: String
            inline get() = stat.name

        @JvmInline
        @Serializable
        value class StatInfo(
            val name: String
        )
    }
}
