package com.example.myapplication.ui.network

import com.example.myapplication.ui.network.model.ApiResponse
import com.example.myapplication.ui.network.model.CandidateFilter
import com.example.myapplication.ui.network.model.CandidatePreference
import com.example.myapplication.ui.network.model.HomeInfoResult
import com.example.myapplication.ui.network.model.JobFilter
import com.example.myapplication.ui.network.model.PreferenceOption
import com.example.myapplication.ui.network.model.RecruiterCardResult
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitService {

    //------------ to B
    @POST("candidate-service/candidates/recommendation")
    fun getRecommendationCandidates(@Query("type") type: Int,@Query("cityId") cityId: Int,@Query("jobId") jobId: Long,@Query("pageNum") pageNum: Int,@Query("pageSize") pageSize: Int,@Body candidateFilter: CandidateFilter): Call<ApiResponse<HomeInfoResult>>

    @POST("recruiter-service/recruiters/jobs/options")
    fun getRecruiterJobs(@Query("type") type: Int,@Query("cityId") cityId: Int,@Query("jobId") jobId: Long,@Query("pageNum") pageNum: Int,@Query("pageSize") pageSize: Int,@Body candidateFilter: CandidateFilter): Call<ApiResponse<HomeInfoResult>>

    //------------ to C
    @GET("candidate-service/candidates/preferences")
    fun getCandidatePreferences(): Call<ApiResponse<List<CandidatePreference>>>

    @GET("candidate-service/candidates/preferences/options")
    fun getCandidateTabs(): Call<ApiResponse<List<PreferenceOption>>>

    @POST("job-service/v1/jobs/recommendation")
    fun getRecommendationRecruiters(@Query("type") type: Int,@Query("cityId") cityId: Int,@Query("preferenceId") preferenceId: Long,@Query("pageNum") pageNum: Int,@Query("pageSize") pageSize: Int,@Body jobFilter: JobFilter): Call<ApiResponse<RecruiterCardResult>>
}