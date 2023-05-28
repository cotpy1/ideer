package com.example.ideer.home

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ideer.R

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
            val intent = Intent(it.context, DevLevelChoose::class.java)
            intent.putExtra("topic", arrayList[position])
            it.context.startActivity(intent)
            //여기서 arraylist에 해당하는 주제를 알아서 보냄 position을 인덱스로
            //key값은 topic
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
