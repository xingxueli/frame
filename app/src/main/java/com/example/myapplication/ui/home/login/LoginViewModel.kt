package com.example.myapplication.ui.home.login

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.App
import com.example.myapplication.ui.network.NoTokenRetrofitService
import com.example.myapplication.ui.network.RetrofitUtils
import com.example.myapplication.ui.network.model.ApiResponse
import com.example.myapplication.ui.network.model.CandidatePreference
import com.example.myapplication.ui.network.model.OtpRequestModel
import com.example.myapplication.ui.network.model.UserInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    private val tag : String  = this::class.java.simpleName

    val dataList: MutableLiveData<ApiResponse<Object>> = MutableLiveData()

    fun initData(mobile : String) {
        loadData(mobile)
    }

    private fun loadData(mobile : String){
        val noTokenRetrofitService = RetrofitUtils.createNoToken(NoTokenRetrofitService::class.java)

        var otpRequestModel = OtpRequestModel()
        otpRequestModel.type = 1
        otpRequestModel.mobile = mobile
        noTokenRetrofitService.getOtp(otpRequestModel).enqueue(object :
            Callback<ApiResponse<Object>> {
            override fun onResponse(
                call: Call<ApiResponse<Object>>,
                response: Response<ApiResponse<Object>>
            ) {
                dataList.postValue(response.body())
            }

            override fun onFailure(call: Call<ApiResponse<Object>>, t: Throwable) {
                t.message?.let { Log.i(tag, it) }
            }

        })

    }

}