package testr.testr.com;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Kevin on 5/5/2018.
 */

public class TestAdapter extends BaseAdapter {

    private final Context mContext;
    private final Question[] q;

    public TestAdapter(Context context, Question[] q) {
        this.mContext = context;
        this.q = q;
    }

    @Override
    public int getCount() {
        return q.length;
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
        final Question question = this.q[i];

        if (view == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            view = layoutInflater.inflate(R.layout.layout_answer, null);
        }

        final TextView answer = (TextView) view.findViewById(R.id.answer);
        final TextView questionNumber = (TextView) view.findViewById(R.id.question_number);

        answer.setText(question.getAnswer());
        questionNumber.setText(question.getNumber());

        return view;
    }
}
