package com.example.myapplication.ui.dashboard.preference

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityPreferenceListBinding


class PreferenceListActivity : AppCompatActivity() {

    private val tag : String  = "PreferenceListActivity";

    private lateinit var binding: ActivityPreferenceListBinding

    private lateinit var recyclerView: RecyclerView
    private lateinit var preferenceListAdapter: PreferenceListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreferenceListBinding.inflate(layoutInflater)
        val preferenceListViewModel=
            ViewModelProvider(this)[PreferenceListViewModel::class.java]
        recyclerView = binding.preferenceListRecycleView
        recyclerView!!.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        preferenceListAdapter = PreferenceListAdapter()
        recyclerView!!.adapter = preferenceListAdapter
        preferenceListViewModel.initData()
        preferenceListViewModel.dataList.observe(this){
            preferenceListAdapter?.setData(it)
        }

        setContentView(binding.root)
    }

    fun onClick(view: View) {
        when (view) {
            binding.preferenceListBack -> finish()
        }
    }

}