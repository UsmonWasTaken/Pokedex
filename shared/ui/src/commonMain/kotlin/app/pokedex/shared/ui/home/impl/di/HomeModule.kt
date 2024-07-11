package app.pokedex.shared.ui.home.impl.di

import app.pokedex.shared.ui.home.impl.HomeScreenFactory
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val HomeModule = module {
    factoryOf(::HomeScreenFactory)
}
