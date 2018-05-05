package com.example.jingxue.testr;

import android.util.Log;
import java.util.Observable;
import java.util.Observer;
import java.lang.Object;
import java.util.ArrayList;



class model extends Observable{
    private static final model ourInstance = new model();
    static model getInstance()    {
        return ourInstance;
    }
    private ArrayList<Question> correct_answer;
    private ArrayList<Question> answer;
    private ArrayList<Question> result_student;

    public ArrayList<Question> getAnswer(){
        return answer;
    }

    public ArrayList<Question> getCorrect_answer() {
        return correct_answer;
    }

    public ArrayList<Question> getResult_student() {
        return result_student;
    }

    public String result_string(){
        String result_s  = "";
        for(int i = 0; i < answer.size(); i++){
            result_s = result_s + answer.get(i).getNumber() + ": " + answer.get(i).getAnswer() + " " + correct_answer.get(i).getAnswer();
            if(answer.get(i).getAnswer() == correct_answer.get(i).getAnswer()){
                result_s = result_s + " " + "True";
            }else{
                result_s = result_s + " " + "False";
            }
            result_s = result_s + "\n";
        }
        return result_s;

    }

    public void setAnswer( ArrayList<Question> a){
        answer = new ArrayList<Question>(a);
    }

    public void setCorrect_answer(ArrayList<Question> a){
        correct_answer = new ArrayList<Question>(a);
    }


    /**     * Helper method to make it easier to initialize all observers     */
    public void initObservers()    {
        setChanged();
        notifyObservers();
    }
    /**
     * deletes an observer from the set of observers of this object.
     * @param o the observer to be deleted
     */
    @Override
    public synchronized void deleteObserver(Observer o)    {
        super.deleteObserver(o);
    }
    /**
     * Adds an observer to the set of observers for this object, provided
     * that it is not the same as some observer already in the set.
     * The order in which notifications will be delivered to multiple
     * observers is not specified. See the class comment.     *\
     * @param o an observer to be added.
     * @throws NullPointerException if the parameter o is null.
     * */
    @Override
    public synchronized void addObserver(Observer o)    {
        super.addObserver(o);
    }
    /**     * Clears the observer list so that this object no longer has any observers.     */
    @Override    public synchronized void deleteObservers()    {
        super.deleteObservers();
    }
    /**
     *  If this object has changed, as indicated by the
     *  <code>hasChanged</code> method, then notify all of its observers
     *  and then call the <code>clearChanged</code> method to
     *  indicate that this object has no longer changed.
     *  * <p>
     *   Each observer has its <code>update</code> method called with two
     *   arguments: this observable object and <code>null</code>. In other
     *   words, this method is equivalent to:     * <blockquote><tt>
     *       notifyObservers(null)
     *   </tt></blockquote>
     *    @see Observable#clearChanged()
     *    @see Observable#hasChanged()
     *    @see Observer#update(Observable, Object)     */
    @Override
    public void notifyObservers()    {
        super.notifyObservers();
    }


}

