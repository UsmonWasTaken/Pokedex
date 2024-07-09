package app.pokedex.shared.pokedex

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.store.StoreFactory

internal class PokedexComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    searchValue: String,
    private val output: (Output) -> Unit
) : ComponentContext by componentContext {

    sealed interface Output {
        data object NavigateBack : Output
        data class NavigateToDetails(val name: String) : Output
    }
}