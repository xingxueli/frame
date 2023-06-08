package com.example.myapplication.ui.network.model

import java.io.Serializable

class Location : Serializable {
    var country: String? = null
    var city: String? = null
    var cityId: Int? = null

    /**
     * 地址名称
     */
    var name: String? = null

    /**
     * 详细地址
     */
    var address: String? = null

    /**
     * 街道门牌号
     */
    var streetNumber: String? = null
    var longitude: Double? = null
    var latitude: Double? = null
    var regionId: Int? = null
    var regionUrl: String? = null
    var state: String? = null
    var stateShortName: String? = null
}