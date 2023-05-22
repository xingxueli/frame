package com.example.myapplication.ui.home.recommend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentRecommendBinding

/**
 *
 */
class RecommendFragment : Fragment() {

    private val tag : String  = "RecommendFragment";
    private var _binding: FragmentRecommendBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    companion object {
        fun newInstance(foo: Int): RecommendFragment {
            val args = Bundle()
            args.putInt("foo", foo)
            val fragment = RecommendFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val recommendViewModel =
            ViewModelProvider(this).get(RecommendViewModel::class.java)
        _binding = FragmentRecommendBinding.inflate(inflater, container, false)
        var recyclerView = binding.recycleViewItem
        var layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = RecommendAdapter(recommendViewModel.homeInfoResult.value)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recommendViewModel =
            ViewModelProvider(this).get(RecommendViewModel::class.java)

        super.onViewCreated(view, savedInstanceState)
        var recyclerView = binding.recycleViewItem
        var layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = RecommendAdapter(recommendViewModel.homeInfoResult.value)
//        recommendViewModel.homeInfoResult.observe(viewLifecycleOwner) {
//            if(it.code != 2000){
//                Toast.makeText(context,it.message, Toast.LENGTH_SHORT).show()
//            }
//            recyclerView.adapter = RecommendAdapter(it)
//        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}