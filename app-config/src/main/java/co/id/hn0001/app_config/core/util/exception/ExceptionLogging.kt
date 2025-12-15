package co.id.hn0001.app_config.core.util.exception

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Process
import androidx.appcompat.app.AppCompatActivity
import co.id.hn0001.app_config.core.model.exception.ExceptionType
import co.id.hn0001.app_config.core.storage.SetupPref
import co.id.hn0001.app_config.core.storage.exception.ExceptionEntity
import com.google.gson.Gson
import kotlin.system.exitProcess

class ExceptionLogging(private val context: Context) : Thread.UncaughtExceptionHandler {

    companion object {
        const val EXTRA_DATA = "data_exception"
        private const val PREF_NAME = "exception_prefs"
        private const val KEY_PENDING_EXCEPTION = "pending_exception"
    }

    private val defaultUEH = Thread.getDefaultUncaughtExceptionHandler()
    private val fileType get() = ".kt|java:"
    private val appID get() = SetupPref(context).get().packageException
    private var strException: String = ""

    fun init() {
        Thread.setDefaultUncaughtExceptionHandler(this)
        // Cek apakah ada pending exception dari crash sebelumnya
        checkPendingException()
    }

    override fun uncaughtException(thread: Thread, event: Throwable) {
        val date = System.currentTimeMillis()
        val exceptionData = ExceptionEntity(
            id = date,
            dateMillis = date,
            message = event.stackTraceToString()
        )

        var isLogging = false

        exceptionData.message
            .split("\n")
            .filter { it.isMainFile() }
            .forEach {
                if (isLogging.not()) {
                    val fileName = it.substringAfter("(").substringBefore(":")
                    exceptionData.fileName = fileName
                    exceptionData.lineNumber = it.substringAfter("$fileName:").substringBefore(")")
                    exceptionData.className = it.replaceBefore(appID, "").substringBefore("(")
                    exceptionData.exceptionType = ExceptionType.MainApp.type
                    isLogging = true
                }
            }

        // PRIORITAS 2: Jika tidak ada MainApp, cari file Android lainnya
        if (isLogging.not()) {
            exceptionData.message
                .split("\n")
                .filter { it.isAndroidFile() }
                .forEach {
                    if (isLogging.not()) {
                        val fileName = it.substringAfter("(").substringBefore(":")
                        exceptionData.fileName = fileName
                        exceptionData.lineNumber = it.substringAfter("$fileName:").substringBefore(")")
                        exceptionData.className = it.replaceBefore(appID, "").substringBefore("(")
                        exceptionData.exceptionType = ExceptionType.All.type
                        isLogging = true
                    }
                }
        }

        if (isLogging) {
            val jsonData = Gson().toJson(exceptionData)

            // 1. Simpan ke SharedPreferences (backup - instant)
            saveExceptionToPrefs(jsonData)

            // 2. Set alarm untuk proses ke database
            setAlarm(jsonData)
        }

        // Tunggu sebentar agar proses selesai
        try {
            Thread.sleep(500)
        } catch (e: InterruptedException) {
            // ignore
        }

        // Force kill app
        Process.killProcess(Process.myPid())
        exitProcess(1)
    }

    private fun setAlarm(exString: String) {
        strException = exString
        try {
            (context.getSystemService(AppCompatActivity.ALARM_SERVICE) as AlarmManager)
                .setExact(
                    AlarmManager.RTC_WAKEUP,
                    System.currentTimeMillis() + 10_000L,
                    getPendingIntent()
                )
        } catch (e: Exception) {
            // Jika gagal set alarm, data sudah tersimpan di SharedPrefs
            e.printStackTrace()
        }
    }

    private fun getPendingIntent(): PendingIntent {
        return PendingIntent.getBroadcast(
            context,
            100,
            getIntent(),
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_CANCEL_CURRENT
        )
    }

    private fun getIntent(): Intent {
        return Intent(context, ExceptionReceiver::class.java)
            .putExtra(EXTRA_DATA, strException)
    }

    private fun saveExceptionToPrefs(jsonData: String) {
        try {
            val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            prefs.edit()
                .putString(KEY_PENDING_EXCEPTION, jsonData)
                .commit()  // Gunakan commit() bukan apply() agar synchronous
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun checkPendingException() {
        try {
            val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            val pendingException = prefs.getString(KEY_PENDING_EXCEPTION, null)

            if (pendingException != null) {
                val intent = Intent(context, ExceptionReceiver::class.java)
                    .putExtra(EXTRA_DATA, pendingException)
                context.sendBroadcast(intent)

                prefs.edit()
                    .remove(KEY_PENDING_EXCEPTION)
                    .apply()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun String.isMainFile() = contains(appID) && isAndroidFile()
    private fun String.isAndroidFile() = contains(Regex(fileType))
}
