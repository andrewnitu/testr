package com.example.andrew.myapplication;

/**
 * Created by Kevin on 5/5/2018.
 */

public class Question {
    int number;
    String answer;

    public Question(int number, String answer) {
        this.number = number;
        this.answer = answer;
    }

    public int getNumber() {
        return this.number;
    }

    public String getAnswer() {
        return this.answer;
    }
}