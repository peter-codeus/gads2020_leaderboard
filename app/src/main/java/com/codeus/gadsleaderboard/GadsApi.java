package com.codeus.gadsleaderboard;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GadsApi {
    public static String BASE_URL = "https://gadsapi.herokuapp.com/api/";

    @GET("skilliq")
    Call<List<GadsModel>> getTopSkillIQ();

    @GET("hours")
    Call<List<GadsModel>> getTopLearners();
}
