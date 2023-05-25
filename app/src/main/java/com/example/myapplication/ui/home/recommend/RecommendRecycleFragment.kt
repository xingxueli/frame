//package com.example.myapplication.ui.home.recommend
//
//import android.annotation.SuppressLint
//import android.os.Bundle
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.ViewModelProvider
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.myapplication.R
//import com.example.myapplication.databinding.FragmentRecommendBinding
//import com.example.myapplication.databinding.FragmentRecommendRecycleBinding
//import com.google.gson.Gson
//
///**
// *
// */
//class RecommendRecycleFragment : Fragment() {
//
//    private val tag : String  = "RecommendRecycleFragment";
//    private var _binding: FragmentRecommendRecycleBinding? = null
//
//    // This property is only valid between onCreateView and
//    // onDestroyView.
//    private val binding get() = _binding!!
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        val recommendRecycleViewModel =
//            ViewModelProvider(this).get(RecommendRecycleViewModel::class.java)
//        _binding = FragmentRecommendRecycleBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    @SuppressLint("LongLogTag")
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        val recommendViewModel =
//            ViewModelProvider(this).get(RecommendViewModel::class.java)
//
//        super.onViewCreated(view, savedInstanceState)
//        var recyclerView = binding.recycleViewItem
//        var layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
//        recyclerView.layoutManager = layoutManager
//        var gson = Gson()
//        Log.i(tag,gson.toJson(recommendViewModel.homeInfoResult.value))
//        recyclerView.adapter = RecommendAdapter(recommendViewModel.homeInfoResult.value)
////        recommendViewModel.homeInfoResult.observe(viewLifecycleOwner) {
////            if(it.code != 2000){
////                Toast.makeText(context,it.message, Toast.LENGTH_SHORT).show()
////            }
////            recyclerView.adapter = RecommendAdapter(it)
////        }
//
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//
//}