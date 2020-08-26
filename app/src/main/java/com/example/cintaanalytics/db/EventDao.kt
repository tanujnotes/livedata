package com.example.cintaanalytics.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EventDao {

    @Insert
    fun insert(event: Event)

    @Query("DELETE FROM events_table")
    fun deleteAllEvents()

    @Query("SELECT * FROM events_table ORDER BY timestamp DESC")
    fun getAllEvents(): LiveData<List<Event>>

}