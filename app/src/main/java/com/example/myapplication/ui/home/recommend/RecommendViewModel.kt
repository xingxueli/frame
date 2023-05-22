package com.example.myapplication.ui.home.recommend

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.ui.net.RetrofitService
import com.example.myapplication.ui.net.RetrofitUtils
import com.example.myapplication.ui.net.model.CandidateFilter
import com.example.myapplication.ui.net.model.HomeInfoResult
import com.example.myapplication.ui.net.model.TabModel
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
class RecommendViewModel : ViewModel() {

    private val tag : String  = "RecommendViewModel";

    private val _homeInfoResult = MutableLiveData<HomeInfoResult>().apply {
        val infoResultService = RetrofitUtils.create(RetrofitService::class.java)
        var userToken = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoiZjMzODExMzYwYjQ1NGZiNWFiYjFkYWVlM2FkNDMiLCJjcmVhdGVUaW1lIjoxNjg0NzI2Mjg1NDE0LCJpYXQiOjE2ODQ3MjYyODUsImlzcyI6ImFwcF9pc3N1ZXIifQ.CFw6MXULYoQUzt12-AbGGeCUKvFjgfbs2JOrAkyc-9k"
        var role = 2
        var candidateFilter : CandidateFilter = CandidateFilter()
        //获取user token
//        val sharedPreferences =
//            requireContext().getSharedPreferences(AllConfig.SHARED_PREFERENCES, Context.MODE_PRIVATE)
//        userToken = sharedPreferences.getString(AllConfig.USER_TOKEN, "").toString()

        infoResultService.getHomeInfo(userToken,role,1,54,856257309058404352,candidateFilter).enqueue(object : Callback<HomeInfoResult> {
            override fun onResponse(
                call: Call<HomeInfoResult>,
                response: Response<HomeInfoResult>
            ) {
                //todo 10007 token 过期 刷新页面并询问是否重新登录
                value = response.body()
                var gson = Gson()
                Log.i(tag,gson.toJson(value))
            }

            override fun onFailure(call: Call<HomeInfoResult>, t: Throwable) {
                t.message?.let { Log.i(tag, it) }
            }

        })
    }
    val homeInfoResult: LiveData<HomeInfoResult> = _homeInfoResult

    private val _tabModel = MutableLiveData<List<TabModel>>().apply {
        val tabModel1 = TabModel()
        tabModel1.tag = 0
        tabModel1.title = "java"
        val tabModel2 = TabModel()
        tabModel2.tag = 1
        tabModel2.title = "CTO"
        value = mutableListOf(tabModel1,tabModel2)
    }
    val tabModel: LiveData<List<TabModel>> = _tabModel

    fun getHomeList() {

    }

}

