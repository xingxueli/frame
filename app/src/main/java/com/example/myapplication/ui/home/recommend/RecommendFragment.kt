package com.example.myapplication.ui.home.recommend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
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

    //如何从别的地方获取数据，而不是到处写接口
//    val sharedViewModel: SharedViewModel by viewModels()

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
        _binding = FragmentRecommendBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recommendViewModel =
            ViewModelProvider(this).get(RecommendViewModel::class.java)

        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        recommendViewModel.homeInfoResult.observe(viewLifecycleOwner) {
            if(it.code != 2000){
                Toast.makeText(context,it.message, Toast.LENGTH_SHORT).show()
            }
            binding.recyclerView.adapter = RecommendAdapter(it)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}