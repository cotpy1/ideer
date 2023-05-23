package com.example.ideer.questionlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ideer.chat.ChatActivity;
import com.example.ideer.databinding.ActivityQuestionBinding;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {
    private ActivityQuestionBinding binding;
    private String selectedQuestion;
    private List<String> selectedQuestions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        binding.arrowMoveBackward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // 이전 페이지로 돌아가기
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
