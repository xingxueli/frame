package com.example.myapplication.ui.network.model

open class JobListBriefInfoC {
    var id: Long? = null
    var title: String? = null
    var urgency: Int? = null

    /**
     * 开放关闭状态
     */
    var status: Int? = null
    var workFromHome: Boolean? = null
    var salaryMin: Int? = null
    var salaryMax: Int? = null

    var salary: String? = null
    var salaryUnit: Int? = null

    var experience: String? = null

    var degree: String? = null

    var city: String? = null
    var detail: String? = null

    var company: CompanyBriefInJobList? = null

    var recruiter: RecruiterBriefInJobList? = null

    var type: String? = null
    var typeId: Int? = null
    var skillTags: List<String>? = null

    /**
     * 工作的地理位置
     */
    var location: Location? = null

    /**
     * 招聘意向地址
     */
    var preferenceLocation: Location? = null
}