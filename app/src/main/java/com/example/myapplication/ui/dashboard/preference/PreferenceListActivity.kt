package com.example.myapplication.ui.dashboard.preference

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityPreferenceListBinding


class PreferenceListActivity : AppCompatActivity() {

    private val tag : String  = this::class.java.simpleName

    private lateinit var binding: ActivityPreferenceListBinding

    private lateinit var recyclerView: RecyclerView
    private lateinit var preferenceListAdapter: PreferenceListAdapter
    private lateinit var preferenceListViewModel: PreferenceListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreferenceListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferenceListViewModel=
            ViewModelProvider(this)[PreferenceListViewModel::class.java]
        recyclerView = binding.preferenceListRecycleView
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        preferenceListAdapter = PreferenceListAdapter()
        recyclerView.adapter = preferenceListAdapter

        preferenceListViewModel.dataList.observe(this){
            preferenceListAdapter.setData(it)
        }

//        supportActionBar?.elevation = 0.0f
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        preferenceListViewModel.initData()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}