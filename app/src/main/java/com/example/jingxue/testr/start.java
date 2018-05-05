package com.example.jingxue.testr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
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

public class start extends AppCompatActivity implements Observer{

    model mModel;
    Button start_test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get Model instance
        mModel = model.getInstance();
        mModel.addObserver(this);
        setContentView(R.layout.activity_start);
        start_test = (Button) findViewById(R.id.start_test) ;

        start_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(start.this, AnswerEnter.class);
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
