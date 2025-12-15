package co.id.hn0001.app_config_core.ui.screen.exception

import android.app.Activity
import androidx.navigation.NavHostController
import co.id.hn0001.app_config_core.core.base.BaseActivity

interface ExceptionInterface {
    val vm: ExceptionViewModel
    val navigation: NavHostController?
    val baseActivity: BaseActivity
    val activity: Activity

    fun export(start: Long, end: Long)
}