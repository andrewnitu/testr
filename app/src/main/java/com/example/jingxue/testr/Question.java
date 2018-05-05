package com.example.jingxue.testr;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Question implements Serializable {
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