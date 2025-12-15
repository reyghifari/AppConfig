package co.id.hn0001.app_config_core.core.util.exception

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import co.id.hn0001.app_config_core.core.storage.exception.ExceptionDB
import co.id.hn0001.app_config_core.core.storage.exception.ExceptionEntity
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExceptionReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null || intent == null) return

        val jsonData = intent.getStringExtra(ExceptionLogging.EXTRA_DATA) ?: return

        try {
            val exceptionData = Gson().fromJson(jsonData, ExceptionEntity::class.java)

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val db = ExceptionDB.getInstance(context)
                    db.dao().insert(exceptionData)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
