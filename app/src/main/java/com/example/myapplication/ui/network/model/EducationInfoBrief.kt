package com.example.myapplication.ui.network.model

import java.util.Date

class EducationInfoBrief {
    var schoolName: String? = null

    /**
     * education level
     */
    var education: String? = null

    /**
     * 学历名称
     */
    var degree: String? = null

    /**
     * 主修专业
     */
    var major: String? = null

    /**
     * 专业选择other,用户自己填写的
     */
    var majorOther: String? = null

    /**
     * 开始时间
     */
    var startTime: String? = null

    /**
     * 结束时间
     */
    var endTime: String? = null
}