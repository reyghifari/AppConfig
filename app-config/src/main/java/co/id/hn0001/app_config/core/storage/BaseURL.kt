package co.id.hn0001.app_config.core.storage

import android.content.Context
import co.id.hn0001.app_config.core.Constants
import co.id.hn0001.app_config.core.util.Preferences

class BaseURL {
    companion object {
        private var BASE_URL: String? = null
    }

    fun get(context: Context, default: String): String {
        if (BASE_URL == null) {
            BASE_URL = Preferences(context).get(Constants.PREF.BASE_URL, "")
        }
        return BASE_URL!!.ifEmpty { default }
    }

    fun set(context: Context, value: String) {
        BASE_URL = value
        Preferences(context).save(Constants.PREF.BASE_URL, value)
    }
}