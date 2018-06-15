package com.example.tapan.demolco.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class QuestionList implements Serializable {


    @SerializedName("questions")
    @Expose
    private List<Question> questions = null;


    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

}
