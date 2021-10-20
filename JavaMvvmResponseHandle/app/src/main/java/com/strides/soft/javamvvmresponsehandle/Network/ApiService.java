package com.strides.soft.javamvvmresponsehandle.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//Web Service Api
//https://jsonplaceholder.typicode.com/posts

public final class ApiService {

    private static ApiEndpoints endpoints;

    public static ApiEndpoints getService() {
        if (endpoints == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://jsonplaceholder.typicode.com")
                    .build();

            endpoints = retrofit.create(ApiEndpoints.class);
        }

        return endpoints;

    }

}