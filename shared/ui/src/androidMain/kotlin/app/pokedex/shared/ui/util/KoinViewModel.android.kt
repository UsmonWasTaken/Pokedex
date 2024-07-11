package app.pokedex.shared.ui.util

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.definition.Definition
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import org.koin.core.module.dsl.DefinitionOptions
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.Qualifier
import org.koin.androidx.viewmodel.dsl.viewModel as actualViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf as actualViewModelOf

@Composable
actual inline fun <reified T : ViewModel> viewModel(): T {
    return koinViewModel()
}

actual inline fun <reified R : ViewModel> Module.viewModel(
    qualifier: Qualifier?,
    noinline definition: Definition<R>,
): KoinDefinition<R> = actualViewModel(qualifier, definition)

actual inline fun <reified R : ViewModel> Module.viewModelOf(
    crossinline constructor: () -> R,
    noinline options: DefinitionOptions<R>?,
): KoinDefinition<R> = actualViewModelOf(constructor, options)

actual inline fun <reified R : ViewModel, reified T1> Module.viewModelOf(
    crossinline constructor: (T1) -> R,
    noinline options: DefinitionOptions<R>?,
): KoinDefinition<R> = actualViewModelOf(constructor, options)

actual inline fun <reified R : ViewModel, reified T1, reified T2> Module.viewModelOf(
    crossinline constructor: (T1, T2) -> R,
    noinline options: DefinitionOptions<R>?,
): KoinDefinition<R> = actualViewModelOf(constructor, options)

actual inline fun <reified R : ViewModel, reified T1, reified T2, reified T3> Module.viewModelOf(
    crossinline constructor: (T1, T2, T3) -> R,
    noinline options: DefinitionOptions<R>?,
): KoinDefinition<R> = actualViewModelOf(constructor, options)

actual inline fun <reified R : ViewModel, reified T1, reified T2, reified T3, reified T4> Module.viewModelOf(
    crossinline constructor: (T1, T2, T3, T4) -> R,
    noinline options: DefinitionOptions<R>?,
): KoinDefinition<R> = actualViewModelOf(constructor, options)

actual inline fun <reified R : ViewModel, reified T1, reified T2, reified T3, reified T4, reified T5> Module.viewModelOf(
    crossinline constructor: (T1, T2, T3, T4, T5) -> R,
    noinline options: DefinitionOptions<R>?,
): KoinDefinition<R> = actualViewModelOf(constructor, options)

actual inline fun <reified R : ViewModel, reified T1, reified T2, reified T3, reified T4, reified T5, reified T6> Module.viewModelOf(
    crossinline constructor: (T1, T2, T3, T4, T5, T6) -> R,
    noinline options: DefinitionOptions<R>?,
): KoinDefinition<R> = actualViewModelOf(constructor, options)

actual inline fun <reified R : ViewModel, reified T1, reified T2, reified T3, reified T4, reified T5, reified T6, reified T7> Module.viewModelOf(
    crossinline constructor: (T1, T2, T3, T4, T5, T6, T7) -> R,
    noinline options: DefinitionOptions<R>?,
): KoinDefinition<R> = actualViewModelOf(constructor, options)

actual inline fun <reified R : ViewModel, reified T1, reified T2, reified T3, reified T4, reified T5, reified T6, reified T7, reified T8> Module.viewModelOf(
    crossinline constructor: (T1, T2, T3, T4, T5, T6, T7, T8) -> R,
    noinline options: DefinitionOptions<R>?,
): KoinDefinition<R> = actualViewModelOf(constructor, options)

actual inline fun <reified R : ViewModel, reified T1, reified T2, reified T3, reified T4, reified T5, reified T6, reified T7, reified T8, reified T9> Module.viewModelOf(
    crossinline constructor: (T1, T2, T3, T4, T5, T6, T7, T8, T9) -> R,
    noinline options: DefinitionOptions<R>?,
): KoinDefinition<R> = actualViewModelOf(constructor, options)

actual inline fun <reified R : ViewModel, reified T1, reified T2, reified T3, reified T4, reified T5, reified T6, reified T7, reified T8, reified T9, reified T10> Module.viewModelOf(
    crossinline constructor: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) -> R,
    noinline options: DefinitionOptions<R>?,
): KoinDefinition<R> = actualViewModelOf(constructor, options)
