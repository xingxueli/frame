package com.example.myapplication.ui.home.login

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.App
import com.example.myapplication.ui.local.SPUtils
import com.example.myapplication.ui.network.NoTokenRetrofitService
import com.example.myapplication.ui.network.RetrofitUtils
import com.example.myapplication.ui.network.model.ApiResponse
import com.example.myapplication.ui.network.model.Headers
import com.example.myapplication.ui.network.model.LoginRequestModel
import com.example.myapplication.ui.network.model.UserInfo
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VerifyViewModel : ViewModel() {

    private val tag : String  = this::class.java.simpleName

    val dataList: MutableLiveData<UserInfo> = MutableLiveData()

    fun initData(mobile : String,otpCode:String) {
        loadData(mobile,otpCode)
    }

    private fun loadData(mobile : String,otpCode:String){
        val noTokenRetrofitService = RetrofitUtils.createNoToken(NoTokenRetrofitService::class.java)
        val role = SPUtils.getInt(App.instance, Headers.ROLE, 0)
        val loginRequestModel = LoginRequestModel()
        loginRequestModel.role = role
        loginRequestModel.mobile = mobile
        loginRequestModel.otpCode = otpCode
        Log.i(tag, Gson().toJson(loginRequestModel))
        noTokenRetrofitService.loginMobile(loginRequestModel).enqueue(object :
            Callback<ApiResponse<UserInfo>> {
            override fun onResponse(
                call: Call<ApiResponse<UserInfo>>,
                response: Response<ApiResponse<UserInfo>>
            ) {
                val apiResponse : ApiResponse<UserInfo>? = response.body()
                if (apiResponse != null) {
                    Log.i(tag, Gson().toJson(apiResponse))
                    if(apiResponse.isSuccess()){
                        val data = apiResponse.data
                        if (data != null) {
                            dataList.postValue(data!!)
                            SPUtils.putString(App.instance, Headers.ID_TOKEN, data.idToken)
                        }
                    }else{
                        Toast.makeText(App.instance, apiResponse.message, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponse<UserInfo>>, t: Throwable) {
                t.message?.let { Log.i(tag, "${t.printStackTrace()}") }
            }

        })

    }

}