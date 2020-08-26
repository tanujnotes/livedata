package com.example.cintaanalytics

import android.app.Application
import com.example.cintaanalytics.db.Event
import com.example.cintaanalytics.db.EventDao
import com.example.cintaanalytics.db.EventDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class MainRepository(application: Application) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var eventDao: EventDao?

    init {
        val db = EventDatabase.getDatabase(application)
        eventDao = db?.eventDao()
    }

    fun getEvents() = eventDao?.getAllEvents()

    fun setEvent(event: Event) {
        launch { setEventBG(event) }
    }

    private suspend fun setEventBG(event: Event) {
        withContext(Dispatchers.IO) {
            eventDao?.insert(event)
        }
    }

}