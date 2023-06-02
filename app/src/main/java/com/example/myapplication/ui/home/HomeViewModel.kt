package com.example.myapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.ui.network.model.HomeInfoResult
import com.example.myapplication.ui.network.model.TabModel

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

    private val _homeInfoResult = MutableLiveData<HomeInfoResult>().apply {

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

