package com.example.jingxue.testr;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultAdapter extends BaseAdapter {

    private final Context mContext;
    private final ArrayList<result_Q> q;

    public ResultAdapter(Context context, ArrayList<result_Q> q) {
        this.mContext = context;
        this.q = q;
    }

    @Override
    public int getCount() {
        return q.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final result_Q question = this.q.get(i);

        if (view == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            view = layoutInflater.inflate(R.layout.result_layout, null);
        }


        final TextView answer = view.findViewById(R.id.answer);
        final TextView questionNumber = view.findViewById(R.id.number);

        answer.setText(question.getAnswer());
        if(question.getResult() == "False"){
            answer.setTextColor(Color.WHITE);

        }
        questionNumber.setText(String.valueOf(question.getNumber()));


        return view;
    }

}
