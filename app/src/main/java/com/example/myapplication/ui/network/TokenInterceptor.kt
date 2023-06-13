package com.example.myapplication.ui.network

import android.util.Log
import com.example.myapplication.App
import com.example.myapplication.ui.local.SPUtils
import com.example.myapplication.ui.network.model.ApiResponse
import com.example.myapplication.ui.network.model.Headers
import com.example.myapplication.ui.network.model.RecruiterCardResult
import com.example.myapplication.ui.network.model.TokenRequestModel
import com.example.myapplication.ui.network.model.TokenResponseModel
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.asResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.Source
import okio.buffer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.awaitResponse
import java.nio.charset.StandardCharsets
import java.util.concurrent.atomic.AtomicBoolean




/**
 *
 */
class TokenInterceptor : Interceptor {

    private val tag : String  = "TokenInterceptor";

    private val tokenExpiredLock = AtomicBoolean(false)

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        var response = chain.proceed(request)
        var responseString = getResponseString(response)
        if(isTokenExpired(responseString)){
            var newToken = getNewToken()
            if(newToken.isNotEmpty()){
                var newRequest = request.newBuilder()
                    .removeHeader(Headers.ID_TOKEN)
                    .addHeader(Headers.ID_TOKEN, newToken)
                    .build();
                return chain.proceed(newRequest);
            }else{
                TODO("to login page")
            }
        }else{
            var newResponse = response.newBuilder().body(responseString.toResponseBody()).build()
            return newResponse
        }

        return response
    }

    @Synchronized
    private fun getNewToken() : String{
        var idToken = SPUtils.getString(App.instance, Headers.ID_TOKEN, "")
        if(idToken!!.isEmpty()){
            return idToken
        }
        if (tokenExpiredLock.compareAndSet(false, true)) {
            val infoResultService = RetrofitUtils.createNoToken(NoTokenRetrofitService::class.java)
            var tokenRequestModel = TokenRequestModel()
            tokenRequestModel.refreshToken = idToken

            var response = infoResultService.getNewToken(tokenRequestModel).execute()
            var apiResponse : ApiResponse<TokenResponseModel>? = response.body()
            return if (apiResponse != null) {
                var idTokenString = apiResponse.data?.idToken.toString()
                SPUtils.putString(App.instance, Headers.ID_TOKEN, idTokenString);
                return idTokenString
            }else{
                ""
            }
        }
            return idToken!!
    }

    private fun isTokenExpired(responseString: String) : Boolean{
        var apiResponse = Gson().fromJson(responseString, ApiResponse::class.java)

        return apiResponse.code == 20006
    }

    private fun getResponseString(response: Response) : String{
        var source = response.body?.source() as Source
        return source.buffer().readString(StandardCharsets.UTF_8)
    }
}
