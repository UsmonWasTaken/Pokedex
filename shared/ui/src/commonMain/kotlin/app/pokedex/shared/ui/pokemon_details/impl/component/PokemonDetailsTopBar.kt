package app.pokedex.shared.ui.pokemon_details.impl.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import app.pokedex.shared.ui.Res
import app.pokedex.shared.ui.cd_favorite
import app.pokedex.shared.ui.cd_navigate_back
import app.pokedex.shared.ui.cd_unfavorite
import app.pokedex.shared.ui.pokemon_details.impl.PokemonDetailsScreen.Intent
import app.pokedex.shared.ui.pokemon_details.impl.state.PokemonDetailsState
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun PokemonDetailsTopBar(
    state: PokemonDetailsState,
    onIntent: (Intent) -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        navigationIcon = {
            IconButton(
                onClick = { onIntent(Intent.NavigateBack) }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = stringResource(Res.string.cd_navigate_back),
                )
            }
        },
        title = {},
        actions = {
            AnimatedVisibility(
                visible = state.isFavorite != null,
                enter = fadeIn() + slideInHorizontally(
                    initialOffsetX = { it / 2 }
                )
            ) {
                val isFavorite = requireNotNull(state.isFavorite)
                IconButton(
                    onClick = {
                        if (isFavorite) onIntent(Intent.UnFavorite) else onIntent(Intent.Favorite)
                    }
                ) {
                    Icon(
                        if (isFavorite) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                        contentDescription = if (isFavorite) stringResource(Res.string.cd_unfavorite) else stringResource(
                            Res.string.cd_favorite
                        ),
                        tint = if (isFavorite) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = Color.Transparent
        ),
        modifier = modifier.fillMaxWidth()
    )
}