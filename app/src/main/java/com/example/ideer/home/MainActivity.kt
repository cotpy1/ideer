package com.example.ideer.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ideer.R
import com.example.ideer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Start 버튼 클릭 시 ChooseTopicActivity로 전환
        binding.button.setOnClickListener {
            val intent = Intent(this, ChooseTopicActivity::class.java)
            startActivity(intent)
        }
    }
}
