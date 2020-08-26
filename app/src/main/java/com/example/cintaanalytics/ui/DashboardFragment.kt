package com.example.cintaanalytics.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cintaanalytics.MainViewModel
import com.example.cintaanalytics.R
import com.example.cintaanalytics.db.Event
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = activity?.run {
            ViewModelProvider(this).get(MainViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        val adapter = AnalyticsAdapter()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        initViewModel(viewModel, adapter)
    }


    private fun initViewModel(viewModel: MainViewModel, appAdapter: AnalyticsAdapter) {
        viewModel.getEvents()?.observe(viewLifecycleOwner, Observer<List<Event>> {
            if (it.isNullOrEmpty()) {
                return@Observer
            }
            appAdapter.setAppList(it)
        })
    }
}