package com.example.myapplication.ui.network.model

import java.io.Serializable

class SysDeptModel : Serializable {
    /**
     * id
     */
    var deptId: Int? = null

    /**
     * Address name
     */
    var areaName: String? = null
    var city: String? = null
    var alias: String? = null

    /**
     * Area abbreviation
     */
    var shortName: String? = null
    var orderNum: Int? = null
    var parentId: Int? = null

    /**
     * State abbreviation
     */
    var stateShortName: String? = null
    var state: String? = null

    /**
     * Is it a popular city
     */
    var isPopular: Int? = null

    /**
     * longitude
     */
    var longitude: Double? = null

    /**
     * latitude
     */
    var latitude: Double? = null
}