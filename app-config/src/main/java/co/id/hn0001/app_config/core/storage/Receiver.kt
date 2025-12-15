package co.id.hn0001.app_config.core.storage

import android.content.Context
import co.id.hn0001.app_config.core.Constants
import co.id.hn0001.app_config.core.util.Preferences

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