package app.pokedex.shared.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
internal data class PokemonsResponse(
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
