package com.example.myapplication.ui.network.model

class JobBriefWithAnalysis : JobListBriefInfoC() {
    var analysis: String? = null
    var regionId: Int? = null
    var regionUrl: String? = null
    var showNewTag = false
    var supplementFlag: Boolean? = null
    var alg_trace_id: String? = null
    var distance: Double? = null
    val distanceText: String? = null
}