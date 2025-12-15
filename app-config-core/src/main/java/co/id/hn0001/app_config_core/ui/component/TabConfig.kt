package co.id.hn0001.app_config_core.ui.component

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import co.id.hn0001.app_config_core.ui.theme.Dimens
import co.id.hn0001.app_config_core.ui.theme.ConfigAppTheme

@Composable
fun TabConfig(
    modifier: Modifier = Modifier,
    datas: List<String> = listOf("Tab 1", "Tab 2"),
    selected: Int = 0,
    onSelected: (Int) -> Unit = {},
) {
    var lSelected by remember { mutableStateOf(selected) }
    TabRow(
        modifier = modifier,
        selectedTabIndex = lSelected,
    ) {
        datas.forEachIndexed { i, s ->
            Tab(
                modifier = Modifier.height(Dimens.x12),
                selected = lSelected == i,
                onClick = {
                    lSelected = i
                    onSelected(i)
                }
            ) {
                Text(text = s)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TabPreview() {
    ConfigAppTheme {
        TabConfig()
    }
}