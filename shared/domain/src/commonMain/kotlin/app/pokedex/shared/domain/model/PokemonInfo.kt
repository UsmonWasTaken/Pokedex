package app.pokedex.shared.domain.model

import kotlinx.serialization.Serializable

/**
 * Data class representing a Pokémon's information.
 *
 * @property name The name of the Pokémon.
 * @property number The national Pokédex number of the Pokémon.
 * @property imageUrl The URL of the Pokémon's official artwork.
 * @property height The height of the Pokémon in decimetres.
 * @property weight The weight of the Pokémon in hectograms.
 * @property experience The base experience gained for defeating the Pokémon.
 * @property types The list of types the Pokémon has.
 * @property stats The list of the Pokémon's stats.
 * @property isFavorite Whether the Pokémon is marked as a favorite.
 */
data class PokemonInfo(
    val id: Int,
    val name: String,
    val number: String,
    val imageUrl: String,
    val height: Int,
    val weight: Int,
    val experience: Int,
    val types: List<Type>,
    val stats: List<Stat>,
    val isFavorite: Boolean,
) {
    /**
     * Represents a type in a protocol buffer message.
     *
     * @property slot The index of the field in the message.
     * @property name The name of the field.
     */
    @Serializable
    data class Type(
        val slot: Int,
        val name: String,
    )

    /**
     * Represents a stat of a Pokemon.
     *
     * @property value The current value of the stat.
     * @property maxValue The maximum value of the stat.
     * @property name The name of the stat.
     * @property shortName The short name of the stat.
     */
    @Serializable
    data class Stat(
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
