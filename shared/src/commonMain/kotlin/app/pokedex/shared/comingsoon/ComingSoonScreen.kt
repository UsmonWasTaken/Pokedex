package app.pokedex.shared.comingsoon

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.pokedex.shared.comingsoon.components.ComingSoonContent
import app.pokedex.shared.comingsoon.components.ComingSoonTopBar

@Composable
internal fun ComingSoonScreen(component: ComingSoonComponent) {
    Scaffold(
        topBar = {
            ComingSoonTopBar(
                onNavigateBack = {
                    component.onOutput(ComingSoonComponent.Output.NavigateBack)
                }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValue ->
        ComingSoonContent(
            modifier = Modifier.padding(paddingValue)
        )
    }
}