package testr.testr.com;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Observable;
import java.util.Observer;

public class Start extends AppCompatActivity implements Observer {
    Model mModel;
    Button start_test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get Model instance
        mModel = Model.getInstance();
        mModel.addObserver(this);
        setContentView(R.layout.activity_start);
        start_test = findViewById(R.id.start_test) ;

        start_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Start.this, TestInput.class);
                startActivity(intent);

            }
        });
        // Init observers
        mModel.initObservers();
    }
    @Override
    protected void onDestroy()    {
        super.onDestroy();
        Log.d("", "MainActivity: onDest");
        // Remove observer when activity is destroyed.
        mModel.deleteObserver(this);
    }
    @Override
        public void update(Observable o, Object arg)    {
        // / Update
    }
}
