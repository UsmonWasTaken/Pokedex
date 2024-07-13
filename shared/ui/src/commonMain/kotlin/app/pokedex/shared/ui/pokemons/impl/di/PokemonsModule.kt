package app.pokedex.shared.ui.pokemons.impl.di

import app.pokedex.shared.ui.pokemons.impl.PokemonsScreenFactory
import app.pokedex.shared.ui.pokemons.impl.PokemonsScreenModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val PokemonsModule = module {
    factoryOf(::PokemonsScreenFactory)
    factoryOf(::PokemonsScreenModel)
}