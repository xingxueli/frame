package com.example.myapplication.ui.network

import com.example.myapplication.ui.network.model.CandidateFilter
import com.example.myapplication.ui.network.model.HomeInfoResult
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitService {

    @POST("candidate-service/candidates/recommendation")
    fun getHomeInfo(@Header("X-IDToken") token: String,@Header("X-role") role: Int,@Query("type") type: Int,@Query("cityId") cityId: Int,@Query("jobId") jobId: Long,@Query("pageNum") pageNum: Int,@Query("pageSize") pageSize: Int,@Body candidateFilter: CandidateFilter): Call<HomeInfoResult>

    @GET("productHomePage/getInfo")
    fun getInfoDetail(@Header("X-IDToken") token: String,@Header("X-role") role: Int): Call<HomeInfoResult>
}