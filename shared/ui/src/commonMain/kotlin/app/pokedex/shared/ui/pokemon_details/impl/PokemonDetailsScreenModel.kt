package app.pokedex.shared.ui.pokemon_details.impl

import app.pokedex.shared.common.either.fold
import app.pokedex.shared.domain.model.Pokemon
import app.pokedex.shared.domain.repository.PokemonRepository
import app.pokedex.shared.ui.pokemon_details.impl.PokemonDetailsScreen.Intent
import app.pokedex.shared.ui.pokemon_details.impl.PokemonDetailsScreen.Intent.Favorite
import app.pokedex.shared.ui.pokemon_details.impl.PokemonDetailsScreen.Intent.UnFavorite
import app.pokedex.shared.ui.pokemon_details.impl.state.PokemonDetailsState
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class PokemonDetailsScreenModel(
    private val pokemonRepository: PokemonRepository,
    private val pokemon: Pokemon,
) : ScreenModel {

    private val _state = MutableStateFlow(PokemonDetailsState(pokemon))
    val state = _state.asStateFlow()

    fun onIntent(intent: Intent) {
        when (intent) {
            is Favorite -> _state.update { it.copy(isFavorite = true) }
            is UnFavorite -> _state.update { it.copy(isFavorite = false) }
            else -> return
        }
    }

    init {
        loadPokemonInfo()
    }

    private fun loadPokemonInfo() = screenModelScope.launch {
        pokemonRepository.getPokemonInfo(pokemon.id)
            .fold(
                onSuccess = { _state.value = PokemonDetailsState(it) },
                onFailure = { it.printStackTrace() }
            )
    }
}