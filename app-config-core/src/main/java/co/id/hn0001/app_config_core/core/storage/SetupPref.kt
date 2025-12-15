package co.id.hn0001.app_config_core.core.storage

import android.content.Context
import co.id.hn0001.app_config_core.core.Constants
import co.id.hn0001.app_config_core.core.model.dropdown.ModelDropdown
import co.id.hn0001.app_config_core.core.util.Preferences
import com.google.gson.Gson

class SetupPref(private val context: Context) {
    data class Model(
        var preferencesName: String = "",
        var packageException: String = "",
        var resetFinishDestination: String = "",
        var packageProvider: String = "",
        var baseUrlEnvironment: List<ModelDropdown> = listOf(),
    )

    fun set(instance: Model.() -> Unit) {
        val data = Model()
        instance(data)
        Preferences(context).save(Constants.PREF.SETUP, Gson().toJson(data))
    }

    fun get(): Model {
        val data = Preferences(context).get(Constants.PREF.SETUP, "{}")
        return Gson().fromJson(data, Model::class.java)
    }
}