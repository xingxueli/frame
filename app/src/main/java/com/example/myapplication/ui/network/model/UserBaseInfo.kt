package com.example.myapplication.ui.network.model

open class UserBaseInfo {
    /**
     * user id in our database
     */
    var id: Long? = null

    /**
     * firebase uid
     */
    var uid: String? = null

    var sendBirdId: String? = null
    var recruiterSid: String? = null

    /**
     * candidate sendBird id
     */
    var candidateSid: String? = null

    var agoraId: Long? = null

    /**
     * recruiter agora Id
     */
    var recruiterAid: Long? = null

    /**
     * candidate agora id
     */
    var candidateAid: Long? = null

    /**
     * user's mobile phone number
     */
    var mobile: String? = null

    /**
     * user's current role , such as un-choose,recruiter,candidate
     */
    var role: String? = null

    /**
     * the status of the user's account,which can be unconfirmed,normal,banned
     */
    var accountStatus: String? = null

    /**
     * firebase idToken
     */
    var idToken: String? = null

    /**
     * firebase refreshToken , use it to help +86 user to get new idToken
     * only when you login by mobile this field can exist
     */
    var refreshToken: String? = null

    /**
     * firebase customToken , for third party login
     * firebase 自定义token,用于第三方登录
     */
    var customToken: String? = null
    var regionId: Int? = null
    var email: String? = null
    var registerType: Int? = null
    var otherUid: String? = null
}