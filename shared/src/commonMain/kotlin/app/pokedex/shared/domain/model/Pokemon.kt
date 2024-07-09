package app.pokedex.shared.domain.model

data class Pokemon(
    val page: Int,
    val name: String,
    val number: String,
    val imageUrl: String,
)
