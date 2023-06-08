package com.example.myapplication.ui.dashboard.preference

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.App
import com.example.myapplication.ui.network.RetrofitService
import com.example.myapplication.ui.network.RetrofitUtils
import com.example.myapplication.ui.network.model.ApiResponse
import com.example.myapplication.ui.network.model.CandidateFilter
import com.example.myapplication.ui.network.model.CandidatePreference
import com.example.myapplication.ui.network.model.HomeInfoResult
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PreferenceListViewModel : ViewModel() {

    private val tag : String  = "PreferenceListViewModel";

    val dataList: MutableLiveData<List<CandidatePreference>> = MutableLiveData()

    fun initData() {
        loadData()
    }

    private fun loadData(){
        val retrofitService = RetrofitUtils.create(RetrofitService::class.java)

        retrofitService.getCandidatePreferences().enqueue(object :
            Callback<ApiResponse<List<CandidatePreference>>> {
            override fun onResponse(
                call: Call<ApiResponse<List<CandidatePreference>>>,
                response: Response<ApiResponse<List<CandidatePreference>>>
            ) {
                //todo 10007 token 过期 刷新页面并询问是否重新登录
                var apiResponse : ApiResponse<List<CandidatePreference>>? = response.body()
                var gson = Gson()
                Log.i(tag,gson.toJson(apiResponse))
                if(apiResponse!!.isSuccess()){
                    dataList.postValue(apiResponse!!.data!!)
                }else{
                    Toast.makeText(App.instance,apiResponse.errMsg, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ApiResponse<List<CandidatePreference>>>, t: Throwable) {
                t.message?.let { Log.i(tag, it) }
            }

        })

    }

}