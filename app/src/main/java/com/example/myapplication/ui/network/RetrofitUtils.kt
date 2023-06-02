package com.example.myapplication.ui.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitUtils {
    private val BASE_URL = "https://ustest.workandroid.com/hy/"//根路径 这里我就不明示了，毕竟涉及公司

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())//添加用于解析数据的转换库
        .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)
}