package app.pokedex.shared.ui.home.impl.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import app.pokedex.shared.ui.Res
import app.pokedex.shared.ui.dna
import app.pokedex.shared.ui.electric
import app.pokedex.shared.ui.evolutions
import app.pokedex.shared.ui.home.impl.HomeScreen.Intent
import app.pokedex.shared.ui.location
import app.pokedex.shared.ui.locations
import app.pokedex.shared.ui.moves
import app.pokedex.shared.ui.pokeball
import app.pokedex.shared.ui.pokemons
import app.pokedex.shared.ui.theme.Black
import app.pokedex.shared.ui.theme.Blue300
import app.pokedex.shared.ui.theme.Blue500
import app.pokedex.shared.ui.theme.Green300
import app.pokedex.shared.ui.theme.Green500
import app.pokedex.shared.ui.theme.Red300
import app.pokedex.shared.ui.theme.Red500
import app.pokedex.shared.ui.theme.Yellow300
import app.pokedex.shared.ui.theme.Yellow500
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun HomeFeaturesSection(
    onIntent: (Intent) -> Unit,
    modifier: Modifier = Modifier
) {
    val padding = 8.dp
    Row(
        modifier = modifier.fillMaxWidth(),
    ) {
        Column(modifier = Modifier.weight(1f)) {
            HomeFeaturesItem(
                text = stringResource(Res.string.pokemons),
                painter = painterResource(Res.drawable.pokeball),
                startColor = Red300,
                endColor = Red500,
                onClick = { onIntent(Intent.NavigateToPokemons) },
                modifier = Modifier.padding(end = padding, bottom = padding)
            )
            HomeFeaturesItem(
                text = stringResource(Res.string.evolutions),
                painter = painterResource(Res.drawable.dna),
                startColor = Green300,
                endColor = Green500,
                onClick = { onIntent(Intent.NavigateToEvolution) },
                modifier = Modifier.padding(end = padding, top = padding)
            )
        }
        Column(modifier = Modifier.weight(1f)) {
            HomeFeaturesItem(
                text = stringResource(Res.string.moves),
                painter = painterResource(Res.drawable.electric),
                startColor = Yellow300,
                endColor = Yellow500,
                onClick = { onIntent(Intent.NavigateToMoves) },
                modifier = Modifier.padding(start = padding, bottom = padding)
            )
            HomeFeaturesItem(
                text = stringResource(Res.string.locations),
                painter = painterResource(Res.drawable.location),
                startColor = Blue300,
                endColor = Blue500,
                onClick = { onIntent(Intent.NavigateToLocations) },
                modifier = Modifier.padding(start = padding, top = padding)
            )
        }
    }
}

@Composable
internal fun HomeFeaturesItem(
    text: String,
    painter: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    startColor: Color = Color.Unspecified,
    endColor: Color = Color.Unspecified,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .clickable { onClick() }
            .background(Brush.linearGradient(listOf(startColor, endColor)))
            .padding(16.dp)
    ) {
        Text(
            text = text,
            color = Color.White,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
        )
        Box(contentAlignment = Alignment.Center) {
            Box(
                modifier = Modifier
                    .scale(1f, .5f)
                    .offset(y = 40.dp)
                    .size(40.dp)
                    .background(Brush.radialGradient(listOf(Black.copy(alpha = .3f), Color.Transparent)))
            )
            Image(
                painter = painter,
                contentDescription = text,
                modifier = Modifier
                    .size(40.dp)
            )
        }
    }
}