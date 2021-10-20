package com.strides.soft.javamvvmresponsehandle.ResponseHandler;

//https://jsonplaceholder.typicode.com/posts/1/comments

import com.strides.soft.javamvvmresponsehandle.Models.ApiModel;

import java.util.List;

public class ApiResponse {
    public List<ApiModel> posts;
    private Throwable error;

    public ApiResponse(List<ApiModel> posts) {
        this.posts = posts;
        this.error = null;
    }

    public ApiResponse(Throwable error) {
        this.error = error;
        this.posts = null;
    }

    public List<ApiModel> getPosts() {
        return posts;
    }

    public void setPosts(List<ApiModel> posts) {
        this.posts = posts;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }
}
