package com.example.ideer.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.ideer.R

class DevLevelChoose : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dev_level_choose)

        val topic = intent.getStringExtra("topic") // Get the topic string from the Intent

        val buttonSets = listOf(
                listOf(R.id.low_button1, R.id.mid_button1, R.id.high_button1),
                listOf(R.id.low_button2, R.id.mid_button2, R.id.high_button2),
                listOf(R.id.low_button3, R.id.mid_button3, R.id.high_button3),
                listOf(R.id.low_button4, R.id.mid_button4, R.id.high_button4),
                listOf(R.id.low_button5, R.id.mid_button5, R.id.high_button5)
        )

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
