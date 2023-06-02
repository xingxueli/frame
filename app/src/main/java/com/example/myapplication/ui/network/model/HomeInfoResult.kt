package com.example.myapplication.ui.network.model

class HomeInfoResult {
    var code: Int? = null
    var data: PageList? = null
    var message: String? = null

    inner class PageList {
        var hasNext = false
        var list: MutableList<CandidateInfoBrief>? = null
    }

}
