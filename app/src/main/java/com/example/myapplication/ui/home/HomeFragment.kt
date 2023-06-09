package com.example.myapplication.ui.home

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.ui.home.recommend.RecommendFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class HomeFragment : Fragment(){

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var searchView: SearchView
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var adapter: TabLayoutChildViewPager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        tabLayout = binding.myTablelayout
        viewPager2 = binding.myViewpage2
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = TabLayoutChildViewPager(childFragmentManager, lifecycle)
        homeViewModel.candidateDataList.observe(viewLifecycleOwner){
            var tabFragments = mutableListOf<Fragment>()
            var tabViews = mutableListOf<View>()
            for (preferenceOption in it) {
                val tabView: View = LayoutInflater.from(activity).inflate(R.layout.custom_tab_layout, null)
                val tabText = tabView.findViewById<TextView>(R.id.tab_text)
                tabText.text = preferenceOption.channel
                tabViews.add(tabView)
                tabFragments.add(RecommendFragment.newInstance(preferenceOption.preferenceId!!))
            }
            adapter.addFragments(tabFragments)
            adapter.addFragmentTabViews(tabViews)
            viewPager2.adapter = adapter
            TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
                tab.customView = adapter.getTabView(position)
                viewPager2.setCurrentItem(tab.position, true)
            }.attach()
        }

        homeViewModel.recruiterDataList.observe(viewLifecycleOwner){
            var tabFragments = mutableListOf<Fragment>()
            var tabViews = mutableListOf<View>()
            for (recruiterPreference in it) {
                val tabView: View = LayoutInflater.from(activity).inflate(R.layout.custom_tab_layout, null)
                val tabText = tabView.findViewById<TextView>(R.id.tab_text)
                tabText.text = recruiterPreference.channel
                tabViews.add(tabView)
                tabFragments.add(RecommendFragment.newInstance(recruiterPreference.jobId!!))
            }
            adapter.addFragments(tabFragments)
            adapter.addFragmentTabViews(tabViews)
            viewPager2.adapter = adapter
            TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
                tab.customView = adapter.getTabView(position)
                viewPager2.setCurrentItem(tab.position, true)
            }.attach()
        }

        bindTabView()

        //先渲染视图，再启动数据
        homeViewModel.initData()

//        val toolbar: Toolbar = binding.toolBar
//        toolbar.inflateMenu(R.menu.menu_fragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    class TabLayoutChildViewPager(manager: FragmentManager, lifecycle: Lifecycle) :  FragmentStateAdapter(manager,lifecycle ){
        private var fragmentList : MutableList<Fragment> =ArrayList()
        private var tabViewList : MutableList<View> =ArrayList()
        override fun getItemCount(): Int {
            return fragmentList.size
        }

        override fun createFragment(position: Int): Fragment {
            return  fragmentList[position]
        }


        fun addFragments(fragments: MutableList<Fragment>) {
            fragmentList = fragments;
        }

        fun addFragmentTabViews(tabViews: MutableList<View>) {
            tabViewList = tabViews;
        }

        fun getTabView(position: Int): View? {
            var tabView : View = tabViewList[position]
            if (position != 0) {
//                tabView.setAlpha(0.5f);
                val tabText = tabView?.findViewById<TextView>(com.example.myapplication.R.id.tab_text)
                if (tabText != null) {
                    tabText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16F)
                }
            } else {
//                tabView.setScaleX(1.1f);
//                tabView.setScaleY(1.1f);
                val tabText = tabView?.findViewById<TextView>(com.example.myapplication.R.id.tab_text)
                if (tabText != null) {
                    tabText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18F)
                    tabText?.paint?.isFakeBoldText = true
                }
            }
            return tabView
        }
    }

    private fun bindTabView(){
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                changeTabSelect(tab) //Tab获取焦点
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                changeTabNormal(tab) //Tab失去焦点
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    /**
     * 改变TabLayout的View到选中状态
     * 使用属性动画改编Tab中View的状态
     */
    private fun changeTabSelect(tab: TabLayout.Tab) {
        val view = tab.customView
//        val anim = ObjectAnimator
//            .ofFloat(view, "alpha", 1.0f, 1.1f)
//            .setDuration(200)
//        anim.start()
//        anim.addUpdateListener { animation ->
//            val cVal = animation.animatedValue as Float
//            view!!.alpha = 0.5f + (cVal - 1f) * (0.5f / 0.1f)
//            view.scaleX = cVal
//            view.scaleY = cVal
//        }
        val tabText2 = view?.findViewById<TextView>(com.example.myapplication.R.id.tab_text)
        if (tabText2 != null) {
            tabText2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18F)
            tabText2?.paint?.isFakeBoldText = true
        }
    }

    /**
     * 改变TabLayout的View到未选中状态
     */
    private fun changeTabNormal(tab: TabLayout.Tab) {
        val view = tab.customView
//        val anim = ObjectAnimator
//            .ofFloat(view, "alpha", 1.0f, 0.9f)
//            .setDuration(200)
//        anim.start()
//        anim.addUpdateListener { animation ->
//            val cVal = animation.animatedValue as Float
//            view!!.alpha = 1f - (1f - cVal) * (0.5f / 0.1f)
//            view.scaleX = cVal
//            view.scaleY = cVal
//        }
        val tabText2 = view?.findViewById<TextView>(com.example.myapplication.R.id.tab_text)
        if (tabText2 != null) {
            tabText2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16F)
            tabText2?.paint?.isFakeBoldText = false
        }
    }

}