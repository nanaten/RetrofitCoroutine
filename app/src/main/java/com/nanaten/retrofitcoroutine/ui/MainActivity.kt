package com.nanaten.retrofitcoroutine.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.nanaten.retrofitcoroutine.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Without Coroutine
        start_button_1.setOnClickListener {
            viewModel.getRepos()
        }

        // With Coroutine
        start_button_2.setOnClickListener {
            viewModel.getReposWithCoroutine()
        }

        viewModel.data.observe(this, Observer {
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
        })
    }
}
