package app.pokedex.shared.domain.model

import kotlinx.serialization.Serializable

data class PokemonInfo(
    val name: String,
    val number: String,
    val imageUrl: String,
    val height: Int,
    val weight: Int,
    val experience: Int,
    val types: List<Type>,
    val stats: List<Stats>,
    val isFavorite: Boolean,
) {
    @Serializable
    data class Type(
        val slot: Int,
        val name: String,
    )

    @Serializable
    data class Stats(
        val value: Int,
        val name: String
    ) {
        val shortName: String
            get() = when (name) {
                "hp" -> "HP"
                "attack" -> "Attack"
                "defense" -> "Defense"
                "special-attack" -> "Sp. Atk"
                "special-defense" -> "Sp. Dep"
                "speed" -> "Speed"
                else -> name
            }

        val maxValue: Int
            get() = when (name) {
                "hp" -> MAX_HP
                "attack" -> MAX_ATTACK
                "defense" -> MAX_DEFENCE
                "special-attack" -> MAX_SP_ATTACK
                "special-defense" -> MAX_SP_DEFENCE
                "speed" -> MAX_SPEED
                else -> value
            }

        companion object {
            const val MAX_HP = 255
            const val MAX_ATTACK = 190
            const val MAX_DEFENCE = 230
            const val MAX_SP_ATTACK = 200
            const val MAX_SP_DEFENCE = 230
            const val MAX_SPEED = 180
        }
    }
}
