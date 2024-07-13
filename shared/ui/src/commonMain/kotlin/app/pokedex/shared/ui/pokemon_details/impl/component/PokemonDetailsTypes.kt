package app.pokedex.shared.ui.pokemon_details.impl.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import app.pokedex.shared.domain.model.PokemonInfo
import app.pokedex.shared.ui.theme.Bug
import app.pokedex.shared.ui.theme.Dark
import app.pokedex.shared.ui.theme.Dragon
import app.pokedex.shared.ui.theme.Electric
import app.pokedex.shared.ui.theme.Fairy
import app.pokedex.shared.ui.theme.Fighting
import app.pokedex.shared.ui.theme.Fire
import app.pokedex.shared.ui.theme.Flying
import app.pokedex.shared.ui.theme.Ghost
import app.pokedex.shared.ui.theme.Grass
import app.pokedex.shared.ui.theme.Gray400
import app.pokedex.shared.ui.theme.Ground
import app.pokedex.shared.ui.theme.Ice
import app.pokedex.shared.ui.theme.Poison
import app.pokedex.shared.ui.theme.Psychic
import app.pokedex.shared.ui.theme.Rock
import app.pokedex.shared.ui.theme.Steel
import app.pokedex.shared.ui.theme.Water

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun PokemonDetailsTypesRow(
    types: List<PokemonInfo.Type>,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier,
    ) {
        types.forEach { type ->
            PokemonDetailsTypeItem(type)
        }
    }
}

@Composable
internal fun PokemonDetailsTypeItem(
    type: PokemonInfo.Type,
    modifier: Modifier = Modifier,
    containerColor: Color = getTypeColor(type),
) {
    Text(
        text = type.name.replaceFirstChar(Char::uppercaseChar),
        color = containerColor,
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier
            .border(1.dp, containerColor, CircleShape)
            .padding(horizontal = 10.dp, vertical = 4.dp)
    )
}

private fun getTypeColor(type: PokemonInfo.Type): Color = when (type.name) {
    "fighting" -> Fighting
    "flying" -> Flying
    "poison" -> Poison
    "ground" -> Ground
    "rock" -> Rock
    "bug" -> Bug
    "ghost" -> Ghost
    "steel" -> Steel
    "fire" -> Fire
    "water" -> Water
    "grass" -> Grass
    "electric" -> Electric
    "psychic" -> Psychic
    "ice" -> Ice
    "dragon" -> Dragon
    "fairy" -> Fairy
    "dark" -> Dark
    else -> Gray400
}