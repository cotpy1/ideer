package com.example.ideer.home

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ideer.R
import com.example.ideer.chat.ChatActivity
import com.example.ideer.databinding.ActivityDevLevelChooseBinding
import com.example.ideer.questionlist.QuestionActivity


class DevLevelChoose : AppCompatActivity() {
    private lateinit var binding: ActivityDevLevelChooseBinding

    //여기서 우성원이 한것
    private var personLevels: HashMap<String, String> = HashMap()
    var intentedTopic:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDevLevelChooseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //intent 가져오기
        intentedTopic=intent.extras!!.getString("topic")

        val buttonSets = listOf(
                listOf(R.id.low_button1, R.id.mid_button1, R.id.high_button1),
                listOf(R.id.low_button2, R.id.mid_button2, R.id.high_button2),
                listOf(R.id.low_button3, R.id.mid_button3, R.id.high_button3),
                listOf(R.id.low_button4, R.id.mid_button4, R.id.high_button4),
                listOf(R.id.low_button5, R.id.mid_button5, R.id.high_button5)
        )

        binding.startBackBtnDevLevel.setOnClickListener {
            super.finish()
        }//뒤로가기는 intent보단 끝내기로 하는게 좋음 ㅎㅎ,안그러면 새로운 엑티비티가 실행되면서
        //기존 엑티비티로 가는게 아닌 새롭게 시작됨


        //넘어가는 데이터 주제,인원 수준,인원 수, 인원 역할
        binding.arrowMoveForwardDevLevel.setOnClickListener {
            val intent = Intent(this, QuestionActivity::class.java)
            intent.putExtra("topic", intentedTopic)
            intent.putExtra("personLevels", personLevels)

            val personCountString = binding.personcount.text.toString()
            val personCount = personCountString.toIntOrNull()

            if (personCount != null) {
                for (i in 1..personCount) {
                    val personRole = when (i) {
                        1 -> binding.person1.text.toString()
                        2 -> binding.person2.text.toString()
                        3 -> binding.person3.text.toString()
                        4 -> binding.person4.text.toString()
                        5 -> binding.person5.text.toString()
                        else -> ""
                    }
                    intent.putExtra("person${i}role", personRole)
                }
                intent.putExtra("personcount", personCount)
                startActivity(intent)
            } else {
                Toast.makeText(this, "유효한 숫자가 아닙니다", Toast.LENGTH_SHORT).show()
            }
        }


        for (i in 1..buttonSets.size) {
            val buttons = buttonSets[i-1].map { findViewById<ImageButton>(it) }
            for (button in buttons) {
                button.setOnClickListener {
                    // Set the clicked button to the selected state
                    it.isSelected = true

                    // Iterate over the other buttons
                    for (otherButton in buttons - it) {
                        // Set the other buttons to the unselected state
                        otherButton.isSelected = false
                    }

                    // Get the level associated with the button
                    val level = when (button.id) {
                        R.id.low_button1, R.id.low_button2, R.id.low_button3, R.id.low_button4, R.id.low_button5 -> "Low"
                        R.id.mid_button1, R.id.mid_button2, R.id.mid_button3, R.id.mid_button4, R.id.mid_button5 -> "Mid"
                        R.id.high_button1, R.id.high_button2, R.id.high_button3, R.id.high_button4, R.id.high_button5 -> "High"
                        else -> ""
                    }

                    // Store the person ID and level in the HashMap
                    personLevels[i.toString()] = level
                }
            }
        }



    }
}
