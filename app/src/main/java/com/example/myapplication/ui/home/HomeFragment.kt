package com.example.myapplication.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.ui.home.search.SearchPageActivity
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.ui.home.recommend.RecommendFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


/**
 *
 */
class HomeFragment : Fragment(),MenuProvider{

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var searchView: SearchView
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
//    private lateinit var toolbar: Toolbar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val homeViewModel =
//            ViewModelProvider(this)[HomeViewModel::class.java]
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        requireActivity().addMenuProvider(this)// 注册当前 Fragment 作为菜单提供者
//        toolbar = binding.toolBar
        tabLayout = binding.myTablelayout
        viewPager2 = binding.myViewpage2
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = TabLayoutChildViewPager(childFragmentManager, lifecycle)
        adapter.addFragments(initChildFragmentList())
        adapter.addFragmentTabViews(initTabViewList())
        viewPager2.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.customView = adapter.getTabView(position)
            viewPager2.setCurrentItem(tab.position, true)
        }.attach()

        bindTabView()
//        setupToolbar()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        requireActivity().removeMenuProvider(this) // 在 Fragment 销毁时取消注册菜单提供者
    }

//    private fun setupToolbar() {
//        (activity as AppCompatActivity).setSupportActionBar(toolbar)
//        (activity as AppCompatActivity).supportActionBar?.apply {
//            title = "My Fragment"
//            // 其他 Toolbar 相关设置，如显示返回按钮等
//        }
//    }

    private fun initChildFragmentList():MutableList<Fragment>{
        var recommendFragment1 = RecommendFragment.newInstance(1)
        var recommendFragment2 = RecommendFragment.newInstance(2)
        var recommendFragment3 = RecommendFragment.newInstance(3)
        return mutableListOf(recommendFragment1,recommendFragment2,recommendFragment3)
    }

    private fun initTabViewList() : MutableList<View> {

        // 创建标签样式
        val tabView1: View = LayoutInflater.from(activity).inflate(com.example.myapplication.R.layout.custom_tab_layout, null)
//        val tabIcon1 = tabView1.findViewById<ImageView>(R.id.tab_icon)
        val tabText1 = tabView1.findViewById<TextView>(com.example.myapplication.R.id.tab_text)
//        tabIcon1.setImageResource(R.drawable.tab_icon1)
        tabText1.text = "Java"

        val tabView2: View = LayoutInflater.from(activity).inflate(com.example.myapplication.R.layout.custom_tab_layout, null)
        val tabText2 = tabView2.findViewById<TextView>(com.example.myapplication.R.id.tab_text)
        tabText2.text = "技术总监"

        val tabView3: View = LayoutInflater.from(activity).inflate(com.example.myapplication.R.layout.custom_tab_layout, null)
        val tabText3 = tabView3.findViewById<TextView>(com.example.myapplication.R.id.tab_text)
        tabText3.text = "CTO"
        return mutableListOf(tabView1,tabView2,tabView3);
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
        binding.myTablelayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
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

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_fragment,menu);
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when(menuItem.itemId){
            R.id.action_add -> TODO()
            R.id.action_search -> startActivity(Intent(requireContext(), SearchPageActivity::class.java))
        }
        return super.onContextItemSelected(menuItem)
    }

}