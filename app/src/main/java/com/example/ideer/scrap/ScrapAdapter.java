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
import com.example.ideer.scrap.Scrap;
import com.example.ideer.scrap.ScrapDetailsActivity;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;


public class ScrapAdapter extends FirestoreRecyclerAdapter<Scrap, ScrapAdapter.ScrapViewHolder> {
    Context context;
    public ScrapAdapter(@NonNull FirestoreRecyclerOptions<Scrap> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ScrapViewHolder holder, int position, @NonNull Scrap scrap) {
        holder.contentTextView.setText(scrap.getContent());
        holder.timestampTextView.setText(ScrapDetailsActivity.timestampToString(scrap.getTimestamp()));

        holder.itemView.setOnClickListener((v)->{
            Intent intent = new Intent(context, ScrapDetailsActivity.class);
            intent.putExtra("conversationText", scrap.getContent());
            String docId = this.getSnapshots().getSnapshot(position).getId();
            intent.putExtra("docId", docId);
            context.startActivity(intent);
        });
    }


    @NonNull
    @Override
    public ScrapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_scrap,parent,false);
        return new ScrapViewHolder(view);
    }

    class ScrapViewHolder extends RecyclerView.ViewHolder{
        TextView contentTextView,timestampTextView;
        public ScrapViewHolder(@NonNull View itemView) {
            super(itemView);
            contentTextView = itemView.findViewById(R.id.content_scrap_view);
            timestampTextView = itemView.findViewById(R.id.note_timestamp_scrap_view);

        }
    }
}
