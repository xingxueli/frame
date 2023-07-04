package com.example.myapplication.ui.network.model


class PreferenceModel {
    var id: Long? = null
    var candidateId: Long? = null

    var jobTypeId: Int? = null

    var industryTags: List<Int>? = null

    var channelIds: List<Int>? = null
    var cityId: Int? = null

    var salaryType: Int? = null

    var salaryMin: Int? = null

    var salaryMax: Int? = null
    var salaryUnit: Int? = null
    var oldIndustry: String? = null
    var oldTableId: Int? = null
    var regionId: Int? = null
    var workFromHome: Boolean? = null
    var address: String? = null
    var ifAzSales: Int? = null
    var ifAzCustomerService: Int? = null
    var ifAzCaregiver: Int? = null
    var authorized: Int? = null
    var labelValue: Int? = null
    var labelDescription: String? = null
}