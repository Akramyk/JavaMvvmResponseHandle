package com.strides.soft.apptunixassignmentkotlin.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BreedModel {

    @SerializedName("message")
    @Expose
    private var message: String? = null

    @SerializedName("status")
    @Expose
    private var status: String? = null

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String?) {
        this.message = message
    }

    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String?) {
        this.status = status
    }
}