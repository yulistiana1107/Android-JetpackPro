package com.yulistiana.jetpackpro.lastsubmission.ui.home

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yulistiana.jetpackpro.lastsubmission.R
import com.yulistiana.jetpackpro.lastsubmission.viewmodel.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(
            this@MainActivity,
            factory
        ).get(MainViewModel::class.java)

        setSupportActionBar(toolbar)
        supportActionBar?.elevation = 0F

        val navigation: BottomNavigationView = findViewById(R.id.bottom_nav)
        val navigationController = findNavController(R.id.nav_host_fragment)

        val barConfiguration = AppBarConfiguration.Builder(
            R.id.movie_navigation,
            R.id.tvshow_navigation,
            R.id.favorite_navigation
        ).build()

        setupActionBarWithNavController(navigationController, barConfiguration)
        navigation.setupWithNavController(navigationController)
    }
}
