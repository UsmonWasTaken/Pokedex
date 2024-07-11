package app.pokedex.shared.ui.home.impl

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import app.pokedex.shared.domain.model.Video
import app.pokedex.shared.ui.Res
import app.pokedex.shared.ui.common.component.SearchField
import app.pokedex.shared.ui.home.impl.HomeScreen.Intent
import app.pokedex.shared.ui.home.impl.component.HomeFeaturesSection
import app.pokedex.shared.ui.home.impl.component.HomeTopBar
import app.pokedex.shared.ui.home.impl.component.HomeVideosSection
import app.pokedex.shared.ui.home_welcome_text
import app.pokedex.shared.ui.watch
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun HomeContent(
    onIntent: (Intent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = { HomeTopBar(onIntent = onIntent) },
        modifier = modifier
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState())
        ) {
            Text(
                text = stringResource(Res.string.home_welcome_text),
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 20.dp),
            )

            // Search isn't implemented yet, just to fit the aesthetics.
            var searchQuery by rememberSaveable { mutableStateOf("") }
            SearchField(
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                onClearSearch = { searchQuery = "" },
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)
            )

            HomeFeaturesSection(
                onIntent = onIntent,
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            Text(
                text = stringResource(Res.string.watch),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(20.dp)
            )

            HorizontalDivider(
                color = MaterialTheme.colorScheme.outline.copy(alpha = .4f),
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
            )

            HomeVideosSection(
                videos = Video.demoVideos,
                onIntent = onIntent,
                contentPadding = PaddingValues(horizontal = 10.dp)
            )
        }
    }
}

