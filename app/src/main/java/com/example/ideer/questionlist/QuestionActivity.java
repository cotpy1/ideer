package com.example.ideer.questionlist;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ideer.R;
import com.example.ideer.chat.ChatActivity;
import com.example.ideer.databinding.ActivityQuestionBinding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionActivity extends AppCompatActivity {
    private ActivityQuestionBinding binding;
    private List<String> selectedQuestions;
    private String intentedTopic;
    private TextView selectedCountText;
    private Map<String, String> personLevels;
    private String person1Role;
    private String person2Role;
    private String person3Role;
    private String person4Role;
    private String person5Role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        selectedQuestions = new ArrayList<>();


        // onCreate 메서드 내부에서 초기화
        selectedCountText = findViewById(R.id.selected_count_text);






        // Intent에서 데이터 추출
        Intent intent = getIntent();
        if (intent != null) {
            intentedTopic = intent.getStringExtra("topic");
            personLevels = (HashMap<String, String>) (HashMap<?, ?>) intent.getSerializableExtra("personLevels");
            int personCount = intent.getIntExtra("personcount", 0);
            for (int i = 1; i <= personCount; i++) {
                String personRole = intent.getStringExtra("person" + i + "role");
                String personLevel = personLevels.get(String.valueOf(i));
                Log.d("Question Activity", "Person " + i + " - Role: " + personRole + ", Level: " + personLevel);
            }
        }

        // 추출한 데이터 확인
        Log.d("Question Activity", "Intended Topic: " + intentedTopic);

        //위에 찍힌 로그보고 이제 객체 생성하면 끝!
        //이 엑티비티로 전달된 정보는 주제,총 개발인원의 수,각 인원별 역할,각 인원별 난이도




        binding.startBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuestionActivity.super.finish();
            }
        });

        binding.arrowMoveForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestionActivity.this, ChatActivity.class);
                intent.putStringArrayListExtra("selectedQuestions", (ArrayList<String>) selectedQuestions);
                intent.putExtra("topic", intentedTopic);
                Bundle bundle = new Bundle();
                bundle.putSerializable("personLevels", (Serializable) personLevels);
                intent.putExtras(bundle);
                intent.putExtra("callAPI",true);
                startActivity(intent);
            }
        });

        binding.questionButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = "어떤 앱을 개발할까요?";
                toggleQuestionSelection(question);
            }
        });

        binding.questionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = "앱의 목적이 무엇인가요";
                toggleQuestionSelection(question);
            }
        });

        binding.questionButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = "어떤 방식으로 구현해야 하나요";
                toggleQuestionSelection(question);
            }
        });

        binding.questionButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = "어떤 방식으로 수익 창출을 해야하나요?";
                toggleQuestionSelection(question);
            }
        });

        binding.questionButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = "타겟 사용자는 누구인가요";
                toggleQuestionSelection(question);
            }
        });

        binding.questionButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(QuestionActivity.this);
                builder.setTitle("질문 입력");

                // 사용자 입력을 받기 위한 EditText 뷰 생성
                final EditText input = new EditText(QuestionActivity.this);
                builder.setView(input);

                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String question = input.getText().toString();
                        toggleQuestionSelection(question);
                    }
                });

                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });

    }

    private void toggleQuestionSelection(String question) {
        if (selectedQuestions.contains(question)) {
            selectedQuestions.remove(question);
        } else {
            selectedQuestions.add(question);
        }
        // toggleQuestionSelection 메서드 내부에서 호출하여 선택된 개수를 업데이트
        selectedCountText.setText(selectedQuestions.size() + "개 선택됨");
    }
}
