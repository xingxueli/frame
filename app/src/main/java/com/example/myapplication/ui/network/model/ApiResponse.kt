package com.example.myapplication.ui.network.model

import java.io.Serializable

data class ApiResponse<T>(
    val code: Int,
    val message: String,
    val errMsg: Object,
    val data: T?
) : Serializable {
    fun isSuccess(): Boolean {
        return 2000 == code
    }
}