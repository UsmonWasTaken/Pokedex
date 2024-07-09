package app.pokedex.shared.data.remote.request

import io.ktor.resources.Resource

@Resource("pokemon/{name}")
internal class PokemonInfoRequest(val name: String)
