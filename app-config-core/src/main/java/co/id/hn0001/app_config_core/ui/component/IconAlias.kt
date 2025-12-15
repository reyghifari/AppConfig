package co.id.hn0001.app_config_core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import co.id.hn0001.app_config_core.core.extensions.harmoniousColor
import co.id.hn0001.app_config_core.ui.theme.Dimens
import co.id.hn0001.app_config_core.ui.theme.ConfigAppTheme

@Composable
fun IconAlias(
    alias: String = "IST",
    index: Int = 0,
) {
    val hColor = index.harmoniousColor()
    val aliasValidLength = alias.ifEmpty { "-" }
    val newAlias = when (aliasValidLength.length > 2) {
        true -> aliasValidLength.substring(0, 2)
        else -> aliasValidLength
    }
    Column(
        modifier = Modifier
            .size(Dimens.x12)
            .clip(CircleShape)
            .background(hColor.backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(newAlias, color = hColor.foregroundColor)
    }
}

@Preview(showBackground = true)
@Composable
fun IconPreview() {
    ConfigAppTheme {
        IconAlias()
    }
}