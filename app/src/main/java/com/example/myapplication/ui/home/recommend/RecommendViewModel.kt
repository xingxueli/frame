package com.example.myapplication.ui.home.recommend

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.ui.network.RetrofitService
import com.example.myapplication.ui.network.RetrofitUtils
import com.example.myapplication.ui.network.model.ApiResponse
import com.example.myapplication.ui.network.model.CandidateFilter
import com.example.myapplication.ui.network.model.CandidateInfoBrief
import com.example.myapplication.ui.network.model.HomeInfoResult
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

    var isLoading: Boolean = false

    val candidateDataList: MutableLiveData<MutableList<CandidateInfoBrief>> = MutableLiveData()

    val recruiterDataList: MutableLiveData<MutableList<CandidateInfoBrief>> = MutableLiveData()

    fun initData(pageNum : Int) {
        // 执行初始化数据操作
        // 设置 isLoading 为 true，表示正在加载数据
        isLoading = true

        // 模拟加载数据的操作
        // 加载完成后更新 dataList，并将 isLoading 设置为 false
        // 例如，你可以在这里发起网络请求或从数据库中加载数据
        // 加载完成后，通过调用 dataList.postValue(dataListValue) 来更新数据
        loadData(pageNum)

    }

    fun refreshData() {
        // 执行下拉刷新操作
        // 设置 isLoading 为 true，表示正在加载数据
        isLoading = true

        // 模拟刷新数据的操作
        // 刷新完成后更新 dataList，并将 isLoading 设置为 false
        loadData(1)

    }

    fun loadMoreData(pageNum : Int) {
        // 执行底部加载更多操作
        // 设置 isLoading 为 true，表示正在加载数据
        isLoading = true

        // 模拟加载更多数据的操作
        // 加载完成后更新 dataList，并将 isLoading 设置为 false
        loadData(pageNum)

    }

    private fun loadData(pageNum: Int){

        val infoResultService = RetrofitUtils.create(RetrofitService::class.java)
        var candidateFilter : CandidateFilter = CandidateFilter()
        var pageSize = 10

        infoResultService.getRecommendationCandidates(1,54,856257309058404352,pageNum,pageSize,candidateFilter).enqueue(object : Callback<ApiResponse<HomeInfoResult>> {
            override fun onResponse(
                call: Call<ApiResponse<HomeInfoResult>>,
                response: Response<ApiResponse<HomeInfoResult>>
            ) {
                //todo 10007 token 过期 刷新页面并询问是否重新登录
                var apiResponse : ApiResponse<HomeInfoResult>? = response.body()
//                var gson = Gson()
//                Log.i(tag,gson.toJson(apiResponse))
                if(apiResponse!!.isSuccess()){
                    candidateDataList.postValue(apiResponse!!.data!!.list)
                    isLoading = false
                }else{
                    TODO()
                }

            }

            override fun onFailure(call: Call<ApiResponse<HomeInfoResult>>, t: Throwable) {
                t.message?.let { Log.i(tag, it) }
                isLoading = false
            }

        })

    }

}

