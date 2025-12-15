package co.id.hn0001.app_config

import android.app.Activity
import android.content.Intent
import co.id.hn0001.app_config.core.base.BaseActivityProcess
import co.id.hn0001.app_config.core.storage.SetupPref


class ActivityProcess : BaseActivityProcess() {

    override fun onResetUserData() {
        nextPage()
        if (cacheDir.deleteRecursively()) {
            try {

            } catch (_: Exception) {
            }
            if (pref().edit().clear().commit()) {
                nextPage()
            }
        }
    }

    private fun pref() = getSharedPreferences(
        SetupPref(this).get().preferencesName,
        Activity.MODE_PRIVATE
    )

    private fun nextPage() {
        val i = Intent().setClassName(
            this,
            SetupPref(this).get().resetFinishDestination
        )
        i.flags =
            Intent.FLAG_ACTIVITY_REORDER_TO_FRONT or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(i)
        finish()
    }
}