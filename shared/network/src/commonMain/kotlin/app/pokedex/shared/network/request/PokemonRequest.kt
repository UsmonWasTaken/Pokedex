package app.pokedex.shared.network.request

import io.ktor.resources.Resource

@Resource("pokemon")
internal class PokemonRequest(val limit: Int, val offset: Int)
