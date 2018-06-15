package com.example.tapan.demolco;

import com.example.tapan.demolco.models.QuestionList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {


    @GET("api/android/datastructure.json")
    Call<QuestionList> getAllQuestions();

}
