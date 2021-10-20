package com.strides.soft.javamvvmresponsehandle.ViewModels;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.strides.soft.javamvvmresponsehandle.ResponseHandler.ApiResponse;
import com.strides.soft.javamvvmresponsehandle.Repositories.ApiRepo;

public class MainActivityViewModel extends ViewModel {

    private MediatorLiveData<ApiResponse> mApiResponse;
    private ApiRepo mApiRepo;

    public MainActivityViewModel() {
        mApiResponse = new MediatorLiveData<>();
        mApiRepo = new ApiRepo();
    }

    public LiveData<ApiResponse> getDataOnViewModel() {
        mApiResponse.addSource(mApiRepo.getApiCallOnRepository(), new Observer<ApiResponse>() {
            @Override
            public void onChanged(@Nullable ApiResponse apiResponse)            {
                mApiResponse.setValue(apiResponse);
            }
        });
        return mApiResponse;
    }

}