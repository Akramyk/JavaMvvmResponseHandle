package com.strides.soft.mvvmretrofitkotlinsamplebyak.Interfaces


interface NetworkResponseCallback {
    fun onNetworkSuccess()
    fun onNetworkFailure(th : Throwable)
}