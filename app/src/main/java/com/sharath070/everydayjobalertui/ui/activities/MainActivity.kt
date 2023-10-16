package com.sharath070.everydayjobalertui.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.sharath070.everydayjobalertui.R
import com.sharath070.everydayjobalertui.repository.Repository
import com.sharath070.everydayjobalertui.viewModels.MainViewModel
import com.sharath070.everydayjobalertui.viewModels.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModelFactory = MainViewModelFactory(Repository())
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

    }
}