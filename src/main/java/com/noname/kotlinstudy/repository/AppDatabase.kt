package com.noname.kotlinstudy.repository

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.Database
import com.noname.kotlinstudy.model.People


@Database(entities = arrayOf(People::class, CustomRoom::class), version = 1, exportSchema = false)
@TypeConverters(CustomTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun customDao(): CustomDao
    abstract fun starDao(): StarDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        internal fun getDatabase(context: Context): AppDatabase? {
            if (INSTANCE == null) {

                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase::class.java, "alarm_database"
                        )
                            .build()
                    }

            }
            return INSTANCE

        }
    }
}