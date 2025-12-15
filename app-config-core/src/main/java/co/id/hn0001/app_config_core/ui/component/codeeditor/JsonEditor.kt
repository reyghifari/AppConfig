package co.id.hn0001.app_config_core.ui.component.codeeditor

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import co.id.hn0001.app_config_core.ui.theme.ConfigAppTheme

@Composable
fun JsonEditor(
    text: String = "",
    onTextChange: (String) -> Unit = {},
) {
    var isFromTextChange by remember { mutableStateOf(false) }
    AndroidView(
        factory = {
            CodeEditorView(it).apply {
                setStyleJSON()
                setOnTextChangeListener { code ->
                    isFromTextChange = true
                    onTextChange(code)
                }
            }
        },
        update = {
            if (isFromTextChange.not()) it.setText(text)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun JsonEditorPreview() {
    ConfigAppTheme {
        JsonEditor()
    }
}