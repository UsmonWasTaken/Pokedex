package app.pokedex.shared.data.remote.request

import io.ktor.resources.Resource

@Resource("pokemon/{id}")
internal class PokemonInfoRequest(val id: Int)
