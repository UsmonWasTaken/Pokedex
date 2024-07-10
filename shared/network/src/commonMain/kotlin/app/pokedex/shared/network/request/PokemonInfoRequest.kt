package app.pokedex.shared.network.request

import io.ktor.resources.Resource

@Resource("pokemon/{id}")
internal class PokemonInfoRequest(val id: Int)
