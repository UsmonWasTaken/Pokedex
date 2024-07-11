package app.pokedex.shared.ui.home.impl

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import app.pokedex.shared.ui.home.api.HomeScreenFactory
import app.pokedex.shared.ui.pokemons.api.PokemonsScreenFactory
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.compose.koinInject

internal fun HomeScreenFactory() = HomeScreenFactory(::HomeScreen)

private class HomeScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val pokemonsScreenFactory = koinInject<PokemonsScreenFactory>()

        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {
                        navigator.push(pokemonsScreenFactory.create())
                    }
                ) {
                    Text(text = "Navigate to Pokemons Screen")
                }
            }
        }
    }
}
