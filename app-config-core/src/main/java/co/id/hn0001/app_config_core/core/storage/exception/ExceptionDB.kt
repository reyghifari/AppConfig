package co.id.hn0001.app_config_core.core.storage.exception

import android.content.Context
import androidx.room.Room
import co.id.hn0001.app_config_core.core.Constants

class ExceptionDB {
    companion object {
        private var INSTANCE: ExceptionDatabase? = null

        fun getInstance(context: Context): ExceptionDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room
                    .databaseBuilder(
                        context.applicationContext,
                        ExceptionDatabase::class.java,
                        Constants.DATABASE.TB_EXCEPTION
                    )
                    .build()
            }
            return INSTANCE!!
        }
    }
}
