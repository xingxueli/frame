package com.example.myapplication.ui.network.model

class JobClassificationVO {
    var dictItemCode: String? = null
    var dictItemName: String? = null
    var itemLevel: Int? = null
    var buildJobClassification: String? = null
    var sales = false

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as JobClassificationVO
        return dictItemCode == that.dictItemCode
    }
}