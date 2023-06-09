package com.example.myapplication.ui.home.recommend

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.App
import com.example.myapplication.R
import com.example.myapplication.ui.local.SPUtils
import com.example.myapplication.ui.network.model.CandidateInfoBrief
import com.example.myapplication.ui.network.model.JobBriefWithAnalysis
import com.example.myapplication.ui.network.model.Role

class RecommendCandidateAdapter : RecyclerView.Adapter<RecommendCandidateAdapter.ViewHolder>() {

    private val tag : String  = "RecommendCandidateAdapter";
    private val candidateDataList = mutableListOf<CandidateInfoBrief>()

    //在内部类里面获取到item里面的组件
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        //candidate view
        var row1: ImageView = view.findViewById(R.id.candidate_row1)
        var row2: TextView = view.findViewById(R.id.candidate_row2)
        var row3: TextView = view.findViewById(R.id.candidate_row3)
        var row4: TextView = view.findViewById(R.id.candidate_row4)
        var row5: TextView = view.findViewById(R.id.candidate_row5)
        var row6: TextView = view.findViewById(R.id.candidate_row6)
        var row7: TextView = view.findViewById(R.id.candidate_row7)
    }

    //重写的第一个方法，用来给制定加载那个类型的Recycler布局
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_recommend_candidate_item,parent,false)
        var viewHolder = ViewHolder(view)
        //单机事件
        viewHolder.itemView.setOnClickListener {
            var position = viewHolder.adapterPosition
            if(candidateDataList.size > 0){
                var candidateInfoBrief = candidateDataList.get(position)
                if (candidateInfoBrief != null) {
                    Toast.makeText(parent.context,"你点击的preferenceID是${candidateInfoBrief.preferenceId}",Toast.LENGTH_SHORT).show()
                }
            }
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(candidateDataList.size > 0){
            val candidateInfoBrief = candidateDataList.get(position)
            if (candidateInfoBrief != null) {
                holder.row2.text = candidateInfoBrief.name
                holder.row3.text = candidateInfoBrief.degree + " | " + candidateInfoBrief.identityShow + " | " + candidateInfoBrief.salary
                var experience = candidateInfoBrief.experience
                if (experience != null) {
                    holder.row4.text = experience.companyName + " · " + experience.designation
                    holder.row5.text = experience.startTime + " - " + experience.endTime
                }
                var education = candidateInfoBrief.education
                if (education != null) {
                    if(education.major.isNullOrEmpty()){
                        holder.row6.text = education.schoolName
                    }else{
                        holder.row6.text = education.schoolName + " · " + education.major
                    }
                    holder.row7.text = education.startTime + " - " + education.endTime
                }
//                candidateInfoBrief.avatar = "https://test.hirect.ai/hirect/file/resources/803644712392196096/CANDIDATE_AVATAR/ff14cd2b25e7ba52eceac5e6684cfe72d55ca4d8b70689dc2ad33a41aaba173fac9a8a4df2c657221452d3fb3571c51f/download/1.jpeg/small"
//                Glide.with(holder.itemView.getContext())
//                    .load(candidateInfoBrief.avatar)
//                .placeholder(R.drawable.placeholder_image) // 设置占位图
//                .error(R.drawable.error_image) // 设置错误图
//                    .into(holder.newPicture);

            }
        }
    }

    override fun getItemCount(): Int {
        return candidateDataList.size;
    }

    fun setCandidateData(dataList: List<CandidateInfoBrief>) {
        this.candidateDataList.clear()
        this.candidateDataList.addAll(dataList)
        notifyDataSetChanged()
    }

    fun addCandidateData(newDataList: List<CandidateInfoBrief>) {
        val insertPosition = candidateDataList.size
        candidateDataList.addAll(newDataList)
        notifyItemRangeInserted(insertPosition, newDataList.size)
    }

}