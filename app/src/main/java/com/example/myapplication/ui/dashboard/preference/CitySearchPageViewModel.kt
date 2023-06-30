package com.example.myapplication.ui.dashboard.preference

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.App
import com.example.myapplication.ui.network.RetrofitService
import com.example.myapplication.ui.network.RetrofitUtils
import com.example.myapplication.ui.network.model.ApiResponse
import com.example.myapplication.ui.network.model.SysDeptModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CitySearchPageViewModel : ViewModel() {

    private val tag : String  = this::class.java.simpleName

    val dataList: MutableLiveData<List<SysDeptModel>> = MutableLiveData()

    fun initData(query: String) {
        loadData(query)
    }

    private fun loadData(query: String){
        val retrofitService = RetrofitUtils.create(RetrofitService::class.java)

        retrofitService.searchCity(query).enqueue(object :
            Callback<ApiResponse<List<SysDeptModel>>> {
            override fun onResponse(
                call: Call<ApiResponse<List<SysDeptModel>>>,
                response: Response<ApiResponse<List<SysDeptModel>>>
            ) {
                var apiResponse : ApiResponse<List<SysDeptModel>>? = response.body()
                var gson = Gson()
                Log.i(tag,"-----------------")
                Log.i(tag,gson.toJson(apiResponse))
                if(apiResponse!!.isSuccess()){
                    dataList.postValue(apiResponse.data!!)
                }else{
                    gson = Gson()
                    Toast.makeText(App.instance, gson.toJson(apiResponse.errMsg), Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<ApiResponse<List<SysDeptModel>>>, t: Throwable) {
                t.message?.let { Log.i(tag, it) }
            }

        })

    }

}