package com.example.myapplication.ui.dashboard.preference

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.AdapterView.OnItemClickListener
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivitySearchCityPageBinding
import com.example.myapplication.ui.network.model.SysDeptModel


class CitySearchPageActivity : AppCompatActivity() {

    private val tag : String  = this::class.java.simpleName

    private lateinit var binding: ActivitySearchCityPageBinding
    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView
    private lateinit var citySearchPageViewModel: CitySearchPageViewModel
    private lateinit var searchCityListAdapter: SearchCityListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchCityPageBinding.inflate(layoutInflater)
        searchView = binding.citySearchView
        recyclerView = binding.cityRecyclerView
        setContentView(binding.root)
        citySearchPageViewModel =
            ViewModelProvider(this)[CitySearchPageViewModel::class.java]
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        searchCityListAdapter = SearchCityListAdapter(this)
        //initViews()
        recyclerView.adapter = searchCityListAdapter

        // 观察数据变化
        citySearchPageViewModel.dataList.observe(this) {
            searchCityListAdapter.setData(it)
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

    fun onItemClick(sysDeptModel: SysDeptModel) {
        val intent = Intent(this, PreferenceEditActivity::class.java)
        intent.putExtra("city", sysDeptModel.city)
        intent.putExtra("cityId", "${sysDeptModel.deptId}")
        setResult(RESULT_OK,intent)
        finish()
    }

    private fun performSearch(query: String) {
        // 执行搜索操作
        citySearchPageViewModel.initData(query)
        searchCityListAdapter.setKeyWord(query)
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