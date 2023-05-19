package com.example.ideer.memo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ideer.databinding.FragmentMemoBinding;
import com.example.ideer.memo.Note;
import com.example.ideer.memo.NoteAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;
import com.example.ideer.memo.NoteDetailsActivity;

public class fragment_memo extends Fragment {

    private FragmentMemoBinding binding;
    NoteAdapter noteAdapter;
    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstancesState){
        binding = FragmentMemoBinding.inflate(inflater, container, false);// noteAdapter 초기화 메서드 호출
        binding.addNoteBtn.setOnClickListener((view -> startActivity(new Intent(getActivity(), NoteDetailsActivity.class))));
        setupRecyclerView();

        return binding.getRoot();
    }



    void setupRecyclerView() {
        Query query = NoteDetailsActivity.getCollectionReferenceForNotes().orderBy("timestamp", Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<Note> options = new FirestoreRecyclerOptions.Builder<Note>().setQuery(query,Note.class).build();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        noteAdapter = new NoteAdapter(options,requireContext());
        binding.recyclerView.setAdapter(noteAdapter);
    }
    @Override
    public void onStart() {
        super.onStart();
        noteAdapter.startListening();
    }
    @Override
    public void onStop(){
        super.onStop();
        noteAdapter.stopListening();
    }

    @Override
    public void onResume() {
        super.onResume();
        noteAdapter.notifyDataSetChanged();
    }
}
