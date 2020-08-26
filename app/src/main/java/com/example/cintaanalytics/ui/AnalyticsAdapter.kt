package com.example.cintaanalytics.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cintaanalytics.R
import com.example.cintaanalytics.db.Event
import kotlinx.android.synthetic.main.adapter_events.view.*
import java.text.SimpleDateFormat
import java.util.*

class AnalyticsAdapter : RecyclerView.Adapter<AnalyticsAdapter.ViewHolder>() {

    private var eventsList: List<Event> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_events, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(eventsList[position])
    }

    override fun getItemCount(): Int = eventsList.size

    fun setAppList(appsList: List<Event>) {
        this.eventsList = appsList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(event: Event) = with(itemView) {
            uid.text = event.uid
            eventName.text = event.name
            timeStamp.text = formatTime(event.timestamp)
        }

        private fun formatTime(timeStamp: Long): String {
            return try {
                val dateFormat = SimpleDateFormat("dd MMM, yy HH:mm:ss", Locale.getDefault());
                val date = Date(timeStamp);
                dateFormat.format(date)
            } catch (e: Exception) {
                "Error!"
            }
        }
    }
}