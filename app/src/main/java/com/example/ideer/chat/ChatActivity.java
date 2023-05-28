package com.example.ideer.chat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ideer.R;
import com.example.ideer.databinding.ActivityChatBinding;
import com.example.ideer.main.main;
import com.example.ideer.scrap.fragment_scrap;
import com.google.firebase.firestore.FirebaseFirestore;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ChatActivity extends AppCompatActivity {
    private ActivityChatBinding binding;
    private List<Message> messageList;
    private MessageAdapter messageAdapter;
    fragment_scrap fragment_scrap = new fragment_scrap();
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private OkHttpClient client = new OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS).build();
    private ChatViewModel chatViewModel;
    private StringBuilder chatContext = new StringBuilder();
    private List<String> selectedQuestions;
    private String intentedTopic;
    private TextView selectedCountText;
    private Map<String, String> personLevels;
    private String person1Role;
    private String person2Role;
    private String person3Role;
    private String person4Role;
    private String person5Role;


    // ChatActivity 내부

    private FirebaseFirestore db;
    private String previousQuestion = "";
    private int previousDifficultyLevel = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = FirebaseFirestore.getInstance();
        Intent intent = getIntent();
        if (intent != null) {
            selectedQuestions = intent.getStringArrayListExtra("selectedQuestions");
            intentedTopic = intent.getStringExtra("topic");
            personLevels = (HashMap<String, String>) (HashMap<?, ?>) intent.getSerializableExtra("personLevels");
            int personCount = intent.getIntExtra("personcount", 0);
            for (int i = 1; i <= personCount; i++) {
                String personRole = intent.getStringExtra("person" + i + "role");
                String personLevel = personLevels.get(String.valueOf(i));
                Log.d("ChatActivity", "Person " + i + " - Role: " + personRole + ", Level: " + personLevel);
            }
        }
        Log.d("ChatActivity", "Intended Topic: " + intentedTopic);



        chatViewModel = new ViewModelProvider(this).get(ChatViewModel.class);
        messageList = chatViewModel.getMessageList();
        messageAdapter = new MessageAdapter(messageList);
        binding.recyclerView.setAdapter(messageAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setStackFromEnd(true);
        binding.recyclerView.setLayoutManager(llm);
        binding.startMenuBtn.setOnClickListener((v) ->{
            showMenuOptions();
        } );
        if (intent != null) {
            String initialQuestion = intent.getStringExtra("question");
            if (initialQuestion != null && !initialQuestion.isEmpty()) {
                addToChat(initialQuestion, Message.SENT_BY_ME);
                callAPI(initialQuestion);
            }
        }
        String question = "공학 프로젝트 아이디어 1개 생성\n" +
                "주제 :"+ intentedTopic +
                "인원 수:"+ personLevels +
                "주의 사항 : 예시를 1개 이상 서술해야 한다. 이대로 개발을 시작할 수 있는 완성된 아이디어여야 한다. 앱 제작의 문제 상황과 목적을 제시하여야 한다. 구현 과정 및 인원별 역할을 절차 순으로 제시해야 한다.\n" +
                "필수 답변 요소: " +selectedQuestions +
                "Detail: 매우 상세\n" +
                "\n" +
                "형식 예시\n" +
                "\"앱 이름\"\n" +
                "\n" +
                "문제 상황(1문장) :\n" +
                " · 문제상황\n" +
                "\n" +
                "개발 목적(1문장) :\n" +
                " · 개발 목적\n" +
                "\n" +
                "타겟 사용자(1문장) :\n" +
                " · 20대\n" +
                "\n" +
                "수익 창출 방법(1문장)\n" +
                " · 광고\n" +
                "\n" +
                "핵심 기능(2문장 이하) :\n" +
                " · \n" +
                " ·\n" +
                "\n" +
                "핵심 상세(5문장 이하) :\n" +
                " · \n" +
                " ·\n" +
                " ·\n" +
                " ·\n" +
                " ·\n" +
                "\n" +
                "예시(2문장 이하) :\n" +
                " ·\n" +
                " ·\n" +
                "\n" +
                "구현(7문장 이하) :\n" +
                " 1.\n" +
                " 2.\n" +
                " 3.\n" +
                " 4.\n" +
                " 5.\n" +
                " 6.\n" +
                " 7.";
        addToChat(question, Message.SENT_BY_ME);
        callAPI(question);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_fragment, menu);
        return true;
    }
    private String getCurrentQuestion() {
        if (previousQuestion != null) {
            return previousQuestion;
        } else {
            return "";
        }
    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.start_menu_btn) {
            // 메뉴 버튼 클릭 시 동작 수행
            showMenuOptions(); // 메뉴 항목 표시 작업 수행
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showMenuOptions() {
        // 메뉴 항목을 표시하는 작업 수행
        PopupMenu popupMenu = new PopupMenu(this, binding.startMenuBtn);
        popupMenu.getMenuInflater().inflate(R.menu.menu_fragment, popupMenu.getMenu()); // 수정된 메뉴 리소스 사용

        // 메뉴 항목 클릭 이벤트 처리
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.scrap_page) {
                    // 스크랩 페이지로 이동하는 작업 수행
                    Intent intent = new Intent(ChatActivity.this, main.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.scrap) {
                    // 채팅 대화 스크랩하는 작업 수행
                    scrapChatConversation();
                    return true;
                } else if (id == R.id.refresh) {
                    // 채팅 대화 내용을 갱신하는 작업 수행
                    String question = getCurrentQuestion();
                    addToChat(question, Message.SENT_BY_ME);
                    callAPI(question);
                    return true;
                } else if (id == R.id.difficulty_down) {
                    // 난이도를 낮추는 작업 수행
                    if (!previousQuestion.isEmpty()) {
                        previousDifficultyLevel--;
                        requestLowerDifficultyQuestion();
                    } else {
                        showToast("이전 질문이 없습니다.");
                    }
                    return true;
                } else if (id == R.id.difficulty_up) {
                    // 난이도를 높이는 작업 수행
                    if (!previousQuestion.isEmpty()) {
                        previousDifficultyLevel++;
                        requestHigherDifficultyQuestion();
                    } else {
                        showToast("이전 질문이 없습니다.");
                    }
                    return true;
                }

                return false;
            }
        });

        popupMenu.show();
    }

    private void requestHigherDifficultyQuestion() {
        String question = getCurrentQuestion(); // 이전 질문 가져오기
        String newQuestion = " 더 어려운"+question;
        addToChat(newQuestion, Message.SENT_BY_ME); // 새로운 질문을 대화에 추가
        callAPI(newQuestion); // 새로운 질문에 대한 API 호출
    }
    private void requestLowerDifficultyQuestion() {
        String question = getCurrentQuestion(); // 이전 질문 가져오기
        String newQuestion = "더 쉬운"+question;
        addToChat(newQuestion, Message.SENT_BY_ME); // 새로운 질문을 대화에 추가
        callAPI(newQuestion); // 새로운 질문에 대한 API 호출
    }



    private void scrapChatConversation() {
        String conversationText = convertChatConversationToText();

        // Firestore에 대화 내용 저장
        Map<String, Object> data = new HashMap<>();
        data.put("conversationText", conversationText);
        db.collection("users").document("my_uid").collection("my_scraps")
                .add(data)
                .addOnSuccessListener(documentReference -> {
                    String scrapId = documentReference.getId();
                    Fragment fragment = new fragment_scrap();
                    Bundle bundle = new Bundle();
                    bundle.putString("scrapId", scrapId);
                    fragment.setArguments(bundle);

                    //프래그먼트 호출 및 전환
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(android.R.id.content, fragment);
                    fragmentTransaction.commit();
                })
                .addOnFailureListener(e -> {
                    showToast("스크랩에 실패했습니다.");
                });
    }






    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private String convertChatConversationToText() {
        StringBuilder textBuilder = new StringBuilder();

        for (int i = 0; i < messageList.size(); i++) {
            Message message = messageList.get(i);
            String sender = message.getSentBy();
            String content = message.getMessage();

            textBuilder
                    .append(sender).append(": ").append(content).append("\n");
        }

        return textBuilder.toString();
    }







    private void addToChat(String message, String sentBy) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                chatViewModel.addMessage(new Message(message, sentBy));
                messageAdapter.notifyDataSetChanged();
                binding.recyclerView.smoothScrollToPosition(messageAdapter.getItemCount());
            }
        });
    }







    void addResponse(String response){
        messageList.remove(messageList.size()-1);
        addToChat(response,Message.SENT_BY_BOT);
    }

    void callAPI(String question) {
        previousQuestion = question;


        // okhttp
        messageList.add(new Message("입력중..", Message.SENT_BY_BOT));

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("model", "gpt-3.5-turbo");
            JSONArray messageArr = new JSONArray();
            JSONObject obj = new JSONObject();
            obj.put("role", "user");
            obj.put("content", question);
            messageArr.put(obj);

            jsonBody.put("messages", messageArr);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        RequestBody body = RequestBody.create(jsonBody.toString(), JSON);
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .header("Authorization", "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")
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                addResponse("좀만 더 생각해 볼께요 조금 이따 다시 물어봐주세요^^");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response.body().string());
                        JSONArray jsonArray = jsonObject.getJSONArray("choices");
                        String result = jsonArray.getJSONObject(0).getJSONObject("message").getString("content");
                        addResponse(result.trim());
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    addResponse("좀만 더 생각해 볼께요 조금 이따 다시 물어봐주세요^^");
                }
            }
        });
    }




}
