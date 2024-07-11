package app.pokedex.shared.ui.di

import app.pokedex.shared.ui.home.impl.di.HomeModule
import app.pokedex.shared.ui.notimplemented.impl.di.NotImplementedModule
import app.pokedex.shared.ui.pokemons.impl.di.PokemonsModule
import org.koin.core.KoinApplication

/**
 *  Collects all UI (view model, navigation, etc.) modules into one place for easy inclusion.
 *  This approach is useful when screens are in the same Gradle module.
 *  If screens were in separate modules, each module would define its own Koin module.
 */
fun KoinApplication.uiModules() {
    modules(
        HomeModule,
        PokemonsModule,
        NotImplementedModule,
    )
}