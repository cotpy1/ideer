package com.example.ideer.home

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.ideer.R
import com.example.ideer.databinding.ActivityChooseTopicBinding

class ChooseTopicActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChooseTopicBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChooseTopicBinding.inflate(layoutInflater)
        setContentView(binding.root)




    }

}