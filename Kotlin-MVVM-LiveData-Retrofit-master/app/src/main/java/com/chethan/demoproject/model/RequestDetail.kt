package com.strides.soft.mvvmretrofitfragmentkotlin.Models

import java.io.Serializable

data class RequestDetail(
    var srno: Int,
    var patientId: String,
    var patientName: String,
    var motherName: String,
    var clhhNum: String,
    var admissionNum: String,
    var orderNum: String,
    var dob: String,
    var patientBg: String,
    var age: String,
    var gender: String,
    var registrationDate: String,
    var sampleStatus: String
) : Serializable
