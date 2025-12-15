package co.id.hn0001.app_config

import android.content.Context
import co.id.hn0001.app_config.core.model.dropdown.ModelDropdown
import co.id.hn0001.app_config.core.storage.BaseURL
import co.id.hn0001.app_config.core.storage.SetupPref
import co.id.hn0001.app_config.core.util.ConfigNotification
import co.id.hn0001.app_config.core.util.exception.ExceptionLogging

object AppConfig {
    fun init(context: Context, finishDestination : String, listEnvironmentUrl: List<String>) {
        SetupPref(context).set {
            preferencesName = "hn0001_app_pref"
            packageException = "co.id.hn0001.app_config"
            packageProvider = "co.id.hn0001.app_config.provider"
            resetFinishDestination = finishDestination
            baseUrlEnvironment = listEnvironmentUrl.mapIndexed { index, url ->
                ModelDropdown(
                    id = index.toString(),
                    label = "ENVIRONMENT_$index",
                    value = url
                )
            }
        }
        ConfigNotification.init(context)
    }

    fun baseURL(context: Context, default: String): String = BaseURL().get(context, default)

    fun exceptionLog(context: Context) {
        ExceptionLogging(context).init()
    }
}
