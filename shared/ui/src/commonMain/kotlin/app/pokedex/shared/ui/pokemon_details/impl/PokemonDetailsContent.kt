package app.pokedex.shared.ui.pokemon_details.impl

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import app.pokedex.shared.domain.model.PokemonInfo
import app.pokedex.shared.ui.pokemon_details.impl.PokemonDetailsScreen.Intent
import app.pokedex.shared.ui.pokemon_details.impl.component.PokemonDetailsPokemonInfo
import app.pokedex.shared.ui.pokemon_details.impl.component.PokemonDetailsPokemonStatItem
import app.pokedex.shared.ui.pokemon_details.impl.component.PokemonDetailsTopBar
import app.pokedex.shared.ui.pokemon_details.impl.component.PokemonDetailsTypesRow
import app.pokedex.shared.ui.pokemon_details.impl.state.PokemonDetailsState
import coil3.compose.AsyncImage

@Composable
internal fun PokemonDetailsContent(
    state: PokemonDetailsState,
    onIntent: (Intent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        AsyncImage(
            model = state.imageUrl,
            contentDescription = state.name,
            contentScale = ContentScale.FillWidth,
            colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(3f) }),
            modifier = Modifier
                .widthIn(max = 800.dp)
                .fillMaxWidth(.9f)
                .wrapContentHeight(Alignment.Top, true)
                .scale(1f, 1.8f)
                .blur(70.dp, BlurredEdgeTreatment.Unbounded)
                .alpha(.5f)
        )

        Scaffold(
            topBar = {
                PokemonDetailsTopBar(
                    state = state,
                    onIntent = onIntent,
                )
            },
            containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.7f),
        ) { padding ->
            Box(
                modifier = Modifier
                    .padding(padding)
                    .padding(20.dp)
            ) {
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    item("pokemon_image") {
                        AsyncImage(
                            model = state.imageUrl,
                            contentDescription = state.name,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .widthIn(max = 500.dp)
                                .fillMaxWidth()
                                .aspectRatio(1.2f)
                                .fillMaxHeight()
                        )
                    }

                    item("pokemon_name") {
                        Text(
                            text = state.name.replaceFirstChar(Char::uppercaseChar),
                            style = MaterialTheme.typography.displaySmall,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    item("pokemon_number") {
                        Text(
                            text = state.number,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    item("pokemon_abilities") {
                        AnimatedVisibility(
                            visible = state.types != null,
                            enter = slideInVertically() + fadeIn(),
                        ) {
                            PokemonDetailsTypesRow(types = requireNotNull(state.types))

                        }
                    }

                    item("pokemon_info") {
                        AnimatedVisibility(
                            visible = state.weight != null && state.height != null,
                            enter = slideInVertically() + fadeIn(),
                        ) {
                            PokemonDetailsPokemonInfo(
                                weight = requireNotNull(state.weight),
                                height = requireNotNull(state.height),
                                modifier = Modifier
                                    .padding(top = 18.dp)
                                    .fillMaxWidth(.9f)
                            )
                        }
                    }

                    item("spacer") {
                        Spacer(modifier = Modifier.height(12.dp))
                    }

                    items(
                        items = state.stats ?: listOf(),
                        key = PokemonInfo.Stat::name,
                    ) { stat ->
                        PokemonDetailsPokemonStatItem(
                            stat = stat,
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                        )
                    }
                }
            }
        }
    }
}
