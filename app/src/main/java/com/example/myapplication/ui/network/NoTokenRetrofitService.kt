package com.example.myapplication.ui.network

import com.example.myapplication.ui.network.model.ApiResponse
import com.example.myapplication.ui.network.model.LoginRequestModel
import com.example.myapplication.ui.network.model.OtpRequestModel
import com.example.myapplication.ui.network.model.TokenRequestModel
import com.example.myapplication.ui.network.model.TokenResponseModel
import com.example.myapplication.ui.network.model.UserInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface NoTokenRetrofitService {

    // ------------- both role
    @POST("login/token")
    fun getNewToken(@Body tokenRequestModel: TokenRequestModel): Call<ApiResponse<TokenResponseModel>>

    @POST("login/otp")
    fun getOtp(@Body otpRequestModel: OtpRequestModel): Call<ApiResponse<Object>>

    @POST("login/mobile")
    fun loginMobile(@Body loginRequestModel: LoginRequestModel): Call<ApiResponse<UserInfo>>
}