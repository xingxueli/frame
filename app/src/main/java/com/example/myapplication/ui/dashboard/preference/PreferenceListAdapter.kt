package com.example.myapplication.ui.dashboard.preference

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.ui.network.model.CandidateInfoBrief
import com.example.myapplication.ui.network.model.CandidatePreference
import com.example.myapplication.ui.network.model.IndustryTagInfo

class PreferenceListAdapter : RecyclerView.Adapter<PreferenceListAdapter.ViewHolder>() {

    private val tag : String  = "PreferenceListAdapter";
    private var dataList = listOf<CandidatePreference>()

    //在内部类里面获取到item里面的组件
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        var city: TextView = view.findViewById(R.id.activity_preference_list_item_city)
        var industry:TextView = view.findViewById(R.id.activity_preference_list_item_industry)
        var channel:TextView = view.findViewById(R.id.activity_preference_list_item_channel)
        var salary:TextView = view.findViewById(R.id.activity_preference_list_item_salary)
    }

    //重写的第一个方法，用来给制定加载那个类型的Recycler布局
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_preference_list_item,parent,false)
        var viewHolder = ViewHolder(view)
        //单机事件
        viewHolder.itemView.setOnClickListener {
            var position = viewHolder.adapterPosition
            var item = dataList[position]
            Toast.makeText(parent.context,"你点击的简历是${item.id}",Toast.LENGTH_SHORT).show()
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        var industryTags = item.industryTags
        var industryTagInfo = ""
        if (industryTags != null) {
            industryTagInfo = industryTags.map { it.industry }.joinToString("、")
        }
        if (item != null) {
            holder.channel.text = item.channel
            holder.industry.text = industryTagInfo
            holder.city.text = item.city
            holder.salary.text = item.salary
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(dataList: List<CandidatePreference>) {
        this.dataList = dataList
        notifyDataSetChanged()
    }

}