package com.example.cintaanalytics.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Event::class], version = 1)
abstract class EventDatabase : RoomDatabase() {

    abstract fun eventDao(): EventDao

    private var instance: EventDatabase? = null

    companion object {

        @Volatile
        private var INSTANCE: EventDatabase? = null

        fun getDatabase(context: Context): EventDatabase? {
            if (INSTANCE == null) {
                synchronized(EventDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            EventDatabase::class.java, "events_database"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}