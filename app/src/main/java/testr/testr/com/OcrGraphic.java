package testr.testr.com;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.google.android.gms.vision.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class OcrGraphic extends GraphicOverlay.Graphic implements Observer {

    private int mId;

    private static final int TEXT_COLOR = Color.WHITE;
    private static final int CORRECT_COLOR = Color.parseColor("#00FF00");
    private static final int INCORRECT_COLOR = Color.parseColor("#FF0000");

    private static Paint sRectPaint;
    private static Paint sTextPaint;
    private static Paint sCorrectPaint;
    private static Paint sIncorrectPaint;
    private final ModifiedTextBlock mText;

    OcrGraphic(GraphicOverlay overlay, ModifiedTextBlock text) {
        super(overlay);

        mText = text;

        if (sRectPaint == null) {
            sRectPaint = new Paint();
            sRectPaint.setColor(TEXT_COLOR);
            sRectPaint.setStyle(Paint.Style.STROKE);
            sRectPaint.setStrokeWidth(4.0f);
        }

        if (sCorrectPaint == null) {
            sCorrectPaint = new Paint();
            sCorrectPaint.setColor(CORRECT_COLOR);
            sCorrectPaint.setTextSize(54.0f);
            sCorrectPaint.setFakeBoldText(true);
        }

        if (sIncorrectPaint == null) {
            sIncorrectPaint = new Paint();
            sIncorrectPaint.setColor(INCORRECT_COLOR);
            sIncorrectPaint.setTextSize(54.0f);
            sIncorrectPaint.setFakeBoldText(true);
        }

        if (sTextPaint == null) {
            sTextPaint = new Paint();
            sTextPaint.setColor(TEXT_COLOR);
            sTextPaint.setTextSize(54.0f);
        }
        // Redraw the overlay, as this graphic has been added.
        postInvalidate();
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public ModifiedTextBlock getTextBlock() {
        return mText;
    }

    public boolean contains(float x, float y) {
        ModifiedTextBlock text = mText;
        if (text == null) {
            return false;
        }
        RectF rect = new RectF(text.getBoundingBox());
        rect.left = translateX(rect.left);
        rect.top = translateY(rect.top);
        rect.right = translateX(rect.right);
        rect.bottom = translateY(rect.bottom);
        return (rect.left < x && rect.right > x && rect.top < y && rect.bottom > y);
    }

    @Override
    public void draw(Canvas canvas) {
        ArrayList<Question> questions;

        Model mModel = Model.getInstance();
        mModel.addObserver(this);

        mModel.initObservers();

        questions = mModel.getCorrectAnswerSet();

        ModifiedTextBlock text = mText;
        if (text == null) {
            return;
        }

        // Draws the bounding box around the TextBlock.
        RectF rect = new RectF(text.getBoundingBox());
        rect.left = translateX(rect.left);
        rect.top = translateY(rect.top);
        rect.right = translateX(rect.right);
        rect.bottom = translateY(rect.bottom);
        canvas.drawRect(rect, sRectPaint);

        // Break the text into multiple lines and draw each one according to its own bounding box.
        List<? extends Text> textComponents = text.getComponents();
        for(int i = 0; i < textComponents.size(); i++) {
            float left = translateX(textComponents.get(i).getBoundingBox().left);
            float bottom = translateY(textComponents.get(i).getBoundingBox().bottom);

            String questionText = textComponents.get(i).getValue();

            String strippedQuestionText = questionText.replaceAll("\\s+","");

            String questionNumberText = strippedQuestionText.substring(0, 1);

            try {
                int questionNumber = Integer.parseInt(questionNumberText);

                String questionAnswer = strippedQuestionText.substring(1, 2).toUpperCase();

                ArrayList<Question> currentAnswers = mModel.getAnswer();

                currentAnswers.set(questionNumber - 1, new Question(questionNumber, questionAnswer));

                mModel.setAnswer(currentAnswers);

                String correctAnswer = questions.get(questionNumber - 1).getAnswer();

                if (questionAnswer.equals(correctAnswer)) {
                    canvas.drawText(textComponents.get(i).getValue() + " Correct", left, bottom, sCorrectPaint);
                } else {
                    canvas.drawText(textComponents.get(i).getValue() + " Incorrect", left, bottom, sIncorrectPaint);
                }
            }
            catch(Exception e) {
                canvas.drawText(textComponents.get(i).getValue(), left, bottom, sTextPaint);
            }
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        // not implemented
    }
}
