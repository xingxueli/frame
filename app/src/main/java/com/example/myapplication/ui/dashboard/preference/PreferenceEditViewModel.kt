package com.example.myapplication.ui.dashboard.preference

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.App
import com.example.myapplication.ui.local.SPUtils
import com.example.myapplication.ui.network.RetrofitService
import com.example.myapplication.ui.network.RetrofitUtils
import com.example.myapplication.ui.network.model.ApiResponse
import com.example.myapplication.ui.network.model.BaseDictModel
import com.example.myapplication.ui.network.model.Headers
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PreferenceEditViewModel : ViewModel() {

    private val tag : String  = this::class.java.simpleName

    val dataList: MutableLiveData<List<BaseDictModel>> = MutableLiveData()

    fun initData() {
        loadData()
    }

    private fun loadData(){
        val retrofitService = RetrofitUtils.create(RetrofitService::class.java)

        val role = SPUtils.getInt(App.instance, Headers.ROLE, 0)
        retrofitService.searchSalary(role).enqueue(object :
            Callback<ApiResponse<List<BaseDictModel>>> {
            override fun onResponse(
                call: Call<ApiResponse<List<BaseDictModel>>>,
                response: Response<ApiResponse<List<BaseDictModel>>>
            ) {
                var apiResponse : ApiResponse<List<BaseDictModel>>? = response.body()
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

            override fun onFailure(call: Call<ApiResponse<List<BaseDictModel>>>, t: Throwable) {
                t.message?.let { Log.i(tag, it) }
            }

        })

    }

}