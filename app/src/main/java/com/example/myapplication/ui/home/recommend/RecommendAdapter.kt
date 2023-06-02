package com.example.myapplication.ui.home.recommend

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

class RecommendAdapter : RecyclerView.Adapter<RecommendAdapter.ViewHolder>() {

    private val tag : String  = "RecommendAdapter";
    private val dataList = mutableListOf<CandidateInfoBrief>()

    //在内部类里面获取到item里面的组件
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        var newPicture: ImageView =view.findViewById(R.id.newPicture)
        var newName: TextView = view.findViewById(R.id.newsName)
        var newDetail:TextView = view.findViewById(R.id.newDetail)
    }

    //重写的第一个方法，用来给制定加载那个类型的Recycler布局
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_recommend_item,parent,false)
        var viewHolder = ViewHolder(view)
        //单机事件
        viewHolder.itemView.setOnClickListener {
            var position = viewHolder.adapterPosition
            var item= dataList.get(position)
            if (item != null) {
                Toast.makeText(parent.context,"你点击的新闻是${item.id}",Toast.LENGTH_SHORT).show()
            }
        }
        viewHolder.newPicture.setOnClickListener {
            var position = viewHolder.adapterPosition
            var item = dataList.get(position)
            if (item != null) {
                Toast.makeText(parent.context,"你点击的图片是${item.id}",Toast.LENGTH_SHORT).show()
            }
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList.get(position)
        if (item != null) {
            holder.newDetail.text = item.uid
            holder.newName.text = item.name
            item.avatar = "https://test.hirect.ai/hirect/file/resources/803644712392196096/CANDIDATE_AVATAR/ff14cd2b25e7ba52eceac5e6684cfe72d55ca4d8b70689dc2ad33a41aaba173fac9a8a4df2c657221452d3fb3571c51f/download/1.jpeg/small"
            Glide.with(holder.itemView.getContext())
                .load(item.avatar)
//                .placeholder(R.drawable.placeholder_image) // 设置占位图
//                .error(R.drawable.error_image) // 设置错误图
                .into(holder.newPicture);

        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(dataList: List<CandidateInfoBrief>) {
        this.dataList.clear()
        this.dataList.addAll(dataList)
//        Log.i(tag,Gson().toJson(dataList))
        notifyDataSetChanged()
    }

    fun addData(newDataList: List<CandidateInfoBrief>) {
        val insertPosition = dataList.size
        dataList.addAll(newDataList)
        notifyItemRangeInserted(insertPosition, newDataList.size)
    }

}