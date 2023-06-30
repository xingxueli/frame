package com.example.myapplication.ui.network

import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Looper
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.App
import com.example.myapplication.R
import com.example.myapplication.ui.home.login.LoginActivity
import com.example.myapplication.ui.home.login.RoleActivity
import com.example.myapplication.ui.local.SPUtils
import com.example.myapplication.ui.network.model.ApiResponse
import com.example.myapplication.ui.network.model.Headers
import com.example.myapplication.ui.network.model.RecruiterCardResult
import com.example.myapplication.ui.network.model.TokenRequestModel
import com.example.myapplication.ui.network.model.TokenResponseModel
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Protocol
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

class TokenInterceptor(private val context: Context) : Interceptor {

    private val tag : String  = this::class.java.simpleName

    private val tokenExpiredLock = AtomicBoolean(false)

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        var response = chain.proceed(request)
        var responseString = getResponseString(response)
        var apiResponse = Gson().fromJson(responseString, ApiResponse::class.java)
        if(isTokenExpired(apiResponse)){
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
        }else if(apiResponse.code == 10004) {//App update required to continue.
//            Looper.prepare();//sub thread toast need
//            Toast.makeText(App.instance.applicationContext, apiResponse.message, Toast.LENGTH_SHORT).show()
//            Looper.loop();//sub thread toast need

            val loginIntent = Intent(context, LoginActivity::class.java)
            loginIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            loginIntent.putExtra("apiResponseCode",apiResponse.code)
            context.startActivity(loginIntent)

        }else if(apiResponse.code == 20009){
            TODO("Requesting too often ")
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


    private fun isTokenExpired(apiResponse:ApiResponse<*>) : Boolean{
        return apiResponse.code == 20006
    }

    private fun getResponseString(response: Response) : String{
        var source = response.body?.source() as Source
        return source.buffer().readString(StandardCharsets.UTF_8)
    }
}
