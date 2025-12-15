package co.id.hn0001.app_config.core.extensions

import androidx.room.RoomDatabase
import net.zetetic.database.sqlcipher.SupportOpenHelperFactory

fun <T : RoomDatabase> RoomDatabase.Builder<T>.encrypt(): RoomDatabase.Builder<T> {
    val passphrase = "hpHxATOP84XaoihLRGmy".toByteArray()
    val factory = SupportOpenHelperFactory(passphrase)
    openHelperFactory(factory)
    return this
}
