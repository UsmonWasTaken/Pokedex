package app.pokedex.shared.ui.home.impl.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import app.pokedex.shared.domain.model.Video
import app.pokedex.shared.ui.home.impl.HomeScreen.Intent
import app.pokedex.shared.ui.theme.Black
import coil3.compose.AsyncImage

@Composable
internal fun HomeVideosSection(
    videos: List<Video>,
    onIntent: (Intent) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    // TODO: Fix the scroll issue on desktop
    LazyRow(
        contentPadding = contentPadding,
        modifier = modifier.fillMaxWidth()
    ) {
        items(
            items = videos,
            key = Video::id
        ) { video ->
            HomeVideoItem(
                video = video,
                onClick = { onIntent(Intent.PlayVideo(video)) },
            )
        }
    }
}

@Composable
internal fun HomeVideoItem(
    video: Video,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .width(220.dp)
            .clip(MaterialTheme.shapes.medium)
            .clickable { onClick() }
            .padding(10.dp)
    ) {
        Box(contentAlignment = Alignment.Center) {
            AsyncImage(
                model = video.imageUrl,
                contentDescription = video.title,
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(Black.copy(.5f), BlendMode.Darken),
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.5f)
                    .clip(MaterialTheme.shapes.medium)
            )

            Icon(
                Icons.Rounded.PlayArrow,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(30.dp),
            )

            Icon(
                Icons.Outlined.Circle,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(60.dp),
            )
        }

        Text(
            text = video.title,
            style = MaterialTheme.typography.titleSmall,

            )

        Text(
            text = "${video.year} | ${video.category} | ${video.details}",
            color = MaterialTheme.colorScheme.onBackground.copy(.8f),
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}