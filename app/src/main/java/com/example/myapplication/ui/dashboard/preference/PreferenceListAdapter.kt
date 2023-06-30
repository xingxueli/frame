package com.example.myapplication.ui.dashboard.preference

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.ui.home.login.VerifyActivity
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
        var imageView:ImageView = view.findViewById(R.id.activity_preference_list_item_image)
    }

    //重写的第一个方法，用来给制定加载那个类型的Recycler布局
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_preference_list_item,parent,false)
        // 设置子项的宽度为 match_parent
//        val layoutParams = view.layoutParams
//        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
//        view.layoutParams = layoutParams

        var viewHolder = ViewHolder(view)
        //单机事件
        viewHolder.itemView.setOnClickListener {
            var position = viewHolder.adapterPosition
            var item = dataList[position]

            val intent = Intent(parent.context, PreferenceEditActivity::class.java)
            intent.putExtra("jobTypeId", "${item.jobTypeId}")
            intent.putExtra("jobType", "${item.jobType}")
            intent.putExtra("preferenceId", "${item.id}")
            intent.putExtra("cityId", "${item.cityId}")
            intent.putExtra("city", "${item.city}")
            intent.putExtra("channel", "${item.channel}")
            intent.putExtra("channelId", "${item.channelId}")
            intent.putExtra("salary", "${item.salary}")
//            intent.pu.putExtra("channelId", "${item.industryTags}")
            parent.context.startActivity(intent)
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
            holder.city.text = "[" +item.city + "] "
            holder.salary.text = item.salary + " | "
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