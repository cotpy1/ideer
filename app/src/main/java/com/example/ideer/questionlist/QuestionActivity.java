package com.example.ideer.questionlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ideer.SelectedData;
import com.example.ideer.chat.ChatActivity;
import com.example.ideer.databinding.ActivityQuestionBinding;
import com.example.ideer.home.DevLevelChoose;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {
    private ActivityQuestionBinding binding;
    private String selectedQuestion;
    private List<String> selectedQuestions;
    private SelectedData selectedData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        // 이전 Activity에서 전달된 SelectedData 객체를 가져옴
        selectedData = getIntent().getParcelableExtra("selectedData");

        // 선택된 질문을 변수에 저장
        String chosenQuestion = "선택된 질문";

        // 선택된 질문을 SelectedData 객체에 저장
        selectedData.setChosenQuestion(chosenQuestion);


        binding.startBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestionActivity.this, DevLevelChoose.class);
                intent.putExtra("selectedData", String.valueOf(selectedData));
                startActivity(intent);
            }
        });

        binding.arrowMoveForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestionActivity.this, ChatActivity.class);
                intent.putStringArrayListExtra("selectedQuestions", (ArrayList<String>) selectedQuestions);
                startActivity(intent); // 다음 페이지로 이동
            }
        });

        binding.questionButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = "질문 1";
                toggleQuestionSelection(question);
            }
        });

        binding.questionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = "질문 2";
                toggleQuestionSelection(question);
            }
        });

        binding.questionButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = "질문 3";
                toggleQuestionSelection(question);
            }
        });

        binding.questionButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = "질문 4";
                toggleQuestionSelection(question);
            }
        });

        binding.questionButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = "질문 5";
                toggleQuestionSelection(question);
            }
        });

        binding.questionButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = "질문 6";
                toggleQuestionSelection(question);
            }
        });
    }

    private void toggleQuestionSelection(String question) {
        if (selectedQuestions.contains(question)) {
            selectedQuestions.remove(question);
        } else {
            selectedQuestions.add(question);
        }
    }
}
