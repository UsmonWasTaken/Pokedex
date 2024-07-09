package app.pokedex.shared

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import app.pokedex.shared.comingsoon.ComingSoonComponent
import app.pokedex.shared.details.DetailsComponent
import app.pokedex.shared.favorite.FavoriteComponent
import app.pokedex.shared.main.MainComponent
import app.pokedex.shared.pokedex.PokedexComponent
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.store.StoreFactory
import kotlinx.serialization.Serializable

@Immutable
class RootComponent internal constructor(
    componentContext: ComponentContext,
    private val mainComponentFactory: (ComponentContext, (MainComponent.Output) -> Unit) -> MainComponent,
    private val pokedexComponentFactory: (ComponentContext, searchValue: String, (PokedexComponent.Output) -> Unit) -> PokedexComponent,
    private val favoriteComponentFactory: (ComponentContext, (FavoriteComponent.Output) -> Unit) -> FavoriteComponent,
    private val detailsComponentFactory: (ComponentContext, pokemonName: String, (DetailsComponent.Output) -> Unit) -> DetailsComponent,
    private val comingSoonComponentFactory: (ComponentContext, (ComingSoonComponent.Output) -> Unit) -> ComingSoonComponent,
) : ComponentContext by componentContext {

    constructor(
        componentContext: ComponentContext,
        storeFactory: StoreFactory,
    ) : this(
        componentContext = componentContext,
        mainComponentFactory = { childContext, output ->
            MainComponent(
                componentContext = childContext,
                storeFactory = storeFactory,
                output = output
            )
        },
        pokedexComponentFactory = { childContext, searchValue, output ->
            PokedexComponent(
                componentContext = childContext,
                storeFactory = storeFactory,
                searchValue = searchValue,
                output = output
            )
        },
        favoriteComponentFactory = { childContext, output ->
            FavoriteComponent(
                componentContext = childContext,
                storeFactory = storeFactory,
                output = output
            )
        },
        detailsComponentFactory = { childContext, pokemonName, output ->
            DetailsComponent(
                componentContext = childContext,
                storeFactory = storeFactory,
                pokemonName = pokemonName,
                output = output
            )
        },
        comingSoonComponentFactory = { childContext, output ->
            ComingSoonComponent(
                componentContext = childContext,
                output = output
            )
        },
    )

    private val navigation = StackNavigation<Configuration>()

    private val stack: Value<ChildStack<Configuration, Child>> = childStack(
        source = navigation,
        serializer = Configuration.serializer(),
        initialConfiguration = Configuration.Main,
        handleBackButton = false,
        childFactory = ::createChild
    )

    @Stable
    val childStack: Value<ChildStack<*, Child>> = stack

    private fun createChild(
        configuration: Configuration,
        componentContext: ComponentContext
    ): Child = when (configuration) {
        is Configuration.Main -> Child.Main(mainComponentFactory(componentContext, ::onMainOutput))
        is Configuration.Pokedex -> Child.Pokedex(pokedexComponentFactory(componentContext, configuration.searchValue, ::onPokedexOutput))
        is Configuration.Favorite -> Child.Favorite(favoriteComponentFactory(componentContext, ::onFavoriteOutput))
        is Configuration.Details -> Child.Details(detailsComponentFactory(componentContext, configuration.pokemonName, ::onDetailsOutput))
        is Configuration.ComingSoon -> Child.ComingSoon(comingSoonComponentFactory(componentContext, ::onComingSoonOutput))
    }

    private fun onMainOutput(output: MainComponent.Output) = when (output) {
        is MainComponent.Output.PokedexClicked -> navigation.push(Configuration.Pokedex())
        is MainComponent.Output.FavoriteClicked -> navigation.push(Configuration.Favorite)
        is MainComponent.Output.ComingSoon -> navigation.push(Configuration.ComingSoon)
        is MainComponent.Output.PokedexSearchSubmitted -> navigation.push(Configuration.Pokedex(output.searchValue))
    }

    private fun onPokedexOutput(output: PokedexComponent.Output) = when (output) {
        is PokedexComponent.Output.NavigateBack -> navigation.pop()
        is PokedexComponent.Output.NavigateToDetails -> navigation.push(Configuration.Details(output.name))
    }

    private fun onFavoriteOutput(output: FavoriteComponent.Output) = when (output) {
        is FavoriteComponent.Output.NavigateBack -> navigation.pop()
        is FavoriteComponent.Output.NavigateToDetails -> navigation.push(Configuration.Details(output.name))
    }

    private fun onDetailsOutput(output: DetailsComponent.Output) = when (output) {
        is DetailsComponent.Output.NavigateBack -> navigation.pop()
    }

    private fun onComingSoonOutput(output: ComingSoonComponent.Output) = when (output) {
        is ComingSoonComponent.Output.NavigateBack -> navigation.pop()
    }

    @Immutable
    @Serializable
    private sealed class Configuration {

        @Immutable
        @Serializable
        data object Main : Configuration()

        @Immutable
        @Serializable
        data class Pokedex(val searchValue: String = "") : Configuration()

        @Immutable
        @Serializable
        data object Favorite : Configuration()

        @Immutable
        @Serializable
        data class Details(val pokemonName: String) : Configuration()

        @Immutable
        @Serializable
        data object ComingSoon : Configuration()
    }

    @Immutable
    sealed class Child {

        @Immutable
        internal data class Main(val component: MainComponent) : Child()

        @Immutable
        internal data class Pokedex(val component: PokedexComponent) : Child()

        @Immutable
        internal data class Favorite(val component: FavoriteComponent) : Child()

        @Immutable
        internal data class Details(val component: DetailsComponent) : Child()

        @Immutable
        internal data class ComingSoon(val component: ComingSoonComponent) : Child()
    }
}