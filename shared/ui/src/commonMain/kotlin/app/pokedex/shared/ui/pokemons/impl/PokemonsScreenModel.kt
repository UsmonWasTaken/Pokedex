package app.pokedex.shared.ui.pokemons.impl

import app.cash.paging.cachedIn
import app.pokedex.shared.domain.repository.PokemonRepository
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope

internal class PokemonsScreenModel(
    pokemonRepository: PokemonRepository,
) : ScreenModel {

    val pokemons = pokemonRepository.getPokemons()
        .cachedIn(screenModelScope)
}
