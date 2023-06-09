package com.example.myapplication.ui.home.recommend

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.ui.network.model.JobBriefWithAnalysis

class RecommendRecruiterAdapter : RecyclerView.Adapter<RecommendRecruiterAdapter.ViewHolder>() {

    private val tag : String  = "RecommendRecruiterAdapter";
    private val recruiterDataList = mutableListOf<JobBriefWithAnalysis>()


    //在内部类里面获取到item里面的组件
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        //recruiter view
//        var row1: ImageView = view.findViewById(R.id.recruiter_row1)
        var row2: TextView = view.findViewById(R.id.recruiter_row2)
        var row3: TextView = view.findViewById(R.id.recruiter_row3)
        var row4: TextView = view.findViewById(R.id.recruiter_row4)
        var row5: TextView = view.findViewById(R.id.recruiter_row5)
        var row6: TextView = view.findViewById(R.id.recruiter_row6)
        var row7: TextView = view.findViewById(R.id.recruiter_row7)
    }

    //重写的第一个方法，用来给制定加载那个类型的Recycler布局
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_recommend_recruiter_item,parent,false)
        var viewHolder = ViewHolder(view)
        //单机事件
        viewHolder.itemView.setOnClickListener {
            var position = viewHolder.adapterPosition
            if(recruiterDataList.size > 0){
                var jobBriefWithAnalysis = recruiterDataList[position]
                if (jobBriefWithAnalysis != null) {
                    Toast.makeText(parent.context,"你点击的preferenceID是${jobBriefWithAnalysis.id}",Toast.LENGTH_SHORT).show()
                }
            }
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(recruiterDataList.size > 0){
            val jobBriefWithAnalysis = recruiterDataList[position]
            if (jobBriefWithAnalysis != null) {
                holder.row2.text = jobBriefWithAnalysis.title
                holder.row3.text = jobBriefWithAnalysis.salary
                var company = jobBriefWithAnalysis.company
                if (company != null) {
                    if(company.financing.isNullOrEmpty()){
                        holder.row4.text = company.simpleName
                    }else{
                        holder.row4.text = company.simpleName + " · " + company.financing
                    }
                }
                var skillTags = jobBriefWithAnalysis.skillTags
                holder.row5.text = jobBriefWithAnalysis.experience + " " + jobBriefWithAnalysis.degree
                if (!skillTags.isNullOrEmpty()) {
                    holder.row6.text = skillTags.joinToString()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return recruiterDataList.size;
    }

    fun setRecruiterData(dataList: List<JobBriefWithAnalysis>) {
        this.recruiterDataList.clear()
        this.recruiterDataList.addAll(dataList)
        notifyDataSetChanged()
    }

    fun addRecruiterData(newDataList: List<JobBriefWithAnalysis>) {
        val insertPosition = recruiterDataList.size
        recruiterDataList.addAll(newDataList)
        notifyItemRangeInserted(insertPosition, newDataList.size)
    }

}