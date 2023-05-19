package com.example.ideer.memo;

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

public class NoteDetailsActivity extends AppCompatActivity {
    EditText titleEditText, contentEditText;
    ImageButton saveNoteBtn;
    TextView pageTitleTextView;
    String title,content,docId;
    TextView deleteNoteTextViewBtn;
    boolean isEditMode = false;
    FirebaseFirestore firestore;
    CollectionReference notesRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        titleEditText = findViewById(R.id.notes_title_text);
        contentEditText = findViewById(R.id.notes_content_text);
        saveNoteBtn = findViewById(R.id.save_note_btn);
        pageTitleTextView = findViewById(R.id.page_title);
        deleteNoteTextViewBtn = findViewById(R.id.delete_note_text_view_btn);

        //데이터 받아오기
        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        docId = getIntent().getStringExtra("docId");

        if(docId!=null && !docId.isEmpty()){
            isEditMode = true;
        }

        titleEditText.setText(title);
        contentEditText.setText(content);
        if(isEditMode){
            pageTitleTextView.setText("노트를 수정하세요");
            deleteNoteTextViewBtn.setVisibility(View.VISIBLE);
        }


        firestore = FirebaseFirestore.getInstance();
        notesRef = getCollectionReferenceForNotes();

        saveNoteBtn.setOnClickListener(v -> saveNote());
        deleteNoteTextViewBtn.setOnClickListener((v)-> deleteNoteFromFirebase());
    }

    void saveNote() {
        String noteTitle = titleEditText.getText().toString();
        String noteContent = contentEditText.getText().toString();
        if (noteTitle == null || noteTitle.isEmpty()) {
            titleEditText.setError("제목입력이 필요합니다");
            return;
        }
        Note note = new Note();
        note.setTitle(noteTitle);
        note.setContent(noteContent);
        note.setTimestamp(Timestamp.now());
        if(isEditMode){
            notesRef
                    .document(docId)
                    .set(note)
                    .addOnSuccessListener(documentReference -> {
                        Toast.makeText(NoteDetailsActivity.this, "저장되었습니다", Toast.LENGTH_SHORT).show();
                        finish();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(NoteDetailsActivity.this, "저장에 실패했습니다: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        }
        else{
            notesRef
                    .document()
                    .set(note)
                    .addOnSuccessListener(documentReference -> {
                        Toast.makeText(NoteDetailsActivity.this, "저장되었습니다", Toast.LENGTH_SHORT).show();
                        finish();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(NoteDetailsActivity.this, "저장에 실패했습니다: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        }

    }

    static CollectionReference getCollectionReferenceForNotes() {
        String uid = "my_uid"; // 고유한 UID 값 입력
        return FirebaseFirestore.getInstance().collection("users").document(uid).collection("my_notes");
    }

    static String timestampToString(Timestamp timestamp) {
        return new SimpleDateFormat("MM/dd/yyyy").format(timestamp.toDate());
    }

    void deleteNoteFromFirebase(){
        notesRef
                .document(docId)
                .delete()
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(NoteDetailsActivity.this, "삭제되었습니다", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(NoteDetailsActivity.this, "삭제에 실패했습니다: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });

    }
}
