package app.pokedex.shared.ui.notimplemented.impl.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.pokedex.shared.ui.Res
import app.pokedex.shared.ui.cd_navigate_back
import app.pokedex.shared.ui.notimplemented.impl.NotImplementedScreen.Intent
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun NotImplementedTopBar(
    onIntent: (Intent) -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = { },
        navigationIcon = {
            IconButton(
                onClick = {
                    onIntent(Intent.NavigateBack)
                }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = stringResource(Res.string.cd_navigate_back)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        modifier = modifier.fillMaxWidth()
    )
}