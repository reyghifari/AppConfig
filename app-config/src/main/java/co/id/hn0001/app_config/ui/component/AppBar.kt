package co.id.hn0001.app_config.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.id.hn0001.app_config.ui.theme.Dimens
import co.id.hn0001.app_config.ui.theme.ConfigAppTheme
import co.id.hn0001.app_config.ui.theme.Transparent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    title: String = "App Bar",
    prefixIcon: ImageVector? = null,
    onClickPrefix: () -> Unit = {},
    suffix: @Composable (() -> Unit)? = null,
    suffixIcon: ImageVector? = null,
    onClickSuffix: () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        modifier = modifier
            .fillMaxWidth()
            .border(0.dp, Transparent)
            .shadow(Dimens.elevationShadowView),
        title = {
            Text(
                title,
                maxLines = 2,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(horizontal = Dimens.x4),
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            if (prefixIcon != null) {
                IconButton(onClickPrefix) {
                    Icon(prefixIcon, "Appbar Suffix")
                }
            }
        },
        actions = {
            when (suffix) {
                null -> {
                    if (suffixIcon != null) {
                        IconButton(onClickSuffix) {
                            Icon(suffixIcon, "Appbar Suffix")
                        }
                    }
                }

                else -> suffix()
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun AppBarPreview() {
    ConfigAppTheme {
        AppBar()
    }
}