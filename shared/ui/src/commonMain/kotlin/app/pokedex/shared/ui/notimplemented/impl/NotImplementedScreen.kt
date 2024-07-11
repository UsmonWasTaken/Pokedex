package app.pokedex.shared.ui.notimplemented.impl

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import app.pokedex.shared.ui.notimplemented.api.NotImplementedScreenFactory
import app.pokedex.shared.ui.notimplemented.impl.NotImplementedScreen.Intent
import app.pokedex.shared.ui.notimplemented.impl.component.NotImplementedTopBar
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

internal fun NotImplementedScreenFactory() = NotImplementedScreenFactory(::NotImplementedScreen)

internal class NotImplementedScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        NotImplementedContent(
            onIntent = { intent ->
                when (intent) {
                    Intent.NavigateBack -> navigator.pop()
                }
            },
        )
    }

    sealed interface Intent {
        data object NavigateBack : Intent
    }
}

@Composable
internal fun NotImplementedContent(
    onIntent: (Intent) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { NotImplementedTopBar(onIntent = onIntent) },
        modifier = modifier
    ) { padding ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                Text(
                    text = "Surprise 🤗",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.displayMedium,
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "This part of the application is not yet implemented",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium,
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Please wait. I'll finish it tomorrow 👉👈",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}
