package app.pokedex.shared.ui.util

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import org.koin.core.definition.Definition
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import org.koin.core.module.dsl.DefinitionOptions
import org.koin.core.qualifier.Qualifier

@Composable
expect inline fun <reified T : ViewModel> viewModel(): T

expect inline fun <reified R : ViewModel> Module.viewModelOf(
    crossinline constructor: () -> R,
    noinline options: DefinitionOptions<R>? = null,
): KoinDefinition<R>

expect inline fun <reified R : ViewModel> Module.viewModel(
    qualifier: Qualifier? = null,
    noinline definition: Definition<R>,
): KoinDefinition<R>