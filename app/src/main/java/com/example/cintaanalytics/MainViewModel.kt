package com.example.cintaanalytics

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import com.example.cintaanalytics.db.Event
import java.util.*

class MainViewModel(application: Application) : AndroidViewModel(application) {

    var uid: String

    init {
        val preferences: SharedPreferences = application.getSharedPreferences("CINTA_PREFS", 0);
        val userId = preferences.getString("UID", "")
        if (userId.isNullOrEmpty()) {
            uid = UUID.randomUUID().toString()
            preferences.edit().putString("UID", uid).apply()
        } else {
            uid = userId
        }
    }

    private var repository: MainRepository = MainRepository(application)

    fun getEvents() = repository.getEvents()

    private fun setEvent(event: Event) {
        repository.setEvent(event)
    }

    fun addEvent(eventName: String) {
        val event = Event(uid, eventName, System.currentTimeMillis())
        setEvent(event)
    }

}