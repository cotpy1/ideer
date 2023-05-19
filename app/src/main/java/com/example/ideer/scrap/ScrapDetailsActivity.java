package com.example.ideer.scrap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ideer.R;
import com.example.ideer.scrap.ScrapDetailsActivity;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;

public class ScrapDetailsActivity extends AppCompatActivity {
    EditText contentEditText;
    String content,docId;
    TextView deleteScrapTextViewBtn;
    boolean isEditMode = false;
    FirebaseFirestore firestore;
    CollectionReference scrapsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrap_detail);

        contentEditText = findViewById(R.id.content_scrap_view);
        deleteScrapTextViewBtn = findViewById(R.id.delete_scrap_text_view_btn);

        //데이터 받아오기
        content = getIntent().getStringExtra("conversationText");
        docId = getIntent().getStringExtra("docId");

        if(docId!=null && !docId.isEmpty()){
            isEditMode = true;
        }


        contentEditText.setText(content);
        if(isEditMode){
            deleteScrapTextViewBtn.setVisibility(View.VISIBLE);
        }


        firestore = FirebaseFirestore.getInstance();
        scrapsRef = getCollectionReferenceForScraps();

        deleteScrapTextViewBtn.setOnClickListener((v)-> deleteNoteFromFirebase());
    }
        void saveNote() {
            String scrapContent = contentEditText.getText().toString();

            Scrap scrap = new Scrap();
            scrap.setContent(scrapContent);
            scrap.setTimestamp(Timestamp.now());
            if (isEditMode) {
                scrapsRef
                        .document(docId)
                        .set(scrap)
                        .addOnSuccessListener(documentReference -> {
                            Toast.makeText(ScrapDetailsActivity.this, "저장되었습니다", Toast.LENGTH_SHORT).show();
                            finish();
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(ScrapDetailsActivity.this, "저장에 실패했습니다: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
            } else {
                scrapsRef
                        .document()
                        .set(scrap)
                        .addOnSuccessListener(documentReference -> {
                            Toast.makeText(ScrapDetailsActivity.this, "저장되었습니다", Toast.LENGTH_SHORT).show();
                            finish();
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(ScrapDetailsActivity.this, "저장에 실패했습니다: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
            }
        }

    static CollectionReference getCollectionReferenceForScraps() {
        String uid = "my_uid"; // 고유한 UID 값 입력
        return FirebaseFirestore.getInstance().collection("users").document(uid).collection("my_scraps");
    }

    static String timestampToString(Timestamp timestamp) {
        return new SimpleDateFormat("MM/dd/yyyy").format(timestamp.toDate());
    }

    void deleteNoteFromFirebase(){
        scrapsRef
                .document(docId)
                .delete()
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(ScrapDetailsActivity.this, "삭제되었습니다", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(ScrapDetailsActivity.this, "삭제에 실패했습니다: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });

    }
}
