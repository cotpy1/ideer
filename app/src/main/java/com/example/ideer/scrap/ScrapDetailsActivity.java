package com.example.ideer.scrap;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ideer.R;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;

public class ScrapDetailsActivity extends AppCompatActivity {
    EditText  contentEditText;
    ImageButton saveNoteBtn;

    String content,docId;
    TextView deleteScrapTextViewBtn;
    boolean isEditMode = false;
    FirebaseFirestore firestore;
    CollectionReference notesRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrap_detail);

        contentEditText = findViewById(R.id.content_scrap_view);
        saveNoteBtn = findViewById(R.id.save_scrap_btn);
        deleteScrapTextViewBtn = findViewById(R.id.delete_scrap_text_view_btn);

        //데이터 받아오기
        content = getIntent().getStringExtra("gptresponse");
        docId = getIntent().getStringExtra("docId");

        if(docId!=null && !docId.isEmpty()){
            isEditMode = true;
        }

        contentEditText.setText(content);
        if(isEditMode){
            deleteScrapTextViewBtn.setVisibility(View.VISIBLE);
        }


        firestore = FirebaseFirestore.getInstance();
        notesRef = getCollectionReferenceForScraps();
        saveScrap();
        deleteScrapTextViewBtn.setOnClickListener((v)-> deleteScrapFromFirebase());
    }

    void saveScrap() {
        String noteContent = contentEditText.getText().toString();
        Scrap scrap = new Scrap();
        scrap.setContent(noteContent);
        scrap.setTimestamp(Timestamp.now());
        if(isEditMode){
            notesRef
                    .document(docId)
                    .set(scrap);

        }
        else{
            notesRef
                    .document()
                    .set(scrap);

        }

    }

    static CollectionReference getCollectionReferenceForScraps() {
        String uid = "my_uid"; // 고유한 UID 값 입력
        return FirebaseFirestore.getInstance().collection("users").document(uid).collection("my_scraps");
    }

    static String timestampToString(Timestamp timestamp) {
        return new SimpleDateFormat("MM/dd/yyyy").format(timestamp.toDate());
    }

    void deleteScrapFromFirebase(){
        notesRef
                .document(docId)
                .delete()
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(com.example.ideer.scrap.ScrapDetailsActivity.this, "삭제되었습니다", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(com.example.ideer.scrap.ScrapDetailsActivity.this, "삭제에 실패했습니다: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });

    }
}
