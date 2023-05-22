package com.example.myapplication.ui.net.model

class HomeInfoResult {
    var code: Int? = null
    var data: PageList? = null
    var message: String? = null

    inner class PageList {
        var hasNext = false
        var list: List<CandidateInfoBrief>? = null
    }

}
