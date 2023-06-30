package com.example.myapplication.ui.dashboard.preference

import android.util.Log
import com.example.myapplication.ui.network.model.BaseDictModel
import com.github.gzuliyujiang.wheelpicker.contract.LinkageProvider
import com.google.gson.Gson

class SalaryProvider(private val baseDictModelList:List<BaseDictModel>) : LinkageProvider {

    private val tag : String  = this::class.java.simpleName
    private var map : Map<String,List<String>> = mapOf()

    override fun firstLevelVisible(): Boolean {
        return true
    }

    override fun thirdLevelVisible(): Boolean {
        return false
    }

    override fun provideFirstData(): List<String> {
        var it = baseDictModelList
        var childs = it?.get(1)?.child
        var strings = childs?.map { r -> r.dictItemName }?.toMutableList()
        if (strings != null) {
            strings.add(0,it?.get(0)?.dictItemName)
        }
        map = childs?.map { r -> r.dictItemName to (r.child?.map { s->s.dictItemName }?.toList()) as List<String> }
            ?.toMap() as Map<String, List<String>>

        return strings as List<String>
    }

    override fun linkageSecondData(firstIndex: Int): List<String> {
        var firstIndex = firstIndex
        if (firstIndex == LinkageProvider.INDEX_NO_FOUND) {
            firstIndex = 0
        }
        val currentFirstData = provideFirstData()[firstIndex]
        if(map.get(currentFirstData) == null){
            return listOf()
        }
        return map.get(currentFirstData)!!
    }

    override fun linkageThirdData(firstIndex: Int, secondIndex: Int): List<*> {
        return ArrayList<Any>()
    }

    override fun findFirstIndex(firstValue: Any?): Int {
        if (firstValue == null) {
            return LinkageProvider.INDEX_NO_FOUND
        }
        var i = 0
        val n = SALARY_SHOW.size
        while (i < n) {
            val abbreviation = SALARY_SHOW[i]
            if (abbreviation == firstValue.toString()) {
                return i
            }
            i++
        }
        return LinkageProvider.INDEX_NO_FOUND
    }

    override fun findSecondIndex(firstIndex: Int, secondValue: Any?): Int {
        if (secondValue == null) {
            return LinkageProvider.INDEX_NO_FOUND
        }
        val secondData = linkageSecondData(firstIndex)
        var i = 0
        val n = secondData.size
        while (i < n) {
            val currentSecondData = secondData[i]
            if (currentSecondData == secondValue.toString()) {
                return i
            }
            i++
        }
        return LinkageProvider.INDEX_NO_FOUND
    }

    override fun findThirdIndex(firstIndex: Int, secondIndex: Int, thirdValue: Any): Int {
        return 0
    }

    private val SALARY_SHOW = arrayOf(
        "Negotiable","Rs 0 LPA", "Rs 1 LPA", "Rs 2 LPA", "Rs 3 LPA", "Rs 4 LPA", "Rs 5 LPA", "Rs 6 LPA", "Rs 7 LPA", "Rs 8 LPA",
        "Rs 9 LPA", "Rs 10 LPA", "Rs 11 LPA", "Rs 12 LPA", "Rs 13 LPA", "Rs 14 LPA", "Rs 15 LPA", "Rs 16 LPA", "Rs 17 LPA",
        "Rs 18 LPA", "Rs 19 LPA", "Rs 20 LPA", "Rs 21 LPA", "Rs 22 LPA", "Rs 23LPA", "Rs 24 LPA", "Rs 25 LPA", "Rs 26 LPA",
        "Rs 27 LPA", "Rs 28 LPA", "Rs 29 LPA", "Rs 30 LPA+"
    )

}