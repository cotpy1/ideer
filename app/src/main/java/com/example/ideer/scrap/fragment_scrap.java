package com.example.ideer.scrap;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ideer.databinding.FragmentScrapBinding;
import com.example.ideer.scrap.ScrapDetailsActivity;
import com.example.ideer.scrap.ScrapAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;

public class fragment_scrap extends Fragment {

    private FragmentScrapBinding binding;
    ScrapAdapter scrapAdapter;
    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstancesState){
        binding = FragmentScrapBinding.inflate(inflater, container, false);// ScrapAdapter 초기화 메서드 호출
        setupRecyclerView();

        return binding.getRoot();
    }



    void setupRecyclerView() {
        Query query = ScrapDetailsActivity.getCollectionReferenceForScraps().orderBy("timestamp", Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<Scrap> options = new FirestoreRecyclerOptions.Builder<Scrap>().setQuery(query,Scrap.class).build();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        scrapAdapter = new ScrapAdapter(options,requireContext());
        binding.recyclerView.setAdapter(scrapAdapter);
    }
    @Override
    public void onStart() {
        super.onStart();
        scrapAdapter.startListening();
    }
    @Override
    public void onStop(){
        super.onStop();
        scrapAdapter.stopListening();
    }

    @Override
    public void onResume() {
        super.onResume();
        scrapAdapter.notifyDataSetChanged();
    }
}
