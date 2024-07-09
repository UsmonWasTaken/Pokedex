package app.pokedex.shared.data.remote.request

import io.ktor.resources.Resource

@Resource("pokemon")
internal class PokemonRequest(val limit: Int, val offset: Int)
