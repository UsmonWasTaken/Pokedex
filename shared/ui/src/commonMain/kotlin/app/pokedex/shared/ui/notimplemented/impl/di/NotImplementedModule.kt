package app.pokedex.shared.ui.notimplemented.impl.di

import app.pokedex.shared.ui.notimplemented.impl.NotImplementedScreenFactory
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val NotImplementedModule = module {
    factoryOf(::NotImplementedScreenFactory)
}