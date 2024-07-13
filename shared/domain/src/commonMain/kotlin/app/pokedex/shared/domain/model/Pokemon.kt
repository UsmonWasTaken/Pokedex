package app.pokedex.shared.domain.model

/**
 * Data class representing a Pokemon.
 *
 * @property id The unique identifier of the Pokemon.
 * @property name The name of the Pokemon.
 * @property number The Pokedex number of the Pokemon.
 * @property imageUrl The URL of the Pokemon's image.
 */
data class Pokemon(
    val id: Int,
    val name: String,
    val number: String,
    val imageUrl: String,
)
