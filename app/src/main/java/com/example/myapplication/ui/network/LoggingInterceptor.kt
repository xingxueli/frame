package com.example.myapplication.ui.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

/**
 * val headers = Headers.headersOf(
    "Content-Type", "application/json",
    "Authorization", "Bearer YOUR_ACCESS_TOKEN",
    "User-Agent", "Your User Agent"
    )
 *
 */
class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        // 打印请求信息
//        val startTime = System.nanoTime()
//        Log.d("Retrofit", String.format("Sending request %s on %s %n %s",
//            request.url, chain.connection(), request.headers.toString()))

        val response = chain.proceed(request)
        // 打印响应信息
//        val endTime = System.nanoTime()
//        Log.d("Retrofit", String.format("Received response for %s in %.1fms %n %s",
//            response.request.url, (endTime - startTime) / 1e6, response.headers.toString()))

        return response
    }

}
