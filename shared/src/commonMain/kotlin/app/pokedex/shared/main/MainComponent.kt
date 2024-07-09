package app.pokedex.shared.main

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.store.StoreFactory

internal class MainComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    private val output: (Output) -> Unit
) : ComponentContext by componentContext {

    fun onOutput(output: Output) {
        output(output)
    }

    sealed interface Output {
        data object PokedexClicked : Output
        data object FavoriteClicked : Output
        data object ComingSoon : Output
        data class PokedexSearchSubmitted(val searchValue: String) : Output
    }
}