package com.example.myapplication.ui.home.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.App
import com.example.myapplication.MainActivity
import com.example.myapplication.databinding.LoginActivityBinding
import com.example.myapplication.databinding.VerifyActivityBinding
import com.example.myapplication.ui.home.HomeFragment
import com.example.myapplication.ui.local.SPUtils
import com.example.myapplication.ui.network.model.Headers


class VerifyActivity : AppCompatActivity() {

    private val tag : String  = this::class.java.simpleName

    private lateinit var binding: VerifyActivityBinding
    private lateinit var otpTextView: EditText
    private lateinit var verifyViewModel: VerifyViewModel
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var textView1 :TextView
    private lateinit var textView2 :TextView
    private lateinit var textView3 :TextView
    private lateinit var textView4 :TextView
    private lateinit var textView5 :TextView
    private lateinit var textView6 :TextView
    private lateinit var textView9 :TextView

    private lateinit var mobile :String
    // 保存所有显示验证码的textView
    private val verifyViews:Array<TextView> by lazy {
        arrayOf(textView1,textView2,textView3,textView4,textView5,textView6)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = VerifyActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        textView1 = binding.textView1
        textView2 = binding.textView2
        textView3 = binding.textView3
        textView4 = binding.textView4
        textView5 = binding.textView5
        textView6 = binding.textView6
        otpTextView = binding.editTextNumber

        verifyViewModel = ViewModelProvider(this)[VerifyViewModel::class.java]
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        // 获取数据
        intent.getStringExtra("mobile").also {
            // 显示号码
            if (it != null) {
                mobile = it
            }
        }

        textView9 = binding.textView9
        textView9.setOnClickListener {
            loginViewModel.initData(mobile)
        }

        verifyViewModel.dataList.observe(this){
            if(it != null){
                //更新token、role等信息
                SPUtils.putString(App.instance, Headers.ID_TOKEN, it.idToken)

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }

        initEditText()
    }

    private fun initEditText() {
        otpTextView.isFocusable = true
        otpTextView.addTextChangedListener(nameWatcher)
    }

    private val nameWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            // 将输入的数据拆分到对应的TextView
            for ((i:Int,item:Char)in s?.withIndex()!!)
            //获取i对应的TextView
                verifyViews[i].text=item.toString()
            // 如果位数小于6个 后面的文本框不显示任何内容
            for (i:Int in s.length..5){
                verifyViews[i].text=""
            }
        }
        override fun afterTextChanged(s: Editable) {
            if (s.toString().length == 6){
                verifyViewModel.initData(mobile,s.toString())
                otpTextView.setText("")
            }
        }
    }

}