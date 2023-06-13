package com.example.myapplication.ui.network

import com.example.myapplication.App
import com.example.myapplication.ui.local.SPUtils
import com.example.myapplication.ui.network.model.Headers
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * val headers = Headers.headersOf(
    "Content-Type", "application/json",
    "Authorization", "Bearer YOUR_ACCESS_TOKEN",
    "User-Agent", "Your User Agent"
    )
 *
 *  相当于log、header、response统一处理
 */
class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        return chain.proceed(assembleRequest(request))
    }

    /**
     * 登陆成功后存入
     * SpUtils.putString(this,"X-IDToken","token");
     *SPUtils.putInt(App.instance, "X-Role", tempRole);
     *     RECRUITER(1),
     *     CANDIDATE(2);
     */
    private fun assembleRequest(request: Request): Request {
//        var tempc =
//            "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoiMGJmNWIwNDFmYzBiNGQ2Njg3MTc2ZTk0ZmYzYTYiLCJjcmVhdGVUaW1lIjoxNjg2MTg4NTg0NzE4LCJpYXQiOjE2ODYxODg1ODQsImlzcyI6ImFwcF9pc3N1ZXIifQ.BhsxTG7Jgu7CTnY2kuvcr4qGoZk47wW4qdt9OUKivD4";
//        SPUtils.putString(App.instance, Headers.ID_TOKEN, tempc);
//        var tempRoleC = 2
//        SPUtils.putInt(App.instance, Headers.ROLE, tempRoleC);
//        var tempb =
//            "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoiZjJhYWQ0ZTU0YTFjNDBlNmE3ODM4YTExOGJkNDciLCJjcmVhdGVUaW1lIjoxNjg2MjE1NTQ0NjI0LCJpYXQiOjE2ODYyMTU1NDQsImlzcyI6ImFwcF9pc3N1ZXIifQ.NCV0a2i85nExEoiJJio68D8I1R8ZN3f3GHyaoXIjzOI";
//        SPUtils.putString(App.instance, Headers.ID_TOKEN, tempb);
//        var tempRoleB = 1
//        SPUtils.putInt(App.instance, Headers.ROLE, tempRoleB);

        var newBuilder = request.newBuilder()
        var idToken = SPUtils.getString(App.instance, "X-IDToken", "")
        if (idToken!!.isNotEmpty()) {
            newBuilder.addHeader(Headers.ID_TOKEN, idToken!!)
        }
        var role = SPUtils.getInt(App.instance, "X-Role", 2)
        if (role != 0) {
            newBuilder.addHeader(Headers.ROLE, role.toString())
        }

        return newBuilder.build();
    }

}
