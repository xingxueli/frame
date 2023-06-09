package com.example.myapplication.ui.network.model

class CandidateInfoBrief {

    var id: Long? = null

    var uid: String? = null

    var name: String? = null
//
//    var firstName: String? = null
//
//    var lastName: String? = null
//
      var avatar: String? = null
//
//    /**
//     * candidate's gender
//     */
//    var gender: Int? = null
//
//    /**
//     * current status of the candidate , such as on working ,leave from the working and at home and so on
//     */
//    var status: Int? = null
//
//    /**
//     * 求职状态描述
//     */
//    var availabilityStatus: String? = null
//
    var preferenceId: Long? = null
//
//    var channelId: Int? = null
//
//    var channel: String? = null
//
//    var cityId: Int? = null
//
//    var city: String? = null
//
//    var salaryType: Int? = null
//
//    var salaryMin: Int? = null
//
//    var salaryMax: Int? = null
//
    /**
     * candidate's expect salary, for example 30000 ~ 50000
     */
    var salary: String? = null

    /**
     * years from the candidate started working, calculate
     * 工作年限（计算得出）
     */
//    var workYears: Int? = null
//
//    /**
//     * candidate's age , calculate by birth date
//     * 年龄（计算得出)
//     */
//    var age: Int? = null
//
//    /**
//     * the identity of the candidate , such as student , talent candidate and so on
//     * 身份 牛人 or 学生
//     */
//    var identity: Int? = null
//
//    /**
//     * 个人优势
//     * candidate's advantage
//     */
//    var advantage: String? = null
//
//    /**
//     * 个人主页
//     * candidate's homepage
//     */
//    var homepage: String? = null
    /**
     * the last education of the candidate
     * candidate can have many education experiences, but it is last one
     * 最高学历
     */
    var degree: String? = null

    /**
     * the last work experience of the candidate
     * 最近一份工作经验
     */
    var experience: ExperienceInfoBrief? = null

    var education: EducationInfoBrief? = null
//
//    var skillTags: List<String>? = null
//
//    var regionId: Int? = null
//
//    var expId: Int? = null
//
    var identityShow: String? = null
//
//    var onlineStatus: String? = null
//
//    var sendBirdId: String? = null
}