package com.example.ideer.main;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.ideer.R;
import com.example.ideer.memo.fragment_memo;
import com.example.ideer.scrap.ScrapFragment;
import com.example.ideer.scrap.ScrapFragment;
import com.google.android.material.tabs.TabLayout;

public class main extends AppCompatActivity {

    ScrapFragment fragment1;
    fragment_memo fragment2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatmemo_act);

        fragment1 = new ScrapFragment();
        fragment2 = new fragment_memo();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("스크랩"));
        tabs.addTab(tabs.newTab().setText("메모"));

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Log.d("MainActivity", "선택된 탭: " + position);

                Fragment selected = null;
                if(position == 0){
                    selected = fragment1;
                } else {
                    selected = fragment2;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}