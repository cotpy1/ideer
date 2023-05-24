package com.example.ideer.home

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import com.example.ideer.R
import com.example.ideer.chat.ChatActivity
import com.example.ideer.databinding.ActivityMainBinding
import com.example.ideer.home.ChooseTopicActivity
import com.example.ideer.main.main

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
        binding.startMenuBtn.setOnClickListener { v -> showMenuOptions() }
    }

    private fun showMenuOptions() {
        // 메뉴 항목을 표시하는 작업 수행
        val popupMenu = PopupMenu(this, binding.startMenuBtn)
        popupMenu.menuInflater.inflate(R.menu.menu_fragment_main, popupMenu.menu) // 수정된 메뉴 리소스 사용

        // 메뉴 항목 클릭 이벤트 처리
        popupMenu.setOnMenuItemClickListener { item ->
            val id = item.itemId

            if (id == R.id.scrap_page) {
                // 스크랩 페이지로 이동하는 작업 수행
                val intent = Intent(this@MainActivity, main::class.java)
                startActivity(intent)
                true
            } else {
                false
            }
        }

        // 메뉴가 표시되도록 명시적으로 호출
        popupMenu.show()
    }
}
