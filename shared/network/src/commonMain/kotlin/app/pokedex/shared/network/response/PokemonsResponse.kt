package app.pokedex.shared.network.response

import kotlinx.serialization.Serializable

@Serializable
data class PokemonsResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonResponse>
) {

    @Serializable
    data class PokemonResponse(
        val name: String,
        val url: String
    )
}
