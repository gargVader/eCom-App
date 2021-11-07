package com.example.ecom.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.ecom.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStream
import java.util.concurrent.Executors


/**
 * Room is a database layer on top of an SQLite database.
 *
 * Room takes care of mundane tasks that you used to handle with an SQLiteOpenHelper.
 *
 * Room uses the DAO to issue queries to its database.
 *
 * By default, to avoid poor UI performance, Room doesn't allow you to issue queries on the main
 * thread. When Room queries return Flow, the queries are automatically run asynchronously on a
 * background thread.
 *
 * Room provides compile-time checks of SQLite statements.
 */
@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)

                            val itemList = getJSONData(context)
                            Executors.newSingleThreadScheduledExecutor().execute(object : Runnable {
                                override fun run() {
                                    instance?.getItemDao()?.insert(itemList)
                                }
                            })

                        }
                    })
                    .build()
            }
            // return instance
            return instance as AppDatabase
        }

        private fun getJSONData(context: Context): List<Item> {
            val jsonString = getJSONString(context)
            val mGson = Gson()
            val sType = object : TypeToken<List<Item>>() {}.type
            return mGson.fromJson(jsonString, sType)
        }

        // Stringifies the json
        private fun getJSONString(context: Context): String {
            val inputStream: InputStream = context.resources.openRawResource(R.raw.list)
            return inputStream.bufferedReader().use { it.readText() }
        }

    }

    abstract fun getItemDao(): ItemDao


}