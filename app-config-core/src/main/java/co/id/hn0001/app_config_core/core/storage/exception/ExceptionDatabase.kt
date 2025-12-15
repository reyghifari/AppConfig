package co.id.hn0001.app_config_core.core.storage.exception

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ExceptionEntity::class], version = 1, exportSchema = false)
abstract class ExceptionDatabase : RoomDatabase() {
    abstract fun dao(): ExceptionDAO
}