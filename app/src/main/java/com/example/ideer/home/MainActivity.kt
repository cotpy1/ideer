package com.example.ideer.home


import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.PopupMenu
import androidx.appcompat.app.AlertDialog
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
        binding.noticeInfo.setOnClickListener {
            showNoticePopup()
        }
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

    private fun showNoticePopup() {
        val noticeText = "본 앱이 제공하는 모든 자료는 데이터마이닝에 기반하며, 기반 자료의 원 저작자의 저작권은 저작권법 제2장 제6조에 의해 보호됩니다. 본 앱으로 생성한 아이디어를 영리적으로 이용하는 것은 저작권의 침해로 인정될 수 있으며 이를 확인 하지 않은 앱 이용에 의한 앱 이용자의 피해에 대한 책임은 앱 이용자에게 있습니다.\n\n" +
                "① 편집저작물은 독자적인 저작물로서 보호된다. <개정 2003·5·27>\n" +
                "② 편집저작물의 보호는 그 편집저작물의 구성부분이 되는 소재의 저작권 그 밖의 이 법에 의하여 보호되는 권리에영향을 미치지 아니한다. <개정 2003·5·27>"

        val alertDialog = AlertDialog.Builder(this)
                .setTitle("알림")
                .setMessage(noticeText)
                .setPositiveButton("확인") { dialog, _ ->
                    dialog.dismiss()
                }
                .create()

        alertDialog.show()
    }
}
