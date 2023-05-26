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

    // 버튼 상태 추적
    val personButtonStates = mutableMapOf<String, String>()


    var intentedTopic:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDevLevelChooseBinding.inflate(layoutInflater)
        setContentView(binding.root)




        //intent 가져오기
        intentedTopic=intent.extras!!.getString("topic")
        //여기까지는 topic만 받아온것임




        // 이전 Activity에서 전달된 SelectedData 객체를 가져옴
        // 이전 Activity에서 전달된 SelectedData 객체를 가져옴
//        selectedData = intent.getParcelableExtra("selectedData")
//
//        // 선택된 레벨을 변수에 저장
//
//        // 선택된 레벨을 변수에 저장
//        val chosenLevel = "선택된 레벨"
//
//        // 선택된 레벨을 SelectedData 객체에 저장
//
//        // 선택된 레벨을 SelectedData 객체에 저장
//        selectedData!!.chosenLevel = chosenLevel


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

            intent.putExtra("choosentopic",intentedTopic)

            intent.putExtra("person1",binding.person1.text)
            intent.putExtra("person2",binding.person2.text)
            intent.putExtra("person3",binding.person3.text)
            intent.putExtra("person4",binding.person4.text)
            intent.putExtra("person5",binding.person5.text)

            // 각 person의 버튼 상태를 intent에 추가
            for ((person, buttonState) in personButtonStates) {
                intent.putExtra("${person}ButtonState", buttonState)
            }
            //이제 다음화면에서 위 값들을 intent로 받아서 객체생성하면됨
            startActivity(intent)

        }

        //뒤로가기 버튼일땐 intent로 넘기는것보단 그냥 현재 activity를 finsih 하는것 추천ㅎㅎ
        //왜냐하면 이렇게하면 새로운 엑티비티가 실행되는것이지 이전 엑티비티로 돌아가는것이 아님.엑티비티가 여러 분기로 나뉘어버림
//        binding.startBackBtnDevLevel.setOnClickListener {
//            super.finish()
//        }



// 각 버튼 세트에 대해
        for ((personIndex, buttonSet) in buttonSets.withIndex()) {
            val buttons = buttonSet.map { findViewById<Button>(it) }
            for (button in buttons) {
                button.setOnClickListener {
                    it.isSelected = true
                    for (otherButton in buttons - it) {
                        otherButton.isSelected = false
                    }

                    // person의 버튼 상태를 저장
                    val person = "person${personIndex + 1}"
                    personButtonStates[person] = it.id.toString()
                }
            }
        }


    }
}
