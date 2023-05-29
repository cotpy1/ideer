package com.example.ideer.home

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ideer.R
import kotlin.random.Random
import kotlin.collections.ArrayList

class ChooseTopicAdapter(private val context: Context, private val arrayList: ArrayList<String>):
    RecyclerView.Adapter<ChooseTopicAdapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemHolder = LayoutInflater.from(parent.context).inflate(R.layout.list_choose_topic, parent, false)
        return ItemHolder(itemHolder)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val topicValue = arrayList[position]

        when (position) {
            in 0..2 -> holder.topicKeyimage.setBackgroundColor(Color.parseColor("#CCD5AE"))
            in 3..5 -> holder.topicKeyimage.setBackgroundColor(Color.parseColor("#D3E4CD"))
            in 6..8 -> holder.topicKeyimage.setBackgroundColor(Color.parseColor("#CCD5AE"))
            else -> holder.topicKeyimage.setBackgroundColor(Color.parseColor("#ADC2A9"))
        }

        holder.topicValueText.text = topicValue
        holder.topicValueText.gravity=Gravity.CENTER
        holder.itemView.setOnClickListener {
            when (position) {
                10 -> {
                    //팝업 띄우기,그리고 팝업에서 직접입력으로 주제 입력받고
                    //해당 입력받은 주제를 intent에 추가
                    val activity = it.context as? Activity
                    if (activity?.isFinishing == false) {
                        val alertDialog = AlertDialog.Builder(activity).apply {
                            setTitle("주제를 입력해주세요")
                            val input = EditText(it.context)
                            setView(input)
                            setPositiveButton("예") { _, _ ->
                                val enteredTopic = input.text.toString()
                                val intent = Intent(it.context, DevLevelChoose::class.java)
                                intent.putExtra("topic", enteredTopic)
                                it.context.startActivity(intent)
                            }
                            setNegativeButton("취소") { dialog, _ ->
                                dialog.cancel()
                            }
                        }.create()

                        alertDialog.show()
                    }
                }
                11 -> {
                    val intent = Intent(it.context, DevLevelChoose::class.java)
                    intent.putExtra("topic", arrayList[Random.nextInt(10)])
                    it.context.startActivity(intent)
                }
                else -> {
                    val intent = Intent(it.context, DevLevelChoose::class.java)
                    intent.putExtra("topic", arrayList[position])
                    it.context.startActivity(intent)
                }
            }

        }

    }


    override fun getItemCount(): Int {
        return arrayList.size
    }

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // assuming there are TextViews to show the topic key and value
        var topicKeyimage = itemView.findViewById<ImageView>(R.id.choose_topic_image)
        var topicValueText = itemView.findViewById<TextView>(R.id.textListTitle)
    }
}