package com.example.jingxue.testr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class statics extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statics);
        String s = getIntent().getStringExtra("Total");
        textView = (TextView) findViewById(R.id.textView);
        textView.setText(s);

    }
}
