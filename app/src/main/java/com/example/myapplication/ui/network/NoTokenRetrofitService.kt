package com.example.myapplication.ui.network

import com.example.myapplication.ui.network.model.ApiResponse
import com.example.myapplication.ui.network.model.TokenRequestModel
import com.example.myapplication.ui.network.model.TokenResponseModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface NoTokenRetrofitService {

    // ------------- both role
    @POST("login/token")
    fun getNewToken(@Body tokenRequestModel: TokenRequestModel): Call<ApiResponse<TokenResponseModel>>

}