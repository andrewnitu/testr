package com.example.jingxue.testr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Observer;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.Observable;
import java.util.Observer;

public class AnswerEnter extends AppCompatActivity implements Observer{

    model mModel;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get Model instance
        mModel = model.getInstance();
        mModel.addObserver(this);
        setContentView(R.layout.activity_answer_enter);
        submit = (Button) findViewById(R.id.submit_answer) ;
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 ArrayList<Question> correct_answer;
                 ArrayList<Question> answer;
                 correct_answer = new ArrayList<Question>();
                 correct_answer.add(new Question(1,"B"));
                 correct_answer.add(new Question(2,"A"));
                 mModel.setCorrect_answer(correct_answer);
                 answer = new ArrayList<Question>();
                 answer.add(new Question(1,"A"));
                 answer.add(new Question(2,"A"));
                 mModel.setAnswer(answer);
                Intent intent = new Intent();
                intent.setClass(AnswerEnter.this, result.class);
                startActivity(intent);
            }
        });
        // Init observers
        mModel.initObservers();


    }

    @Override
    protected void onDestroy()    {
        super.onDestroy();
        Log.d("", "MainActivity: onDestory");
        // Remove observer when activity is destroyed.
        mModel.deleteObserver(this);
    }
    @Override
    public void update(Observable o, Object arg)    {
        // / Update
    }
}
