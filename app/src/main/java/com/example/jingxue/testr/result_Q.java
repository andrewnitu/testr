package com.example.jingxue.testr;

public class result_Q {
    String answer;
    String correct;
    String resultQ;
    int number;

    public result_Q(String correct, String answer, String result, int number) {
        this.correct = correct;
        this.answer = answer;
        this.resultQ = result;
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public String getCorrect() {
        return this.correct;
    }

    public String getResult(){
        return this.resultQ;
    }

    public String getAnswer() {
        return this.answer;
    }
}
