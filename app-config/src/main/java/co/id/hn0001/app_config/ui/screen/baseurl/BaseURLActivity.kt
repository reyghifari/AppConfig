package co.id.hn0001.app_config.ui.screen.baseurl

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import co.id.hn0001.app_config.core.base.BaseActivity
import co.id.hn0001.app_config.core.Constants
import co.id.hn0001.app_config.core.extensions.activityProcessor
import co.id.hn0001.app_config.ui.component.AppBar
import co.id.hn0001.app_config.ui.component.ButtonConfig
import co.id.hn0001.app_config.ui.component.Dropdown
import co.id.hn0001.app_config.ui.component.Note
import co.id.hn0001.app_config.ui.screen.baseurl.popup.ResetUserPopup
import co.id.hn0001.app_config.ui.theme.Dimens
import co.id.hn0001.app_config.ui.theme.ConfigAppTheme

class BaseURLActivity : BaseActivity() {

    @Composable
    override fun Content() {
        val vm: BaseURLViewModel = viewModel()
        var info by remember { mutableStateOf(AnnotatedString("")) }
        var baseURL by remember { mutableStateOf("null") }
        var isOpenPopupResetUser by remember { mutableStateOf(false) }
        val context: Context = this

        LaunchedEffect(true) {
            baseURL = vm.get(context)
            info = vm.setInfo(baseURL)
        }

        Column(modifier = Modifier.fillMaxSize()) {
            AppBar(
                title = "API Base URL",
                prefixIcon = Icons.Rounded.ArrowBack,
                onClickPrefix = {
                    onBackApplication()
                }
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = Dimens.container)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Dropdown(
                        label = "Environment",
                        hint = "Environment",
                        datas = vm.listENV(context),
                        selected = baseURL,
                        onSelected = {
                            baseURL = it.value
                            info = vm.setInfo(baseURL)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = Dimens.x10)
                    )

                    Note(
                        note = "Default",
                        noteAnnotated = info,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = Dimens.container)
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    ButtonConfig(
                        label = "Simpan",
                        onClick = {
                            isOpenPopupResetUser = true
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = Dimens.container)
                    )
                }
            }

            // Popup
            if (isOpenPopupResetUser) {
                ResetUserPopup(
                    onDismissRequest = {
                        isOpenPopupResetUser = false
                    },
                    onClickButton = {
                        isOpenPopupResetUser = false
                        vm.save(context, baseURL)
                        nextPage()
                    }
                )
            }
        }
    }

    private fun nextPage() {
        activityProcessor {
            putExtra(
                Constants.ACTIVITY_PROCESS.INTENT_PROCESS,
                Constants.ACTIVITY_PROCESS.PROCESS_RESET_USER_DATA
            )
            startActivity(this)
            finish()
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun ContentPreview() {
        ConfigAppTheme {
            Content()
        }
    }
}
