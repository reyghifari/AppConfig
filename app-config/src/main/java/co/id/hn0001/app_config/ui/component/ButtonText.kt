package co.id.hn0001.app_config.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import co.id.hn0001.app_config.ui.theme.Dimens
import co.id.hn0001.app_config.ui.theme.ConfigAppTheme
import co.id.hn0001.app_config.ui.theme.Orange

@Composable
fun ButtonText(
    label: String = "Button",
    suffix: ImageVector? = null,
    onClick: () -> Unit = {},
) {
    TextButton(
        onClick,
        Modifier
            .height(Dimens.x12)
    ) {
        Text(label)
        if (suffix != null) {
            Image(
                painter = rememberVectorPainter(image = suffix),
                colorFilter = ColorFilter.tint(Orange),
                contentDescription = "Image"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonTextPreview() {
    ConfigAppTheme {
        ButtonText()
    }
}