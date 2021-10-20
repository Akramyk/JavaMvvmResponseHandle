package com.strides.soft.javamvvmresponsehandle.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.strides.soft.javamvvmresponsehandle.Adapter.JasonDataAdapter;
import com.strides.soft.javamvvmresponsehandle.ResponseHandler.ApiResponse;
import com.strides.soft.javamvvmresponsehandle.Models.ApiModel;
import com.strides.soft.javamvvmresponsehandle.R;
import com.strides.soft.javamvvmresponsehandle.ViewModels.MainActivityViewModel;
import com.strides.soft.javamvvmresponsehandle.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public List<ApiModel> jasonDataList;
    public static  JasonDataAdapter jasonDataAdapter;
    ActivityMainBinding binding;

    //https://medium.com/@sriramr083/error-handling-in-retrofit2-in-mvvm-repository-pattern-a9c13c8f3995
    //API = https://jsonplaceholder.typicode.com/posts

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);

        fetchData();

        binding.btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchData();
            }
        });

    }

    public void fetchData(){
        binding.llProgresslayout.setVisibility(View.VISIBLE);
        binding.rvJasondata.setVisibility(View.GONE);
        binding.llErrorlayout.setVisibility(View.GONE);
        MainActivityViewModel viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        viewModel.getDataOnViewModel().observe(this, new Observer<ApiResponse>() {
            @Override
            public void onChanged(ApiResponse apiResponse) {
                if (apiResponse == null) {
                    // handle error here
                    Log.i("AK", "Response: " +apiResponse);
                    binding.llProgresslayout.setVisibility(View.GONE);
                    binding.rvJasondata.setVisibility(View.GONE);
                    binding.llErrorlayout.setVisibility(View.VISIBLE);
                    return;
                }
                if (apiResponse.getError() == null) {
                    // call is successful
                    Log.i("AK", "Data response is " + apiResponse.getPosts());
                    //List Setup in Adapter
                    jasonDataList = new ArrayList<>();
                    jasonDataList = apiResponse.getPosts();
                    if (jasonDataList.size() > 0){
                        binding.llProgresslayout.setVisibility(View.GONE);
                        binding.rvJasondata.setVisibility(View.VISIBLE);
                        binding.llErrorlayout.setVisibility(View.GONE);
                        adapterSetup(jasonDataList);
                    }else {
                        binding.llProgresslayout.setVisibility(View.GONE);
                        binding.rvJasondata.setVisibility(View.GONE);
                        binding.llErrorlayout.setVisibility(View.VISIBLE);
                    }
                } else {
                    // call failed.
                    Throwable e = apiResponse.getError();
                    Toast.makeText(MainActivity.this, "Error is " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("AK", "Error is " + e.getLocalizedMessage());
                    binding.llProgresslayout.setVisibility(View.GONE);
                    binding.rvJasondata.setVisibility(View.GONE);
                    binding.llErrorlayout.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void adapterSetup(List<ApiModel> jasonDataList){
        try {
            jasonDataAdapter = new JasonDataAdapter(MainActivity.this, jasonDataList);
            LinearLayoutManager layoutManager = new  LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
            binding.rvJasondata.setLayoutManager(layoutManager);
            binding.rvJasondata.setAdapter(jasonDataAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}