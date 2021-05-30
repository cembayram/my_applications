package com.example.loginregister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class SoruListeleme extends AppCompatActivity {
    RecyclerView rvQuestions;
    RecyclerView.LayoutManager layoutManager;
    QuestionAdapter questionAdapter;
    DatabaseHelper databaseHelper;
    List<Questions> questionsList =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soru_listeleme);

        databaseHelper =new DatabaseHelper(this);
        questionsList =databaseHelper.getAllQuestions();
        rvQuestions=findViewById(R.id.rvQuestions);
        rvQuestions.setHasFixedSize(true);
        layoutManager =new LinearLayoutManager(this);
        rvQuestions.setLayoutManager(layoutManager);
        questionAdapter =new QuestionAdapter(this,questionsList,rvQuestions);
        rvQuestions.setAdapter(questionAdapter);
        //commit denemesi

    }

}