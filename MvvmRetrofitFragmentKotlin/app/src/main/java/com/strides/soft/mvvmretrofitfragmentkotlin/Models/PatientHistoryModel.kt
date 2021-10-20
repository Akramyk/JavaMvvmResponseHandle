package com.strides.soft.mvvmretrofitfragmentkotlin.Models

import java.io.Serializable

data class PatientHistoryModel(
    var msgId: Int,
    var msg: List<Msg>
) : Serializable