package app.pokedex.shared.favorite

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.store.StoreFactory

internal class FavoriteComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    private val output: (Output) -> Unit
) : ComponentContext by componentContext {

    sealed interface Output {
        data object NavigateBack : Output
        data class NavigateToDetails(val name: String) : Output
    }
}