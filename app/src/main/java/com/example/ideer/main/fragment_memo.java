package com.example.ideer.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ideer.R;
import com.example.ideer.databinding.FragmentMemoBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class fragment_memo extends Fragment {

    private FragmentMemoBinding binding;
    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstancesState){
        binding = FragmentMemoBinding.inflate(inflater, container, false);
        binding.addNoteBtn.setOnClickListener((view -> startActivity(new Intent(getActivity(), NoteDetailsActivity.class))));
        return binding.getRoot();
    }


}
