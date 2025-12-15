package co.id.hn0001.app_config.core.storage.exception

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import co.id.hn0001.app_config.core.Constants
import co.id.hn0001.app_config.core.model.exception.ExceptionType

@Entity(tableName = Constants.DATABASE.TB_EXCEPTION)
data class ExceptionEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "date_millis") var dateMillis: Long,
    @ColumnInfo(name = "file_name") var fileName: String = "",
    @ColumnInfo(name = "class_name") var className: String = "",
    @ColumnInfo(name = "line_number") var lineNumber: String = "",
    @ColumnInfo(name = "message") var message: String = "",
    @ColumnInfo(name = "type") var exceptionType: String = ExceptionType.MainApp.type,
)
