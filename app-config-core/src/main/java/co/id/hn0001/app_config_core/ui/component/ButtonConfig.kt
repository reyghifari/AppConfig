package co.id.hn0001.app_config_core.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import co.id.hn0001.app_config_core.ui.theme.Dimens
import co.id.hn0001.app_config_core.ui.theme.ConfigAppTheme

@Composable
fun ButtonConfig(
    modifier: Modifier = Modifier,
    label: String = "Button",
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    Button(
        onClick,
        modifier
            .height(Dimens.x12)
            .fillMaxWidth(),
        enabled
    ) {
        Text(label)
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonPreview() {
    ConfigAppTheme {
        ButtonConfig()
    }
}