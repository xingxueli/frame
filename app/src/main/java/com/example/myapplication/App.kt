package com.example.myapplication

import android.app.Application

class App : Application() {

    //因为kotlin中的类定义同时也是构造函数，这个时候是不能进行操作的，
    // 所以kotlin增加了一个新的关键字init用来处理类的初始化问题，init模块中的内容可以直接使用构造函数的参数。
    init {
        instance = this
    }
    //加入注解，标注这个apiComponent是需要注入的
    //lateinit 则用于只能生命周期流程中进行获取或者初始化的变量，比如 Android 的 onCreate()
//    @Inject lateinit var apiComponent: ApiComponent
//    override fun onCreate() {
//        super.onCreate()
//        //使用组件进行构造，component注射器注入
//        DaggerApiComponent.builder()
//            .apiModule(ApiModule())
//            .appModule(AppModule(this))
//            .build()
//            .inject(this)
//    }

    //Companion Object中定义的成员类似于Java中的静态成员，因为Kotlin中没有static成员
    companion object {
        lateinit var instance: App
    }
}