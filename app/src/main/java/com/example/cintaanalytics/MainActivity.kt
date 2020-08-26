package com.example.cintaanalytics

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.cintaanalytics.helpers.Constant
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.addEvent(Constant.EVENT_SESSION_START)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        initTabChangeListener(navController)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.addEvent(Constant.EVENT_SESSION_STOP)
    }

    private fun initTabChangeListener(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_home -> {
                    viewModel.addEvent(Constant.EVENT_TAB_HOME)
                }
                R.id.navigation_dashboard -> {
                    viewModel.addEvent(Constant.EVENT_TAB_DASH)
                }
                R.id.navigation_notifications -> {
                    viewModel.addEvent(Constant.EVENT_TAB_NOTIF)
                }
            }
        }
    }
}