package testr.testr.com;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


public class ResultsActivity extends AppCompatActivity implements Observer {
    Model mModel;
    GridView gridView;
    TextView MarkView;
    final Context context = this;
    Button scanOther;
    Button seeClassResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get Model instance
        mModel = Model.getInstance();
        mModel.addObserver(this);
        setContentView(R.layout.activity_result);
        //set up answer and correct answer
        // by intent
        //ArrayList<Question> answer = new ArrayList<>();
        //answer.add(new Question(1, "A"));
        //ArrayList<Question> answer = mModel.getAnswer();
        //mModel.setAnswer(answer);
        //ArrayList<Question> correct_answer = mModel.getCorrectAnswerSet();
        //mModel.setCorrectAnswerSet(correct_answer);
        // set gridView
        // load data from resultQS ( student question ResultsActivity) to the grid view
        gridView = (GridView) findViewById(R.id.GridView_Result);
        scanOther = (Button) findViewById(R.id.scanOther);
        scanOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ResultsActivity.this, CameraActivity.class);
                startActivity(intent);
            }
        });

        seeClassResult = (Button) findViewById(R.id.seeClassResult);
        seeClassResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ResultsActivity.this, Graph.class);
                startActivity(intent);
            }
        });

        final ArrayList<result_Q> resultQS;
        resultQS = new ArrayList<result_Q>(mModel.getResultQS());
        // load with Adapter
        ResultAdapter resultAdapter = new ResultAdapter(this, resultQS);
        gridView.setAdapter(resultAdapter);
        // set the mark text view
        MarkView = findViewById(R.id.MarkView) ;
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
