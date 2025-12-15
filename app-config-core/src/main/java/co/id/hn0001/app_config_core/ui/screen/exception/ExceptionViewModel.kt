package co.id.hn0001.app_config_core.ui.screen.exception

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.id.hn0001.app_config_core.core.model.dropdown.ModelDropdown
import co.id.hn0001.app_config_core.core.model.exception.ExceptionType
import co.id.hn0001.app_config_core.core.storage.SetupPref
import co.id.hn0001.app_config_core.core.storage.exception.ExceptionDB
import co.id.hn0001.app_config_core.core.storage.exception.ExceptionEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExceptionViewModel : ViewModel() {

    var stateListException by mutableStateOf(listOf<ExceptionEntity>())
    var selected by mutableStateOf(ExceptionEntity(0L, 0L))
    var exceptionType: String by mutableStateOf(ExceptionType.All.type)

    fun getExceptionLog(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            ExceptionDB.getInstance(context).dao().getAll().also {
                viewModelScope.launch {
                    stateListException = it
                }
            }
        }
    }

    fun getMainAppExceptionLog(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            ExceptionDB.getInstance(context).dao().getAll().also {
                viewModelScope.launch {
                    stateListException = it
                }
            }
        }
    }

    fun getExceptionBaseOnDate(
        context: Context,
        start: Long,
        end: Long,
        callback: (List<ExceptionEntity>) -> Unit,
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            ExceptionDB.getInstance(context).dao().getAllBetweenDate(start, end).also {
                viewModelScope.launch {
                    callback(it)
                }
            }
        }
    }

    fun deleteExceptionLog(context: Context, ids: List<Long>) {
        CoroutineScope(Dispatchers.IO).launch {
            ExceptionDB.getInstance(context).dao().delete(ids)
            ExceptionDB.getInstance(context).dao().getAll().also {
                viewModelScope.launch {
                    stateListException = it
                }
            }
        }
    }

    fun updateException(context: Context, type: String = exceptionType) {
        exceptionType = type
        when (type) {
            ExceptionType.All.type -> getExceptionLog(context)
        }
    }

    fun exceptionTypeList(context: Context): List<ModelDropdown> {
        return listOf(
            ModelDropdown("0", "All", ExceptionType.All.type),
        )
    }
}