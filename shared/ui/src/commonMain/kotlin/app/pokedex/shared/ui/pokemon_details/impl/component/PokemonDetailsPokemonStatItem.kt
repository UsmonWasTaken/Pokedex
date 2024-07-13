package app.pokedex.shared.ui.pokemon_details.impl.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import app.pokedex.shared.domain.model.PokemonInfo
import app.pokedex.shared.ui.theme.Green300
import app.pokedex.shared.ui.theme.Yellow400
import app.pokedex.shared.ui.util.AnimatableSaver
import kotlin.math.roundToInt

@Composable
internal fun PokemonDetailsPokemonStatItem(
    stat: PokemonInfo.Stat,
    modifier: Modifier = Modifier,
) {
    val animationProgress = rememberSaveable(saver = AnimatableSaver) {
        Animatable(initialValue = 0f)
    }

    LaunchedEffect(Unit) {
        animationProgress.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 8 * stat.value, easing = LinearEasing)
        )
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Text(
            text = stat.name,
            color = MaterialTheme.colorScheme.onBackground.copy(.8f),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(.3f)
        )

        Text(
            text = (stat.value * animationProgress.value).roundToInt().toString(),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.weight(.2f)
        )

        val progress = stat.value.toFloat() / stat.maxValue.toFloat()
        val animatedProgress = progress * animationProgress.value

        val progressColor = if (progress >= .5f) Green300 else Yellow400
        val progressTrackColor = MaterialTheme.colorScheme.outline.copy(.2f)

        Box(
            modifier = Modifier
                .weight(.5f)
                .height(10.dp)
                .drawBehind {
                    drawRoundRect(
                        color = progressTrackColor,
                        topLeft = Offset.Zero,
                        size = size,
                        cornerRadius = CornerRadius(size.height, size.height),
                    )

                    drawRoundRect(
                        color = progressColor,
                        topLeft = Offset.Zero,
                        size = Size(width = size.width * animatedProgress, height = size.height),
                        cornerRadius = CornerRadius(size.height, size.height),
                    )
                }
        )
    }
}
