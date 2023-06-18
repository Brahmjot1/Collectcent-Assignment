package com.brahm.assigment1;

import com.google.gson.JsonObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;

import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiInterface {
    @POST
    Call<ResponseBody> postRawJSON(@Url String url, @Body JsonObject jsonObject);
}
