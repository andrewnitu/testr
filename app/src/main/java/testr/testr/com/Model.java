package testr.testr.com;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.Observer;


class Model extends Observable {
    private static final Model ourInstance = new Model();

    static Model getInstance() {
        return ourInstance;
    }

    private ArrayList<Question> correct_answer;
    private ArrayList<Question> answer = new ArrayList<>();
    private ArrayList<Question> result_student;
    private int mark = 0;
    private int count = 0;
    private String mark_s = "";
    private ArrayList<result_Q> resultQS;
    private ArrayList<Integer> studentScore = new ArrayList<Integer>();
    private ArrayList<String> studentScore_String = new ArrayList<String>();

    public ArrayList<Question> getAnswer() {
        return answer;
    }

    public int getCount() {
        return count;
    }

    public ArrayList<Question> getCorrectAnswerSet() {
        return correct_answer;
    }

    public ArrayList<Integer> getStudentScore() {
        return studentScore;
    }

    public ArrayList<Integer> getSortedStudentScore(){
        ArrayList<Integer> test = studentScore;
        Collections.sort(test);
        int i = 0;
        return test;
    }

    public ArrayList<Question> getResult_student() {
        return result_student;
    }

    public String getMark() {
        return "Your Mark is:  " + mark_s + " (" + String.valueOf(mark) +"%)";
    }

    // compare student answer with correct answer
    // store the ResultsActivity in result_student
    // store the mark in mark
    public ArrayList<result_Q> getResultQS() {
        result_student = new ArrayList<Question>();
        resultQS = new ArrayList<result_Q>();
        int correct_num = 0;
        // go through the array
        for (int i = 0; i < answer.size(); i++) {
            String resultQ;
            Log.i("TAG", answer.get(i).getAnswer() + " " + correct_answer.get(i).getAnswer());
            //check the answer
            if (answer.get(i).getAnswer().equals(correct_answer.get(i).getAnswer())) {
                correct_num++;
                resultQ = "True";
                result_student.add(new Question(i, "True"));
            } else {
                resultQ = "False";
                result_student.add(new Question(i, "False"));
            }
            Log.i("TAG", resultQ);

            // store the ResultsActivity in to resultQS
            resultQS.add(new result_Q(correct_answer.get(i).getAnswer(), answer.get(i).getAnswer(), resultQ, answer.get(i).getNumber()));

        }
        // Calculate the mark
        mark = (int) ((double) correct_num / (double) answer.size() * 100);
        studentScore.add(correct_num);
        mark_s = String.valueOf(correct_num) +"/" +  String.valueOf(answer.size());
        studentScore_String.add(mark_s);

        count++;
        notifyObservers();
        return resultQS;
    }

    public double getAverage() {
        int total = 0;
        for (int i = 0; i < studentScore.size(); i++) {
            total = total + studentScore.get(i);
            Log.e("score", String.valueOf(studentScore.get(i)));
        }
        return ((double) total)/((double) studentScore.size());
    }

    public int getTotal(){
        int total = 0;
        for (int i = 0; i < studentScore.size(); i++) {
            total = total + studentScore.get(i);
        }
        return total;
    }
    public void setAnswer(ArrayList<Question> a) {
        answer = new ArrayList<Question>(a);
    }

    public void setCorrectAnswerSet(ArrayList<Question> a) {
        correct_answer = new ArrayList<Question>(a);
        answer = new ArrayList<Question>();

        for(Question q:correct_answer) {
            answer.add(new Question(q.number, "-"));
        }
    }


    /**
     * Helper method to make it easier to initialize all observers
     */
    public void initObservers() {
        setChanged();
        notifyObservers();
    }

    /**
     * deletes an observer from the set of observers of this object.
     *
     * @param o the observer to be deleted
     */
    @Override
    public synchronized void deleteObserver(Observer o) {
        super.deleteObserver(o);
    }

    /**
     * Adds an observer to the set of observers for this object, provided
     * that it is not the same as some observer already in the set.
     * The order in which notifications will be delivered to multiple
     * observers is not specified. See the class comment.     *\
     *
     * @param o an observer to be added.
     * @throws NullPointerException if the parameter o is null.
     */
    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }

    /**
     * Clears the observer list so that this object no longer has any observers.
     */
    @Override
    public synchronized void deleteObservers() {
        super.deleteObservers();
    }

    /**
     * If this object has changed, as indicated by the
     * <code>hasChanged</code> method, then notify all of its observers
     * and then call the <code>clearChanged</code> method to
     * indicate that this object has no longer changed.
     * * <p>
     * Each observer has its <code>update</code> method called with two
     * arguments: this observable object and <code>null</code>. In other
     * words, this method is equivalent to:     * <blockquote><tt>
     * notifyObservers(null)
     * </tt></blockquote>
     *
     * @see Observable#clearChanged()
     * @see Observable#hasChanged()
     * @see Observer#update(Observable, Object)
     */
    @Override
    public void notifyObservers() {
        super.notifyObservers();
    }


}

