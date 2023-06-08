package com.example.myapplication.ui.network

import android.util.Log
import com.example.myapplication.App
import com.example.myapplication.ui.local.SPUtils
import com.example.myapplication.ui.network.model.Headers
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * val headers = Headers.headersOf(
    "Content-Type", "application/json",
    "Authorization", "Bearer YOUR_ACCESS_TOKEN",
    "User-Agent", "Your User Agent"
    )
 */
class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        // 打印请求信息
//        val startTime = System.nanoTime()
//        Log.d("Retrofit", String.format("Sending request %s on %s %n %s",
//            request.url, chain.connection(), request.headers.toString()))

        val response = chain.proceed(assembleRequest(request))

        // 打印响应信息
//        val endTime = System.nanoTime()
//        Log.d("Retrofit", String.format("Received response for %s in %.1fms %n %s",
//            response.request.url, (endTime - startTime) / 1e6, response.headers.toString()))

        return response
    }

    /**
     * 登陆成功后存入
     * SpUtils.putString(this,"X-IDToken","token");
     *SPUtils.putInt(App.instance, "X-Role", tempRole);
     *     RECRUITER(1),
     *     CANDIDATE(2);
     */
    private fun assembleRequest(request: Request): Request {
        var tempc =
            "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoiMGJmNWIwNDFmYzBiNGQ2Njg3MTc2ZTk0ZmYzYTYiLCJjcmVhdGVUaW1lIjoxNjg2MTg4NTg0NzE4LCJpYXQiOjE2ODYxODg1ODQsImlzcyI6ImFwcF9pc3N1ZXIifQ.BhsxTG7Jgu7CTnY2kuvcr4qGoZk47wW4qdt9OUKivD4";
        SPUtils.putString(App.instance, Headers.ID_TOKEN, tempc);
        var tempRole = 2
        SPUtils.putInt(App.instance, Headers.ROLE, tempRole);

        var newBuilder = request.newBuilder()
        var idToken = SPUtils.getString(App.instance, "X-IDToken", "")
        if (idToken!!.isNotEmpty()) {
            newBuilder.addHeader("X-IDToken", idToken!!)
        }
        var role = SPUtils.getInt(App.instance, "X-Role", 2)
        if (role != 0) {
            newBuilder.addHeader("X-Role", role.toString())
        }

        return newBuilder.build();
    }
}
