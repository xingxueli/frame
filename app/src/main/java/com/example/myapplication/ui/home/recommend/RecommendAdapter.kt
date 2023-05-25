package com.example.myapplication.ui.home.recommend

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.ui.net.model.HomeInfoResult
import com.google.gson.Gson

class RecommendAdapter(var homeInfoResult: HomeInfoResult?) : RecyclerView.Adapter<RecommendAdapter.ViewHolder>() {

    private val tag : String  = "RecommendAdapter";
    private var recommendList = homeInfoResult?.data?.list

    //在内部类里面获取到item里面的组件
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        var newPicture: ImageView =view.findViewById(R.id.newPicture)
        var newName: TextView = view.findViewById(R.id.newsName)
        var newDetail:TextView = view.findViewById(R.id.newDetail)
    }

    //重写的第一个方法，用来给制定加载那个类型的Recycler布局
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i(tag, "enter onCreateViewHolder")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_recommend_detail,parent,false)
        var viewHolder = ViewHolder(view)
        //单机事件
        viewHolder.itemView.setOnClickListener {
            var position = viewHolder.adapterPosition
            var item= recommendList?.get(position)
            if (item != null) {
                Toast.makeText(parent.context,"你点击的新闻是${item.id}",Toast.LENGTH_SHORT).show()
            }
        }
        viewHolder.newPicture.setOnClickListener {
            var position = viewHolder.adapterPosition
            var item = recommendList?.get(position)
            if (item != null) {
                Toast.makeText(parent.context,"你点击的图片是${item.id}",Toast.LENGTH_SHORT).show()
            }
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i(tag, "enter onBindViewHolder")
        val item = recommendList?.get(position)
        var gson = Gson()
        Log.i(tag,gson.toJson(item))
//        holder.newPicture.setImageResource(news.picture)
//        holder.newName.text = news.name
        if (item != null) {
            holder.newDetail.text = item.uid
            holder.newName.text = item.uid
//            holder.newPicture.c = item.uid
            //glide 加载图片，剩下的懒得写，反正大差不差就是设置数据
//            Glide.with(holder.itemView.context)
//                .load(AllConfig.imgUrl+infoList.icon)
//                .into(holder.rvImg)
        }
    }

    override fun getItemCount(): Int {
        Log.i(tag, "enter getItemCount")
        var i = recommendList?.size ?: 0
        Log.i("getItemCount", i.toString())
        return i
    }

}