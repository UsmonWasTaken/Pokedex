package app.pokedex.shared.ui.pokemon_details.impl.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Scale
import androidx.compose.material.icons.outlined.Straighten
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import app.pokedex.shared.ui.Res
import app.pokedex.shared.ui.height
import app.pokedex.shared.ui.weight
import org.jetbrains.compose.resources.stringResource
import java.util.Locale

@Composable
internal fun PokemonDetailsPokemonInfo(
    weight: Int,
    height: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colorScheme.outline.copy(.2f))
            .padding(horizontal = 12.dp, vertical = 16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            Row {
                Icon(
                    imageVector = Icons.Outlined.Scale,
                    contentDescription = null
                )
                Spacer(Modifier.width(4.dp))

                val weightInKg = weight / 10f

                Text(
                    text = String.format(Locale.getDefault(), "%.1f kg", weightInKg),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                )
            }

            Spacer(Modifier.height(4.dp))

            Text(
                text = stringResource(Res.string.weight),
                color = MaterialTheme.colorScheme.onBackground.copy(.8f),
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Box(
            modifier = Modifier
                .size(width = 1.dp, height = 30.dp)
                .background(color = MaterialTheme.colorScheme.outline)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            Row {
                Icon(
                    imageVector = Icons.Outlined.Straighten,
                    contentDescription = null,
                    modifier = Modifier.rotate(90f)
                )
                Spacer(Modifier.width(4.dp))

                val heightInMeter = height / 10f
                Text(
                    text = String.format(Locale.getDefault(), "%.1f m", heightInMeter),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                )
            }

            Spacer(Modifier.height(4.dp))

            Text(
                text = stringResource(Res.string.height),
                color = MaterialTheme.colorScheme.onBackground.copy(.8f),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}