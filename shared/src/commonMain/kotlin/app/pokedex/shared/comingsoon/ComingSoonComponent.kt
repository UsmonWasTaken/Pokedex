package app.pokedex.shared.comingsoon

import com.arkivanov.decompose.ComponentContext

internal class ComingSoonComponent(
    componentContext: ComponentContext,
    private val output: (Output) -> Unit
) : ComponentContext by componentContext {

    fun onOutput(output: Output) {
        output(output)
    }

    sealed class Output {
        data object NavigateBack : Output()
    }
}