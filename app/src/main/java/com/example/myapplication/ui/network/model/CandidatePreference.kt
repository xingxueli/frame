package com.example.myapplication.ui.network.model

class CandidatePreference {
    var id: Long? = null
    var jobType: String? = null
    var jobTypeId: Int? = null
    var industryTags: List<IndustryTagInfo>? = null
    var channelId: Int? = null

    var channel: String? = null
    var cityId: Int? = null

    var city: String? = null

    var salary: String? = null
    var salaryType: Int? = null
    var salaryMin: Int? = null
    var salaryMax: Int? = null
    var salaryUnit: Int? = null
    var createTime: String? = null
    var updateTime: String? = null

    var buildJobClassification: String? = null
    var isDeleted: Boolean? = null
    var status: Int? = null
    var labelvarue: Int? = null
    var labelDescription: String? = null
    var workFromHome: Boolean? = null
    var preferenceAuditStatus: String? = null
    var address: String? = null
    var authorized: Int? = null
}