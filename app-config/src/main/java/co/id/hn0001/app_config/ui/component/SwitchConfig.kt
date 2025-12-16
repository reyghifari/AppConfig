package co.id.hn0001.app_config.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import co.id.hn0001.app_config.ui.theme.Dimens
import co.id.hn0001.app_config.ui.theme.ConfigAppTheme

@Composable
fun SwitchConfig(
    label: String = "Label",
    checked: Boolean = false,
    modifier: Modifier = Modifier,
    onCheckedChange: (Boolean) -> Unit = {},
) {
    var lChecked by remember { mutableStateOf(checked) }

    Row(
        modifier = modifier
            .height(Dimens.x12)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(Dimens.x3))

        Switch(
            checked = lChecked,
            onCheckedChange = {
                lChecked = it
                onCheckedChange(it)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SwitchPreview() {
    ConfigAppTheme {
        SwitchConfig()
    }
}

@Preview(showBackground = true)
@Composable
fun SwitchWithLongLabelPreview() {
    ConfigAppTheme {
        SwitchConfig(
            label = "This is a very long label that should be truncated with ellipsis"
        )
    }
}
