package com.example.myapplication.ui.network.model

import java.io.Serializable

class BaseDictModel : Serializable {
    var dictItemName: String? = null
    var dictItemCode: String? = null
    var dictType: String? = null
    var dictId: Int? = null
    var isDefault: Int? = null
    var remark: Int? = null
    var itemLevel: Int? = null
    var joinItemName: String? = null
    var salaryValue: Int? = null
    var salaryUnit: Int? = null
    var sales = false
    var id: String? = null
    var pid: String? = null
    var child: List<BaseDictModel>? = null

}