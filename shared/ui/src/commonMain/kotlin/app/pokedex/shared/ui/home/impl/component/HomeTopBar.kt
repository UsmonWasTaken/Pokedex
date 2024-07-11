package app.pokedex.shared.ui.home.impl.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.pokedex.shared.ui.Res
import app.pokedex.shared.ui.cd_about
import app.pokedex.shared.ui.cd_favorite_pokemons
import app.pokedex.shared.ui.home.impl.HomeScreen.Intent
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun HomeTopBar(
    onIntent: (Intent) -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {},
        actions = {
            IconButton(
                onClick = {
                    onIntent(Intent.NavigateToFavorites)
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = stringResource(Res.string.cd_favorite_pokemons)
                )
            }

            IconButton(
                onClick = {
                    onIntent(Intent.NavigateToAbout)
                },
            ) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = stringResource(Res.string.cd_about)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        modifier = modifier.fillMaxWidth()
    )
}