package com.example.ideer.scrap;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ideer.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.util.List;


public class ScrapAdapter extends RecyclerView.Adapter<ScrapAdapter.ScrapViewHolder> {
    private List<ScrapItem> scrapItemList;

    public ScrapAdapter(List<ScrapItem> scrapItemList) {
        this.scrapItemList = scrapItemList;
    }

    @NonNull
    @Override
    public ScrapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_scrap, parent, false);
        return new ScrapViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScrapViewHolder holder, int position) {
        ScrapItem scrapItem = scrapItemList.get(position);
        holder.bind(scrapItem);
    }

    @Override
    public int getItemCount() {
        return scrapItemList.size();
    }

    public class ScrapViewHolder extends RecyclerView.ViewHolder {
        private TextView questionText;
        private TextView answerText;

        public ScrapViewHolder(@NonNull View itemView) {
            super(itemView);
            answerText = itemView.findViewById(R.id.content_scrap_view);
        }

        public void bind(ScrapItem scrapItem) {
            answerText.setText(scrapItem.getAnswer());
        }
    }
}



