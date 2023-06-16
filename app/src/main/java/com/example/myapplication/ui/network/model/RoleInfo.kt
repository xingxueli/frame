package com.example.myapplication.ui.network.model

class RoleInfo {
    /**
     * candidateId or recruiterId in our database
     */
    var id: Long? = null
    var uid: String? = null
    var sendBirdId: String? = null

    var agoraId: Long? = null

    /**
     * 当前身份的状态
     * user's status code , to tell the front the user if he finish his profile or publish a job
     */
    var status: Int? = null

    /**
     * attaching tips for user's status , just for reference
     */
    var tips: String? = null

    /**
     * user's avatar url
     * 头像
     */
    var avatar: String? = null

    /**
     * user's name
     * 姓名
     */
    var name: String? = null
    var firstName: String? = null
    var lastName: String? = null

    /**
     * user's gender
     * 性别
     */
    var gender: String? = null
    var mobile: String? = null

    /**
     * user's mail
     * 邮箱
     */
    var email: String? = null
    var registerType: Int? = null
    var emailVerified: Boolean? = null
    var regionId: Int? = null
    var isIntercept //是否填出激活拦截
            : Boolean? = null
    var accountStatus: Int? = null
    var firstVisitedTime: String? = null
    var brandId: Int? = null
}