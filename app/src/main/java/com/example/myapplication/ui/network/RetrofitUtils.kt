package com.example.myapplication.ui.network

import com.example.myapplication.App
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitUtils {
    private val BASE_URL = "https://test.hirect.ai/hirect/"

    private val headerInterceptor = HeaderInterceptor()
    private val noTokenHeaderInterceptor = HeaderInterceptor()
    private val tokenInterceptor = TokenInterceptor(App.getContext())
    private val loggingInterceptor = LoggingInterceptor()

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(headerInterceptor)
        .addInterceptor(tokenInterceptor)
        .addInterceptor(loggingInterceptor)
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val okHttpClientNoToken: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(noTokenHeaderInterceptor)
        .addInterceptor(loggingInterceptor)
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .build()

    private val noTokenRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClientNoToken)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    fun <T> createNoToken(serviceClass: Class<T>): T = noTokenRetrofit.create(serviceClass)
}