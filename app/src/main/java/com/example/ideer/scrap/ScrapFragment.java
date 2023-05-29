package com.example.ideer.scrap;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ideer.R;

import java.util.ArrayList;
import java.util.List;

public class ScrapFragment extends Fragment {
    private List<ScrapItem> scrapItemList;
    private ScrapAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scrap, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

        scrapItemList = new ArrayList<>();  // 스크랩한 아이템 목록을 담을 리스트


        List<ScrapItem> savedItems = loadScrapItemsFromStorage();
        scrapItemList.addAll(savedItems);

        adapter = new ScrapAdapter(scrapItemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }

    private List<ScrapItem> loadScrapItemsFromStorage() {
        List<ScrapItem> scrapItems = new ArrayList<>();

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("scrap_data", Context.MODE_PRIVATE);
        int itemCount = sharedPreferences.getInt("item_count", 0);

        for (int i = 0; i < itemCount; i++) {
            String answer = sharedPreferences.getString("answer_" + i, "");

            if (!answer.isEmpty()) {
                ScrapItem scrapItem = new ScrapItem(answer);
                scrapItems.add(scrapItem);
            }
        }

        return scrapItems;
    }


    // 스크랩한 답변을 추가하는 메서드
    public void addScrapItem(ScrapItem item) {
        scrapItemList.add(item);
        adapter.notifyDataSetChanged();
        saveScrapItemsToStorage(scrapItemList); // 스크랩한 아이템을 저장
    }
    private void saveScrapItemsToStorage(List<ScrapItem> scrapItems) {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("scrap_data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("item_count", scrapItems.size());

        for (int i = 0; i < scrapItems.size(); i++) {
            ScrapItem scrapItem = scrapItems.get(i);

            editor.putString("answer_" + i, scrapItem.getAnswer());
        }

        editor.apply();
    }

    // ...
}


