package com.example.myapplication.ui.dashboard.preference

import android.content.Intent
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.ui.network.model.JobClassificationVO
import com.example.myapplication.ui.network.model.SysDeptModel
import java.util.Locale

class SearchChannelListAdapter(private val activity: ChannelSearchPageActivity) : RecyclerView.Adapter<SearchChannelListAdapter.ViewHolder>() {

    private val tag : String  = this::class.java.simpleName
    private val dataList = mutableListOf<JobClassificationVO>()
    private var searchKeyword: String = ""

    //在内部类里面获取到item里面的组件
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        //recruiter view
        var row1: TextView = view.findViewById(R.id.searchCityTextView)
    }

    //重写的第一个方法，用来给制定加载那个类型的Recycler布局
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.city_search_item,parent,false)
        var viewHolder = ViewHolder(view)
        //单机事件
        viewHolder.itemView.setOnClickListener {
            var position = viewHolder.adapterPosition
            if(dataList.size > 0){
                var jobClassificationVO = dataList[position]
                if (jobClassificationVO != null) {
                    activity.onItemClick(jobClassificationVO)
                }
            }
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(dataList.size > 0){
            val jobClassificationVO = dataList[position]
            if (jobClassificationVO != null) {
                if (searchKeyword.isNotEmpty()) {
                    val itemText = jobClassificationVO.buildJobClassification
                    val startPos = itemText?.lowercase(Locale.getDefault())?.indexOf(searchKeyword.lowercase(Locale.getDefault()))

                    if (startPos != -1) {
                        val spannableString = SpannableString(itemText)
                        val endPos = startPos?.plus(searchKeyword.length)
                        if (startPos != null) {
                            if (endPos != null) {
                                spannableString.setSpan(
                                    ForegroundColorSpan(Color.RED),
                                    startPos,
                                    endPos,
                                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                                )
                            }
                        }
                        holder.row1.text = spannableString
                    } else {
                        holder.row1.text =  itemText
                    }
                } else {
                    holder.row1.text = jobClassificationVO.buildJobClassification
                }

            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size;
    }

    fun setData(dataList: List<JobClassificationVO>) {
        this.dataList.clear()
        this.dataList.addAll(dataList)
        notifyDataSetChanged()
    }

    fun setKeyWord(searchKeyword: String){
        this.searchKeyword = searchKeyword
    }

}