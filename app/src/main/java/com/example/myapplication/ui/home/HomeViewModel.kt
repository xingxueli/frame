package com.example.myapplication.ui.home

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.App
import com.example.myapplication.ui.local.SPUtils
import com.example.myapplication.ui.network.RetrofitService
import com.example.myapplication.ui.network.RetrofitUtils
import com.example.myapplication.ui.network.model.ApiResponse
import com.example.myapplication.ui.network.model.PreferenceOption
import com.example.myapplication.ui.network.model.RecruiterPreference
import com.example.myapplication.ui.network.model.Role
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * 在使用mvvm架构时，官方推荐我们将网络请求等数据处理都放到ViewModel中，更好实现数据处理和数据解耦
 *
 * 到这里只要你不是写错了什么代码的话，那么已经足以申请到数据，接下来就是利用LiveData的特性，观察者来自动更新数据，
 * 这样的话我们也不怕子线程申请数据跑自己，主线程直接返回数据null给我们的控制器，导致我们页面一片空白了。
 * liveData替带手动notifyDataSetChanged
 *
 * 		子线程用postValue（）设置值
 * 		bean.postValue(response.body());
 */
class HomeViewModel : ViewModel() {

    private val tag : String  = "HomeViewModel";

    val candidateDataList: MutableLiveData<List<PreferenceOption>> = MutableLiveData()
    val recruiterDataList: MutableLiveData<List<RecruiterPreference>> = MutableLiveData()

    fun initData() {
        var role = SPUtils.getInt(App.instance, "X-Role", 0)
        loadData(role)
    }

    private fun loadData(role : Int){
        val retrofitService = RetrofitUtils.create(RetrofitService::class.java)

        when(role){
            Role.CANDIDATE.id -> {
                retrofitService.getCandidateTabs().enqueue(object :
                Callback<ApiResponse<List<PreferenceOption>>> {
                    override fun onResponse(
                        call: Call<ApiResponse<List<PreferenceOption>>>,
                        response: Response<ApiResponse<List<PreferenceOption>>>
                    ) {
                        //todo 10007 token 过期 刷新页面并询问是否重新登录
                        var apiResponse : ApiResponse<List<PreferenceOption>>? = response.body()
                        var gson = Gson()
                        Log.i(tag,gson.toJson(apiResponse))
                        if(apiResponse!!.isSuccess()){
                            candidateDataList.postValue(apiResponse!!.data!!)
                        }else{
                            gson = Gson()
                            Toast.makeText(App.instance, gson.toJson(apiResponse.errMsg), Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                    override fun onFailure(call: Call<ApiResponse<List<PreferenceOption>>>, t: Throwable) {
                        t.message?.let { Log.i(tag, it) }
                    }
                })
            }
            Role.RECRUITER.id -> {
                retrofitService.getRecruiterTabs().enqueue(object :
                    Callback<ApiResponse<List<RecruiterPreference>>> {
                    override fun onResponse(
                        call: Call<ApiResponse<List<RecruiterPreference>>>,
                        response: Response<ApiResponse<List<RecruiterPreference>>>
                    ) {
                        //todo 10007 token 过期 刷新页面并询问是否重新登录
                        var apiResponse: ApiResponse<List<RecruiterPreference>>? = response.body()
                        var gson = Gson()
                        Log.i(tag, gson.toJson(apiResponse))
                        if (apiResponse!!.isSuccess()) {
                            recruiterDataList.postValue(apiResponse!!.data!!)
                        } else {
                            gson = Gson()
                            Toast.makeText(App.instance, gson.toJson(apiResponse.errMsg), Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                    override fun onFailure(
                        call: Call<ApiResponse<List<RecruiterPreference>>>,
                        t: Throwable
                    ) {
                        t.message?.let { Log.i(tag, it) }
                    }
                })
            }
        }

    }

}

