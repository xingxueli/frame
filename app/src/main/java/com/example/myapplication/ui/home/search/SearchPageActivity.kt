package com.example.myapplication.ui.home.search

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivitySearchPageBinding


class SearchPageActivity : AppCompatActivity() {

    private val tag : String  = "SearchPageActivity";

    private lateinit var binding: ActivitySearchPageBinding
    private lateinit var searchView: SearchView

    private var recyclerViewHistory: RecyclerView? = null
    private var ivHistoryArrow: ImageView? = null
    private var tvHistory: TextView? = null

    private var ivHistoryDelete: ImageView? = null
    private var tvHistoryDeleteFinish: TextView? = null

    private val historyContentList: MutableList<String> = ArrayList()

    private var historyAdapter: SearchPageAdapter? = null

    private var isHidePartialHistory = true //是否隐藏部分历史,默认为true

    private var isInHistoryDelete = false //是否处于删除历史模式


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchPageBinding.inflate(layoutInflater)
        searchView = binding.searchActivitySearchView
        setContentView(binding.root)

        initData()
        initViews()
    }

    private fun performSearchView() {
        var queryString : String = searchView.query.toString()
        if(queryString.isNullOrEmpty()){
            Toast.makeText(this, "搜索内容不能为空", Toast.LENGTH_SHORT).show()
            return
        }
        performSearch(queryString)
    }

    private fun performSearch(query: String) {
        // 执行搜索操作
        Toast.makeText(this, "Searching: $query", Toast.LENGTH_SHORT).show()
    }

    private fun initViews() {

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                performSearch(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // 在此处处理搜索文本变化的逻辑
                return true
            }
        })

        recyclerViewHistory = binding.searchActivityRecyclerView
        tvHistory = binding.tvHistory
        ivHistoryArrow = binding.ivArrow
        ivHistoryDelete = binding.ivDelete
        tvHistoryDeleteFinish = binding.tvFinish
        val itemDecorationHor =
            DividerItemDecoration(applicationContext, DividerItemDecoration.HORIZONTAL)
        itemDecorationHor.setDrawable(ColorDrawable(Color.parseColor("#e0e0e0")))
        val itemDecorationVer =
            DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
        itemDecorationVer.setDrawable(ColorDrawable(Color.parseColor("#e0e0e0")))
        historyAdapter = SearchPageAdapter()
        //历史记录先不显示全部
        historyAdapter!!.setData(historyContentList)
        val gmHistory = GridLayoutManager(applicationContext, 2)
        recyclerViewHistory!!.layoutManager = gmHistory
        recyclerViewHistory!!.addItemDecoration(itemDecorationHor)
        recyclerViewHistory!!.addItemDecoration(itemDecorationVer)
        recyclerViewHistory!!.adapter = historyAdapter
    }

    /**
     * 初始化数据，到时可自定义绑定
     */
    private fun initData() {
        historyContentList.add("零食")
        historyContentList.add("美团外卖")
        historyContentList.add("极限挑战")
        historyContentList.add("安卓")
        historyContentList.add("php开发")
        historyContentList.add("鹿晗")
        historyContentList.add("奔跑吧兄弟")
        historyContentList.add("深圳房价")
        historyContentList.add("深圳大学")
        historyContentList.add("科技园")
        historyContentList.add("老梁")
        historyContentList.add("五十一度")
        historyContentList.add("回来吧")
        historyContentList.add("黄渤")
        historyContentList.add("最近上映电影")
        historyContentList.add("小说")
        historyContentList.add("酷狗")
    }

    private val historyBarItemClickListener: OnItemClickListener = object : OnItemClickListener {
        override fun onItemClick(view: View?, position: Int) {
            Toast.makeText(
                applicationContext, String.format(
                    "你点击了-%s",
                    historyContentList[position]
                ), Toast.LENGTH_SHORT
            ).show()
        }

    }

    private val historyBarItemDeleteListener: OnItemDeleteListener =
        object : OnItemDeleteListener {
            override fun onItemDelete(view: View?, position: Int) {
                Toast.makeText(
                    applicationContext, String.format(
                        "你删除了-%s",
                        historyContentList[position]
                    ), Toast.LENGTH_SHORT
                ).show()
                historyContentList.removeAt(position)
                historyAdapter?.setDisplayCount(historyContentList.size)
                historyAdapter?.notifyDataSetChanged()
            }
        }

    @SuppressLint("NotifyDataSetChanged")
    private fun onHistoryHideToggle() {
        isHidePartialHistory = !isHidePartialHistory
        if (isHidePartialHistory) {
            val displayCount = if (historyContentList.size > 4) 4 else historyContentList.size
            historyAdapter?.setDisplayCount(displayCount)
            ivHistoryArrow!!.rotation = 180f
        } else {
            historyAdapter?.setDisplayCount(historyContentList.size)
            ivHistoryArrow!!.rotation = 0f
        }
        historyAdapter?.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun onHistoryDeleteToggle() {
        isInHistoryDelete = !isInHistoryDelete
        if (isInHistoryDelete) {
            //显示所有历史记录
            if (isHidePartialHistory) {
                onHistoryHideToggle()
            }
            historyAdapter?.setShowDelete(true)
            ivHistoryDelete!!.visibility = View.GONE
            tvHistoryDeleteFinish!!.visibility = View.VISIBLE
        } else {
            historyAdapter?.setShowDelete(false)
            ivHistoryDelete!!.visibility = View.VISIBLE
            tvHistoryDeleteFinish!!.visibility = View.GONE
        }

        //在删除历史不能点击历史记录
        ivHistoryArrow!!.visibility = if (isInHistoryDelete) View.GONE else View.VISIBLE
        tvHistory!!.isClickable = !isInHistoryDelete
        historyAdapter?.notifyDataSetChanged()
    }

    fun onClick(view: View) {
        when (view) {
            binding.searchText -> performSearchView()
            binding.searchBack -> finish()
            tvHistory, ivHistoryArrow -> onHistoryHideToggle()
            ivHistoryDelete, tvHistoryDeleteFinish -> onHistoryDeleteToggle()
        }
    }

}