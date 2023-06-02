package com.example.myapplication.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class ContentAdapter2 : RecyclerView.Adapter<ContentAdapter2.ViewHolder>() {

    private val tag : String  = "ContentAdapter2";
    private val dataList = mutableListOf<String>()
    private var isShowDelete = false //是否显示删除按钮
    private var displayCount = 0 //显示的数量，用于历史记录隐藏/显示所有
    private val itemClickListener: OnItemClickListener? = null
    private val itemDeleteListener: OnItemDeleteListener? = null

    //在内部类里面获取到item里面的组件
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var tvContent: TextView =view.findViewById(R.id.tv_content)
        var llDelete: LinearLayout = view.findViewById(R.id.linear_delete)
        var ivDelete: ImageView = view.findViewById(R.id.iv_delete);
    }

    //重写的第一个方法，用来给制定加载那个类型的Recycler布局
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_search_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.llDelete.visibility = if (isShowDelete) View.VISIBLE else View.GONE
        holder.tvContent.setText(dataList.get(position))

        if (isShowDelete) {
            holder.llDelete.setOnClickListener { v ->
                itemDeleteListener?.onItemDelete(
                    v,
                    position
                )
            }
        }

        //设置监听
        holder.itemView.setOnClickListener { v -> itemClickListener?.onItemClick(v, position) }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(dataList: List<String>) {
        this.dataList.clear()
        this.dataList.addAll(dataList)
        notifyDataSetChanged()
    }

    fun addData(newDataList: List<String>) {
        val insertPosition = dataList.size
        dataList.addAll(newDataList)
        notifyItemRangeInserted(insertPosition, newDataList.size)
    }

    fun setShowDelete(showDelete: Boolean) {
        isShowDelete = showDelete
    }

    fun setDisplayCount(displayCount: Int) {
        this.displayCount = displayCount
    }

}