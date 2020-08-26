package com.example.cintaanalytics.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cintaanalytics.MainViewModel
import com.example.cintaanalytics.R
import com.example.cintaanalytics.helpers.Constant
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = activity?.run {
            ViewModelProvider(this).get(MainViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        homeButton1.setOnClickListener { viewModel.addEvent(Constant.EVENT_HOME_BUTTON_1) }
        homeButton2.setOnClickListener { viewModel.addEvent(Constant.EVENT_HOME_BUTTON_2) }
    }
}