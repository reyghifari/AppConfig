package co.id.hn0001.app_config.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.id.hn0001.app_config.core.model.dropdown.ModelDropdown
import co.id.hn0001.app_config.ui.theme.Black
import co.id.hn0001.app_config.ui.theme.Dimens
import co.id.hn0001.app_config.ui.theme.Gray300
import co.id.hn0001.app_config.ui.theme.Gray400

@Composable
fun Dropdown(
    modifier: Modifier = Modifier,
    label: String = "Label",
    hint: String = "Hint",
    datas: List<ModelDropdown> = listOf(),
    selected: String = "",
    onSelected: (ModelDropdown) -> Unit = {},
) {
    var expanded by remember { mutableStateOf(false) }
    var value by remember { mutableStateOf(selected) }
    var inputName by remember { mutableStateOf(hint) }

    if (selected.isNotEmpty()) {
        val dataFiltered = datas.filter { it.value == selected }
        if (dataFiltered.isNotEmpty()) {
            inputName = dataFiltered[0].label
            onSelected(dataFiltered[0])
        }
    }

    LaunchedEffect(selected) {
        value = selected
    }

    Column(
        modifier = modifier.wrapContentSize()
    ) {
        Text(text = label, fontSize = 14.sp)

        Box {
            Row(
                modifier = Modifier
                    .height(Dimens.x16)
                    .fillMaxWidth()
                    .padding(vertical = Dimens.x2)
                    .border(1.dp, Gray300, RoundedCornerShape(Dimens.x2))
                    .clickable { expanded = true }
                    .padding(horizontal = Dimens.x3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = inputName,
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    color = if (value.isEmpty()) Gray400 else Black,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.width(Dimens.x2))

                Icon(
                    imageVector = Icons.Rounded.KeyboardArrowDown,
                    contentDescription = "Dropdown arrow"
                )
            }

            DropdownMenu(
                modifier = Modifier.fillMaxWidth(),
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                datas.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(item.label) },
                        onClick = {
                            expanded = false
                            value = item.value
                            inputName = item.label
                            onSelected(item)
                        },
                        trailingIcon = {
                            if (value == item.value) {
                                Icon(
                                    imageVector = Icons.Rounded.Check,
                                    contentDescription = "Selected"
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}
