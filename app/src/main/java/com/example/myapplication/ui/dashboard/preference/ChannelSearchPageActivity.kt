package com.example.myapplication.ui.dashboard.preference

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivitySearchChannelPageBinding
import com.example.myapplication.ui.network.model.JobClassificationVO
import com.example.myapplication.ui.network.model.SysDeptModel


class ChannelSearchPageActivity : AppCompatActivity() {

    private val tag : String  = this::class.java.simpleName

    private lateinit var binding: ActivitySearchChannelPageBinding
    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView
    private lateinit var channelSearchPageViewModel: ChannelSearchPageViewModel
    private lateinit var searchChannelListAdapter: SearchChannelListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchChannelPageBinding.inflate(layoutInflater)
        searchView = binding.channelSearchView
        recyclerView = binding.cityRecyclerView
        setContentView(binding.root)
        channelSearchPageViewModel =
            ViewModelProvider(this)[ChannelSearchPageViewModel::class.java]
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        searchChannelListAdapter = SearchChannelListAdapter(this)
        //initViews()
        recyclerView.adapter = searchChannelListAdapter

        // 观察数据变化
        channelSearchPageViewModel.dataList.observe(this) {
            searchChannelListAdapter.setData(it)
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                performSearch(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                performSearch(newText)
                return true
            }
        })

//        supportActionBar?.elevation = 0.0f
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun onItemClick(jobClassificationVO: JobClassificationVO) {
        val intent = Intent(this, PreferenceEditActivity::class.java)
        intent.putExtra("channelId", jobClassificationVO.dictItemCode)
        intent.putExtra("channel", jobClassificationVO.dictItemName)
        intent.putExtra("buildJobClassification", jobClassificationVO.buildJobClassification)
        setResult(RESULT_OK,intent)
        finish()
    }

    private fun performSearch(query: String) {
        // 执行搜索操作
        channelSearchPageViewModel.initData(query)
        searchChannelListAdapter.setKeyWord(query)
        recyclerView.scrollToPosition(0) // 滚动到顶部
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}