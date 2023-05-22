package com.example.ideer.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ideer.R


class ChooseTopicActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var gridLayoutManager: GridLayoutManager? = null
    private var arrayList: ArrayList<String>? = null
    private var ChooseTopicAdapters: ChooseTopicAdapter? = null


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_topic)

        recyclerView = findViewById(R.id.ChooseTopicrecyclerGridView)
        gridLayoutManager = GridLayoutManager(applicationContext, 3,
                LinearLayoutManager.VERTICAL, false)
        recyclerView?.layoutManager = gridLayoutManager
        recyclerView?.setHasFixedSize(true)
        arrayList = ArrayList()
        ChooseTopicAdapters = ChooseTopicAdapter(applicationContext, arrayList!!)
        recyclerView?.adapter = ChooseTopicAdapters



        // Populate the arrayList with dummy data
        val dummyData = listOf("건강 · 운동","게임","교육","데이트","생산성","비지니스","여행·지역정보","음악·오디오","사진","식음료","직접 입력","랜덤")
        arrayList!!.addAll(dummyData)
        ChooseTopicAdapters!!.notifyDataSetChanged()
    }
}
