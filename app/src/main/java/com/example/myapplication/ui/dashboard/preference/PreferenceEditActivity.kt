package com.example.myapplication.ui.dashboard.preference

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityPreferenceEditBinding
import com.example.myapplication.ui.network.model.PreferenceModel
import com.google.gson.Gson


class PreferenceEditActivity : AppCompatActivity(){

    private val tag : String  = this::class.java.simpleName

    private lateinit var binding: ActivityPreferenceEditBinding
    private lateinit var preferenceEditViewModel: PreferenceEditViewModel
    private lateinit var salaryPicker: SalaryPicker
    private var cityId: Int = 0
    //Job type 1: full time 2: part time
    private var jobTypeId = 1
    private var channelIds :MutableList<Int> = mutableListOf()
    private var salaryType = 0
    private var salaryUnit = 0
    private var salaryMin = 0
    private var salaryMax = 0
    private var industryTags:MutableList<Int> = mutableListOf(-1)
    private val requestDataLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                result.data?.let { handlePageInteraction(it) }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreferenceEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferenceEditViewModel=
            ViewModelProvider(this)[PreferenceEditViewModel::class.java]

        handleButton()
        handlePageInteraction(this.intent)
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
                    first, second, _ ->
                binding.textView15.text = "$first - $second"
                var modelMap = salaryProvider.getModelMap()
                salaryMin = modelMap[first]!!
                salaryMax = modelMap[second]!!
            }
        }
    }

    private fun handlePageInteraction(intent: Intent) {

        var cityIdString = intent.getStringExtra("cityId")
        if(cityIdString != null){
            cityId = cityIdString.toInt()
            binding.textView11.text = intent.getStringExtra("city")
        }

        var salaryString = intent.getStringExtra("salary")
        if(salaryString != null){
            binding.textView15.text = intent.getStringExtra("salary")
        }
        val channelId = intent.getStringExtra("channelId")
        if(channelId != null){
            channelIds.add(channelId.toInt())
//        val dictItemName = intent.getStringExtra("dictItemName")
            binding.textView13.text = intent.getStringExtra("buildJobClassification")
        }

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
            jobTypeId = 2
        }
    }


    private fun showDynamicTwoColumnWheelDialogFragment() {
        salaryPicker.show()
    }

    fun onClick(view: View) {
        when (view) {
            binding.ivArrow,binding.textView10,binding.textView11 -> requestDataLauncher.launch(Intent(this, CitySearchPageActivity::class.java))
            binding.ivArrow1,binding.textView12,binding.textView13 -> requestDataLauncher.launch(Intent(this, ChannelSearchPageActivity::class.java))
            binding.ivArrow2,binding.textView14,binding.textView15 -> showDynamicTwoColumnWheelDialogFragment()
            binding.save -> savePreference()
            binding.delete -> deletePreference()
        }
    }

    private fun savePreference(){
        var preferenceModel = PreferenceModel()
        preferenceModel.cityId = cityId
        preferenceModel.jobTypeId = jobTypeId
        preferenceModel.channelIds = channelIds
        preferenceModel.salaryType = salaryType
        preferenceModel.salaryUnit = salaryUnit
        preferenceModel.salaryMin = salaryMin
        preferenceModel.salaryMax = salaryMax
        preferenceModel.industryTags = industryTags
        preferenceEditViewModel.savePreference(preferenceModel)

        preferenceEditViewModel.savePreferenceResponse.observe(this){
            Log.i(tag,Gson().toJson(it))
            startActivity(Intent(this,PreferenceListActivity::class.java))
        }
    }

    private fun deletePreference(){

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}