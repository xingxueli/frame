package com.example.myapplication.ui.dashboard.preference

import android.app.Activity
import androidx.annotation.StyleRes
import com.example.myapplication.R
import com.github.gzuliyujiang.wheelpicker.LinkagePicker

class SalaryPicker : LinkagePicker {

    constructor(activity: Activity) : super(activity) {}
    constructor(activity: Activity, @StyleRes themeResId: Int) : super(activity, themeResId) {}

    override fun initData() {
        super.initData()
//        setBackgroundColor(-0x1)
//        cancelView.text = "取消"
//        cancelView.textSize = 16f
//        cancelView.setTextColor(-0xff7e01)
//        okView.setTextColor(-0xff7e01)
//        okView.text = "确定"
//        okView.textSize = 16f
        titleView.setTextColor(-0xcccccd)
        titleView.text = "Expected Annual Salary"
        titleView.textSize = 20f
    }
}