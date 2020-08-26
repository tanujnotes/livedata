package com.example.cintaanalytics

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.cintaanalytics.db.Event

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: MainRepository = MainRepository(application)

    fun getEvents() = repository.getEvents()

    private fun setEvent(event: Event) {
        repository.setEvent(event)
    }

    fun addEvent(eventName: String) {
        val event = Event("sdf", eventName, System.currentTimeMillis())
        setEvent(event)
    }

}