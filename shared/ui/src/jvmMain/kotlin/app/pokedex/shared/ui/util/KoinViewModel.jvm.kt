package app.pokedex.shared.ui.util

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import org.koin.compose.koinInject
import org.koin.core.definition.Definition
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import org.koin.core.module.dsl.DefinitionOptions
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.Qualifier

@Composable
actual inline fun <reified T : ViewModel> viewModel(): T {
    return koinInject<T>()
}

actual inline fun <reified R : ViewModel> Module.viewModelOf(
    crossinline constructor: () -> R,
    noinline options: DefinitionOptions<R>?,
): KoinDefinition<R> = singleOf(constructor, options)

actual inline fun <reified R : ViewModel> Module.viewModel(
    qualifier: Qualifier?,
    noinline definition: Definition<R>,
): KoinDefinition<R> = single(qualifier, false, definition)