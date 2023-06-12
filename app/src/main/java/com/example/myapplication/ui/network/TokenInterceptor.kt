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
        if(isTokenExpired(response)){
            var newToken = getNewToken()
            if(newToken.isNotEmpty()){
                var newRequest = request.newBuilder()
                    .addHeader(Headers.ID_TOKEN, newToken)
                    .build();
                response.close();
                return chain.proceed(newRequest);
            }else{
                TODO("to login page")
            }
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
            val infoResultService = RetrofitUtils.createNoToken(RetrofitService::class.java)
            var tokenRequestModel = TokenRequestModel()
            tokenRequestModel.refreshToken = idToken
//            infoResultService.getNewToken(tokenRequestModel).enqueue(object :
//                Callback<ApiResponse<TokenResponseModel>> {
//                override fun onResponse(
//                    call: Call<ApiResponse<TokenResponseModel>>,
//                    response: retrofit2.Response<ApiResponse<TokenResponseModel>>
//                ) {
//                    var apiResponse : ApiResponse<TokenResponseModel>? = response.body()
//                    if(apiResponse!!.isSuccess()){
//                        idToken = apiResponse.data?.idToken.toString()
//                        SPUtils.putString(App.instance, Headers.ID_TOKEN, idToken)
//                    }else{
//                        TODO()
//                    }
//                }
//                override fun onFailure(call: Call<ApiResponse<TokenResponseModel>>, t: Throwable) {
//                    t.message?.let { Log.i(tag, it) }
//                }
//            })
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

    private fun isTokenExpired(response: Response) : Boolean{
        var source = response.body?.source() as Source
        var bodyString = source.buffer().readString(StandardCharsets.UTF_8)
        var apiResponse = Gson().fromJson(bodyString, ApiResponse::class.java)

        return apiResponse.code == 20006
    }


}
