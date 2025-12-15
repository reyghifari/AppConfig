package co.id.hn0001.app_config_core.core.storage

import android.content.Context
import co.id.hn0001.app_config_core.core.Constants
import co.id.hn0001.app_config_core.core.util.Preferences

class Receiver {
    companion object {
        private var DATA_RECEIVER: String? = null
    }

    fun get(context: Context, default: String): String {
        if (DATA_RECEIVER == null) {
            DATA_RECEIVER = Preferences(context).get(Constants.PREF.RECEIVER, "")
        }
        return DATA_RECEIVER!!.ifEmpty { default }
    }

    fun set(context: Context, value: String) {
        DATA_RECEIVER = value
        Preferences(context).save(Constants.PREF.RECEIVER, value)
    }
}