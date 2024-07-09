package app.pokedex.shared.details

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.store.StoreFactory

internal class DetailsComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    pokemonName: String,
    private val output: (Output) -> Unit
) : ComponentContext by componentContext {

    sealed interface Output {
        data object NavigateBack : Output
    }

}