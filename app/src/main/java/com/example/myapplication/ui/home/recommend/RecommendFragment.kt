package com.example.myapplication.ui.home.recommend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.myapplication.App
import com.example.myapplication.databinding.FragmentRecommendBinding
import com.example.myapplication.ui.local.SPUtils
import com.example.myapplication.ui.network.model.Role
import kotlin.properties.Delegates


/**
 *
 */
class RecommendFragment : Fragment() {

    private val tag : String  = "RecommendFragment";
    private var _binding: FragmentRecommendBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var recommendCandidateAdapter: RecommendCandidateAdapter
    private lateinit var recommendRecruiterAdapter: RecommendRecruiterAdapter
    private lateinit var recommendViewModel: RecommendViewModel
    private var id by Delegates.notNull<Long>()

    //如何从别的地方获取数据，而不是到处写接口
//    val sharedViewModel: SharedViewModel by viewModels()

    private var currentPage = 1
    private var hasNext = true

    companion object {
        fun newInstance(id: Long): RecommendFragment {
            val args = Bundle()
            args.putLong("foo", id)
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
        recommendViewModel =
            ViewModelProvider(this).get(RecommendViewModel::class.java)
        _binding = FragmentRecommendBinding.inflate(inflater, container, false)
        id = this.arguments?.getLong("foo")!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        var role = SPUtils.getInt(App.instance, "X-Role", 0)
        when(role){
            Role.RECRUITER.id -> {
                recommendCandidateAdapter = RecommendCandidateAdapter()
                binding.recyclerView.adapter = recommendCandidateAdapter
            }
            Role.CANDIDATE.id -> {
                recommendRecruiterAdapter = RecommendRecruiterAdapter()
                binding.recyclerView.adapter = recommendRecruiterAdapter
            }
        }

        // 观察数据变化
        recommendViewModel.candidateDataList.observe(viewLifecycleOwner) {
            // 更新数据源
            if (currentPage == 1) {
                // 下拉刷新
                recommendCandidateAdapter.setCandidateData(it)
                hasNext = true
            } else {
                // 底部加载更多
                recommendCandidateAdapter.addCandidateData(it)
            }
        }

        // 观察数据变化
        recommendViewModel.recruiterDataList.observe(viewLifecycleOwner) {
            // 更新数据源
            if (currentPage == 1) {
                // 下拉刷新
                recommendRecruiterAdapter.setRecruiterData(it)
                hasNext = true
            } else {
                // 底部加载更多
                recommendRecruiterAdapter.addRecruiterData(it)
            }
        }

        // 添加下拉刷新监听
        binding.swipeRefreshLayout.setOnRefreshListener {
            // 执行下拉刷新操作
            currentPage = 1
            recommendViewModel.refreshData(id)

            // 刷新完成后，停止刷新动画
            binding.swipeRefreshLayout.isRefreshing = false
        }
        // 添加底部加载更多监听
        bindScroll(binding.recyclerView,recommendViewModel)

        // 初始化数据
        currentPage = 1
        recommendViewModel.initData(currentPage,id)

        //初始化menu
//        initMenu()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // 添加底部加载更多监听
    private fun bindScroll(recyclerView : RecyclerView,recommendViewModel : RecommendViewModel){
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                val threshold = 5 // 定义触发懒加载的阈值，例如剩余5个item时触发加载

                if (hasNext && !recommendViewModel.isLoading && visibleItemCount + firstVisibleItemPosition >= totalItemCount - threshold && firstVisibleItemPosition >= 0) {
                    // 滚动到列表底部，执行底部加载更多操作
                    currentPage++
                    recommendViewModel.loadMoreData(currentPage,id)
                }
            }
        })
    }

}