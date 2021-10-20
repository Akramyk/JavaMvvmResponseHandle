package com.strides.soft.mvvmretrofitfragmentkotlin.Models

import java.io.Serializable

data class Msg(
    var requestDetails:  List<RequestDetail>,
    var hospSrno: String,
    var deviceSrno: String,
    var bbSrno: String,
    var appSrno: String,
    var macId: String,
    var appSessionId: String,
    var langSrno: String
    ) : Serializable
