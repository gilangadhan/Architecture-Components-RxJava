package id.co.imastudio.architecturecomponent.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

/**
 * Created by gilan on 2/8/2018.
 */
@Database(entities = arrayOf(User::class), version = 3)
abstract class AppDatabase : RoomDatabase() {

    abstract fun itemDao(): UserDao

    companion object {

        @Volatile
        private var database: AppDatabase? = null


        fun getDatabase(context: Context): AppDatabase = database ?: synchronized(this) {
            database ?: buildDatabase(context).also { database = it }
        }


        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "user.db")
                        .build()

    }

}
