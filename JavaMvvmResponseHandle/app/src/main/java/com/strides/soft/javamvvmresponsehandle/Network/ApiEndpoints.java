package com.strides.soft.javamvvmresponsehandle.Network;

import com.strides.soft.javamvvmresponsehandle.Models.ApiModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

//Web Service Api
//https://jsonplaceholder.typicode.com/posts

public interface ApiEndpoints {

    @GET("/posts")
    Call<List<ApiModel>> getDataFromApi();

}