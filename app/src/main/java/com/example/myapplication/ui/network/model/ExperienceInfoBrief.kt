package com.example.myapplication.ui.network.model

import java.util.Date

class ExperienceInfoBrief {
    var companyName: String? = null

    /**
     * 职业名称
     * designation(position) in this company, for example java programmer
     */
    var designation: String? = null

    /**
     * 开始时间
     * the date of starting working in this company
     */
    var startTime: String? = null

    /**
     * the date of end working in this company
     * 开始时间结束时间
     */
    var endTime: String? = null
}