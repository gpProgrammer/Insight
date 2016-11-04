package com.a2016reserch.sliit.insight.gaming_module.Logic;



import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Sandasala on 11/3/2016.
 */
public interface QuestionUpdateEndpoint {

    @GET("myresource/test")
    Call<ArrayList<Update_Question>> getQues();
}
