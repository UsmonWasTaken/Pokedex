package app.pokedex.shared.ui.pokemons.impl.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import app.pokedex.shared.domain.model.Pokemon
import app.pokedex.shared.ui.theme.Blue300
import app.pokedex.shared.ui.theme.Blue500
import app.pokedex.shared.ui.theme.Green300
import app.pokedex.shared.ui.theme.Green500
import app.pokedex.shared.ui.theme.Red300
import app.pokedex.shared.ui.theme.Red500
import app.pokedex.shared.ui.theme.Yellow300
import app.pokedex.shared.ui.theme.Yellow500
import coil3.compose.AsyncImage

@Composable
internal fun PokemonGridItem(
    pokemon: Pokemon,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val brush = remember {
        Brush.linearGradient(
            listOf(
                listOf(Red300, Red500),
                listOf(Yellow300, Yellow500),
                listOf(Green300, Green500),
                listOf(Blue300, Blue500),
            ).random()
        )
    }

    Card(
        onClick = onClick,
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
        ),
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(brush = brush, alpha = .4f)
                .padding(10.dp)
        ) {
            AsyncImage(
                model = pokemon.imageUrl,
                contentDescription = pokemon.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.2f)
                    .fillMaxHeight()
            )

            Spacer(Modifier.height(14.dp))

            Text(
                text = pokemon.name.replaceFirstChar { it.uppercase() },
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.alpha(.8f)
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = pokemon.number,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.alpha(.4f)
            )
        }
    }
}