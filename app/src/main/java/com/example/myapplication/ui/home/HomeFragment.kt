package com.example.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.ui.home.recommend.RecommendFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.util.Arrays

/**
 *
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val homeViewModel =
//            ViewModelProvider(this)[HomeViewModel::class.java]
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tableLayout: TabLayout = binding.myTablelayout
        var viewPager2: ViewPager2 = binding.myViewpage2
        val adapter = TabLayoutChildViewPager(childFragmentManager, lifecycle)
        adapter.addFragments(initChildFragmentList())
        adapter.addFragmentTitles(initPageTitleList())
        viewPager2.adapter = adapter
        TabLayoutMediator(tableLayout, viewPager2) { tab, position ->
            tab.text = adapter.getPageTitle(position)
            viewPager2.setCurrentItem(tab.position, true)
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initChildFragmentList():MutableList<Fragment>{
        var recommendFragment1 = RecommendFragment.newInstance(1)
        var recommendFragment2 = RecommendFragment.newInstance(2)
        var recommendFragment3 = RecommendFragment.newInstance(3)
        return mutableListOf(recommendFragment1,recommendFragment2,recommendFragment3)
    }

    private fun initPageTitleList() : MutableList<String> {
        return mutableListOf("推荐","关注","娱乐");
    }

    class TabLayoutChildViewPager(manager: FragmentManager, lifecycle: Lifecycle) :  FragmentStateAdapter(manager,lifecycle ){
        private var fragmentList : MutableList<Fragment> =ArrayList()
        private var titleList : MutableList<String> =ArrayList()
        override fun getItemCount(): Int {
            return fragmentList.size
        }

        override fun createFragment(position: Int): Fragment {
            return  fragmentList[position]
        }


        fun addFragments(fragments: MutableList<Fragment>) {
            fragmentList = fragments;
        }

        fun addFragmentTitles(titles: MutableList<String>) {
            titleList = titles;
        }

        fun getPageTitle(position: Int): CharSequence? {
            return titleList[position]
        }
    }
}