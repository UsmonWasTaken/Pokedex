package app.pokedex.shared.ui.pokemon_details.impl.state

import app.pokedex.shared.domain.model.Pokemon
import app.pokedex.shared.domain.model.PokemonInfo
import app.pokedex.shared.domain.model.PokemonInfo.Stat
import app.pokedex.shared.domain.model.PokemonInfo.Type

data class PokemonDetailsState(
    val id: Int,
    val name: String,
    val number: String,
    val imageUrl: String,
    val height: Int? = null,
    val weight: Int? = null,
    val experience: Int? = null,
    val types: List<Type>? = null,
    val stats: List<Stat>? = null,
    val isFavorite: Boolean? = null,
) {
    constructor(pokemon: Pokemon) : this(
        id = pokemon.id,
        name = pokemon.name,
        number = pokemon.number,
        imageUrl = pokemon.imageUrl,
    )

    constructor(pokemonInfo: PokemonInfo) : this(
        id = pokemonInfo.id,
        name = pokemonInfo.name,
        number = pokemonInfo.number,
        imageUrl = pokemonInfo.imageUrl,
        height = pokemonInfo.height,
        weight = pokemonInfo.weight,
        experience = pokemonInfo.experience,
        types = pokemonInfo.types,
        stats = pokemonInfo.stats,
        isFavorite = pokemonInfo.isFavorite,
    )
}