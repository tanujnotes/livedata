package com.example.cintaanalytics.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events_table")
data class Event(

    var uid: String,
    var name: String,
    var timestamp: Long

) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}