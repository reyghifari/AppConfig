package co.id.hn0001.app_config.ui.screen.exception.page.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import co.id.hn0001.app_config.ui.component.AppBar
import co.id.hn0001.app_config.ui.component.codeeditor.KotlinEditor
import co.id.hn0001.app_config.ui.screen.exception.ExceptionProvider
import co.id.hn0001.app_config.ui.theme.ConfigAppTheme

@Composable
fun DetailPage() {
    ExceptionProvider {
        Column {
            AppBar(
                title = "Exceptions Detail",
                prefixIcon = Icons.Rounded.ArrowBack,
                onClickPrefix = { navigation?.popBackStack() },
            )
            Column(
                Modifier
                    .fillMaxSize(),
            ) {
                KotlinEditor(vm.selected.message, false)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailPagePreview() {
    ConfigAppTheme {
        DetailPage()
    }
}