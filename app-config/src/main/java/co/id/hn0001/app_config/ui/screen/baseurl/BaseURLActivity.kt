package co.id.hn0001.app_config.ui.screen.baseurl

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
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
        Column {
            AppBar(
                title = "API Base URL",
                prefixIcon = Icons.Rounded.ArrowBack,
                onClickPrefix = {
                    onBackApplication()
                }
            )
            ConstraintLayout(
                Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                val (dropdown, note, button) = createRefs()
                val startGuideline = createGuidelineFromStart(Dimens.container)
                val endGuideline = createGuidelineFromEnd(Dimens.container)
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
                        .constrainAs(dropdown) {
                            start.linkTo(startGuideline)
                            end.linkTo(endGuideline)
                            top.linkTo(parent.top, Dimens.x10)
                            width = Dimension.fillToConstraints
                        },
                )
                Note(
                    note = "Default",
                    noteAnnotated = info,
                    modifier = Modifier
                        .padding(horizontal = Dimens.container)
                        .constrainAs(note) {
                            start.linkTo(startGuideline)
                            end.linkTo(endGuideline)
                            top.linkTo(dropdown.bottom, Dimens.container)
                        },
                )
                ButtonConfig(
                    label = "Simpan",
                    onClick = {
                        isOpenPopupResetUser = true
                    },
                    modifier = Modifier.constrainAs(button) {
                        start.linkTo(startGuideline)
                        end.linkTo(endGuideline)
                        linkTo(
                            note.bottom,
                            parent.bottom,
                            Dimens.container,
                            Dimens.container,
                            bias = 1F
                        )
                        width = Dimension.fillToConstraints
                    }
                )
            }
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