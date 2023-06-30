package com.example.myapplication.ui.dashboard.preference

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityPreferenceEditBinding


class PreferenceEditActivity : AppCompatActivity(){

    private val tag : String  = this::class.java.simpleName

    private lateinit var binding: ActivityPreferenceEditBinding
    private lateinit var preferenceEditViewModel: PreferenceEditViewModel
    private lateinit var salaryPicker: SalaryPicker
    private var deptId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreferenceEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferenceEditViewModel=
            ViewModelProvider(this)[PreferenceEditViewModel::class.java]

        handleButton()
        handlePageInteraction()
        initSalaryData()
//        supportActionBar?.elevation = 0.0f
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initSalaryData(){
        preferenceEditViewModel.initData()

        preferenceEditViewModel.dataList.observe(this){
            salaryPicker = SalaryPicker(this)
            var salaryProvider = SalaryProvider(it)
            salaryPicker.setData(salaryProvider)

            salaryPicker.setOnLinkagePickedListener {
                    first, second, third ->
                Log.i(tag,"$first - $second")
            }
        }
    }


//    private fun addListener(){
//        // 在接收结果的Fragment中注册结果监听器
//        supportFragmentManager.setFragmentResultListener(resultKey, this) { _, result ->
//            val value1 = result.getInt("value1")
//            val value2 = result.getInt("value2")
//
//            // 处理接收到的结果
//            // 在这里可以根据接收到的结果执行相应的操作
//            Log.d("Result", "Received result: $value1   $value2")
//        }
//
//    }

    private fun handlePageInteraction(){
        val city = intent.getStringExtra("city")
        deptId = intent.getIntExtra("deptId",0)
        binding.textView11.text = city
        Log.d(tag, deptId.toString())
    }

    private fun handleButton(){
        // 使用颜色资源设置文本颜色
        val colorTeal = R.color.teal_200
        val colorWhite = R.color.white
        val colorOn = ContextCompat.getColor(this, colorWhite)
        val colorOff = ContextCompat.getColor(this, colorTeal)

        val leftTextView = binding.textView7
        val rightTextView = binding.textView8
        leftTextView.setOnClickListener {
            leftTextView.setBackgroundResource(R.drawable.setting_left_on)
            rightTextView.setBackgroundResource(R.drawable.setting_right_off)
            leftTextView.setText(R.string.full_time)
            rightTextView.setText(R.string.part_time)
            leftTextView.setTextColor(colorOn)
            rightTextView.setTextColor(colorOff)
        }
        rightTextView.setOnClickListener {
            leftTextView.setBackgroundResource(R.drawable.setting_left_off)
            rightTextView.setBackgroundResource(R.drawable.setting_right_on)
            leftTextView.setText(R.string.full_time)
            rightTextView.setText(R.string.part_time)
            leftTextView.setTextColor(colorOff)
            rightTextView.setTextColor(colorOn)

        }
    }


    private fun showDynamicTwoColumnWheelDialogFragment() {
        salaryPicker.show()
        //fragment 之间activity和fragment之间传值
//        dynamicTwoColumnWheelDialogFragment.show(supportFragmentManager, "dynamic_two_column_wheel")
    }

//    override fun onValuesSelected(value1: String, value2: String) {
        // 处理选中的值的操作
        // 例如，可以更新UI显示选中的值
//        textView1.text = value1.toString()
//        textView2.text = value2.toString()
//    }

    fun onClick(view: View) {
        when (view) {
            binding.ivArrow,binding.textView10,binding.textView11 -> startActivity(Intent(this, CitySearchPageActivity::class.java))
            binding.ivArrow1,binding.textView12,binding.textView13 -> startActivity(Intent(this, ChannelSearchPageActivity::class.java))
            binding.ivArrow2,binding.textView14,binding.textView15 -> showDynamicTwoColumnWheelDialogFragment()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}