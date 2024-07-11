package app.pokedex.shared.ui.pokemon_details.impl.di

import app.pokedex.shared.ui.pokemon_details.impl.PokemonDetailsScreenFactory
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val PokemonDetailsModule = module {
    factoryOf(::PokemonDetailsScreenFactory)
}