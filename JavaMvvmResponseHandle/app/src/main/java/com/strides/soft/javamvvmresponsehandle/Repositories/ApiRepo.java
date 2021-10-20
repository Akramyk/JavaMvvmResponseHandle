package com.strides.soft.javamvvmresponsehandle.Repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.strides.soft.javamvvmresponsehandle.Models.ApiModel;
import com.strides.soft.javamvvmresponsehandle.ResponseHandler.ApiResponse;
import com.strides.soft.javamvvmresponsehandle.Network.ApiEndpoints;
import com.strides.soft.javamvvmresponsehandle.Network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiRepo{

    public ApiEndpoints endpoints;

    public ApiRepo() {
        endpoints = ApiService.getService();
    }


    public LiveData<ApiResponse> getApiCallOnRepository() {
        final MutableLiveData<ApiResponse> apiResponse = new MutableLiveData<>();
        Call<List<ApiModel>> call = endpoints.getDataFromApi();
        call.enqueue(new Callback<List<ApiModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<ApiModel>> call, @NonNull Response<List<ApiModel>> response) {
                if (response.isSuccessful()) {
                    apiResponse.postValue(new ApiResponse(response.body()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<ApiModel>> call, @NonNull Throwable t) {
                apiResponse.postValue(new ApiResponse(t));
            }
        });

        return apiResponse;
    }
}