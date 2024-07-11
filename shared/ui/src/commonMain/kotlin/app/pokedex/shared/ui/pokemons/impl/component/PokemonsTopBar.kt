package app.pokedex.shared.ui.pokemons.impl.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.pokedex.shared.ui.Res
import app.pokedex.shared.ui.cd_navigate_back
import app.pokedex.shared.ui.common.component.SearchField
import app.pokedex.shared.ui.pokemons.impl.PokemonsScreen.Intent
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun PokemonsTopBar(
    onIntent: (Intent) -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        navigationIcon = {
            IconButton(
                onClick = { onIntent(Intent.NavigateBack) },
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = stringResource(Res.string.cd_navigate_back)
                )
            }
        },
        title = {
            // Search isn't implemented yet, just to fit the aesthetics.
            var searchQuery by rememberSaveable { mutableStateOf("") }
            Row(modifier = Modifier.padding(end = 10.dp)) {
                SearchField(
                    query = searchQuery,
                    onQueryChange = { searchQuery = it },
                    onClearSearch = { searchQuery = "" },
                )
            }
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        windowInsets = TopAppBarDefaults.windowInsets.add(WindowInsets(top = 16.dp, bottom = 4.dp)),
        modifier = modifier.fillMaxWidth()
    )
}
