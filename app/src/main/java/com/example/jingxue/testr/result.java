package com.example.jingxue.testr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.app.Dialog;
import android.widget.TextView;
import android.graphics.drawable.ColorDrawable;
import java.util.Observable;
import java.util.Observer;


public class result extends AppCompatActivity implements Observer {
    model mModel;
    GridView gridView;
    TextView MarkView;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get Model instance
        mModel = model.getInstance();
        mModel.addObserver(this);

        setContentView(R.layout.activity_result);
        // set gridView
        // load data from resultQS ( student question result) to the grid view
        gridView = (GridView) findViewById(R.id.GridView_Result);
        final ArrayList<result_Q> resultQS;
        resultQS = new ArrayList<result_Q>(mModel.getResultQS());
        // load with Adapter
        ResultAdapter resultAdapter = new ResultAdapter(this, resultQS);
        gridView.setAdapter(resultAdapter);
        // set the mark text view
        MarkView = (TextView) findViewById(R.id.MarkView) ;
        MarkView.setText(mModel.getMark());

        // show the correct answer with dialog when click on the question
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                result_Q Q = resultQS.get(position);
                final Dialog dialog = new Dialog(context);

                dialog.setContentView(R.layout.dialog_correctanswer);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                // set TextView to show the correct answer of this question
                TextView text = (TextView) dialog.findViewById(R.id.dialog_correctanswer);
                text.setText("Correct Answer is" + "\n" + Q.getCorrect());
                // press ok to close the dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                // show dialog
                dialog.show();

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
        MarkView.setText(mModel.getMark());
    }
}
