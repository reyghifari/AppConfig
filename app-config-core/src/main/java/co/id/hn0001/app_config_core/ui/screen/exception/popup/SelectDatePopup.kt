package co.id.hn0001.app_config_core.ui.screen.exception.popup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import co.id.hn0001.app_config_core.ui.component.ButtonConfig
import co.id.hn0001.app_config_core.ui.component.DatePicker
import co.id.hn0001.app_config_core.ui.theme.Dimens
import co.id.hn0001.app_config_core.ui.theme.ConfigAppTheme
import java.util.Calendar

@Composable
fun SelectDatePopup(
    onDismissRequest: () -> Unit = {},
    setOnSubmit: (Calendar, Calendar) -> Unit = { _, _ -> },
) {
    var lDateStart: Calendar? by remember { mutableStateOf(null) }
    var lDateEnd: Calendar? by remember { mutableStateOf(null) }
    Dialog(onDismissRequest) {
        Column(
            Modifier
                .background(Color.White)
                .padding(Dimens.x3)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Pilih Tanggal",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier
                    .padding(top = Dimens.x5),
                text = "Pilih rentang waktu terjadinya exception"
            )
            Row(
                modifier = Modifier.padding(top = Dimens.x5)
            ) {
                DatePicker(
                    modifier = Modifier
                        .weight(1F)
                        .padding(end = Dimens.x2),
                    label = "Start",
                    hint = "Start",
                    setOnDateSetListener = {
                        it.set(Calendar.HOUR_OF_DAY, 0)
                        lDateStart = it
                    }
                )
                DatePicker(
                    modifier = Modifier
                        .weight(1F)
                        .padding(start = Dimens.x2),
                    label = "End",
                    hint = "End",
                    setOnDateSetListener = {
                        it.set(Calendar.HOUR_OF_DAY, 23)
                        lDateEnd = it
                    }
                )
            }
            ButtonConfig(
                modifier = Modifier.padding(top = Dimens.x5),
                label = "Simpan",
                enabled = lDateStart != null && lDateEnd != null,
                onClick = {
                    setOnSubmit(lDateStart!!, lDateEnd!!)
                    onDismissRequest()
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SelectDatePopupPreview() {
    ConfigAppTheme {
        SelectDatePopup()
    }
}