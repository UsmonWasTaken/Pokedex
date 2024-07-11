package app.pokedex.shared.ui.pokemons.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cash.paging.cachedIn
import app.pokedex.shared.domain.repository.PokemonRepository

internal class PokemonsViewModel(
    pokemonRepository: PokemonRepository,
) : ViewModel() {

    val pokemons = pokemonRepository.getPokemons()
        .cachedIn(viewModelScope)

    init {
        println("PokemonsViewModel init")
    }

    override fun onCleared() {
        println("PokemonsViewModel onCleared")
    }
}
