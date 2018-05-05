package testr.testr.com;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kevin on 5/5/2018.
 */

public class TestAdapter extends BaseAdapter {

    private final Context mContext;
    private final ArrayList<Question> q;

    public TestAdapter(Context context, ArrayList<Question> q) {
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
        final Question question = this.q.get(i);

        if (view == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            view = layoutInflater.inflate(R.layout.layout_answer, null);
        }

        TextView answer = view.findViewById(R.id.answer);
        TextView questionNumber = view.findViewById(R.id.question_number);

        answer.setText(question.getAnswer());
        questionNumber.setText(String.valueOf(question.getNumber()));

        return view;
    }
}
