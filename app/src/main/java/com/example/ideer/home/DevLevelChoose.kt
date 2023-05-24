package com.example.ideer.home

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.ideer.R
import com.example.ideer.SelectedData
import com.example.ideer.databinding.ActivityDevLevelChooseBinding
import com.example.ideer.questionlist.QuestionActivity


class DevLevelChoose : AppCompatActivity() {
    private lateinit var binding: ActivityDevLevelChooseBinding
    private var selectedData: SelectedData? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDevLevelChooseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // 이전 Activity에서 전달된 SelectedData 객체를 가져옴
        // 이전 Activity에서 전달된 SelectedData 객체를 가져옴
        selectedData = intent.getParcelableExtra("selectedData")

        // 선택된 레벨을 변수에 저장

        // 선택된 레벨을 변수에 저장
        val chosenLevel = "선택된 레벨"

        // 선택된 레벨을 SelectedData 객체에 저장

        // 선택된 레벨을 SelectedData 객체에 저장
        selectedData!!.chosenLevel = chosenLevel


        val topic = intent.getStringExtra("topic") // Get the topic string from the Intent


        val buttonSets = listOf(
                listOf(R.id.low_button1, R.id.mid_button1, R.id.high_button1),
                listOf(R.id.low_button2, R.id.mid_button2, R.id.high_button2),
                listOf(R.id.low_button3, R.id.mid_button3, R.id.high_button3),
                listOf(R.id.low_button4, R.id.mid_button4, R.id.high_button4),
                listOf(R.id.low_button5, R.id.mid_button5, R.id.high_button5)
        )
        binding.startBackBtnDevLevel.setOnClickListener {
            val intent = Intent(this, ChooseTopicActivity::class.java)
            startActivity(intent)
        }
        binding.arrowMoveForwardDevLevel.setOnClickListener {
            val intent = Intent(this, QuestionActivity::class.java)
            startActivity(intent)
        }

        for (buttonSet in buttonSets) {
            val buttons = buttonSet.map { findViewById<Button>(it) }
            for (button in buttons) {
                button.setOnClickListener {
                    it.isSelected = true // Set the clicked button to the selected state
                    for (otherButton in buttons - it) { // Iterate over the other buttons
                        otherButton.isSelected = false // Set the other buttons to the unselected state
                    }
                }
            }
        }


    }
}
