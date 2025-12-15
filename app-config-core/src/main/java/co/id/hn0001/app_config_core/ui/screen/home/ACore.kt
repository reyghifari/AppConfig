package co.id.hn0001.app_config_core.ui.screen.home

import android.content.Intent
import androidx.activity.viewModels
import co.id.hn0001.app_config_core.core.base.BaseActivity

abstract class ACore : BaseActivity() {
    protected val homeVM: HomeViewModel by viewModels()

    protected fun Class<*>.next() {
        startActivity(Intent(this@ACore, this))
    }
}