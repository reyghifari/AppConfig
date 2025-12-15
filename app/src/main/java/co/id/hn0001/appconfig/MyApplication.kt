package co.id.hn0001.appconfig

import android.app.Application
import co.id.hn0001.app_config.AppConfig

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppConfig.init(this, "co.id.hn0001.appconfig.MainActivity", listOf(
            "https://dev.id/",
            "https://sit.id/",
            "https://uat.id/",
        ) )
        AppConfig.exceptionLog(this)
    }
}