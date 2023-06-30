package com.example.myapplication.ui.dashboard.preference

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.myapplication.databinding.ActivitySearchChannelPageBinding


class ChannelSearchPageActivity : AppCompatActivity() {

    private val tag : String  = this::class.java.simpleName

    private lateinit var binding: ActivitySearchChannelPageBinding
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchChannelPageBinding.inflate(layoutInflater)
        searchView = binding.citySearchView
        setContentView(binding.root)

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

        val itemDecorationHor =
            DividerItemDecoration(applicationContext, DividerItemDecoration.HORIZONTAL)
        itemDecorationHor.setDrawable(ColorDrawable(Color.parseColor("#e0e0e0")))
        val itemDecorationVer =
            DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
        itemDecorationVer.setDrawable(ColorDrawable(Color.parseColor("#e0e0e0")))
    }

}