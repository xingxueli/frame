package com.example.myapplication.ui.network.model

class TabListResult : BaseResult(){
    var code: Int? = null
    var data: List<InfoList>? = null
    var message: String? = null
    var isSuccess = false

    inner class InfoList {
        var content: String? = null
    }
}
