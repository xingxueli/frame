package com.example.myapplication.ui.home.login

import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.App
import com.example.myapplication.R
import com.example.myapplication.databinding.LoginActivityBinding


class LoginActivity : AppCompatActivity(),View.OnClickListener {

    private val tag : String  = this::class.java.simpleName

    private lateinit var binding: LoginActivityBinding

    private lateinit var mobileEditText: EditText
    private lateinit var textView: TextView
    private lateinit var nextButton: Button

    private lateinit var loginViewModel: LoginViewModel

    private var shouldAutoSplit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        mobileEditText = binding.loginMobile
        nextButton = binding.loginNext
        textView = binding.textView

        loginViewModel=
            ViewModelProvider(this)[LoginViewModel::class.java]

        loginViewModel.dataList.observe(this){
            if(it.isSuccess()){
                //跳转到otp页面
                val mobile = getPhoneNumber(mobileEditText.text)
                val intent = Intent(this, VerifyActivity::class.java)
                intent.putExtra("mobile", mobile)
                startActivity(intent)
            }else{
                Log.i(tag,it.message)
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT)
                    .show()
            }
        }

        // 获取数据
        intent.getIntExtra("apiResponseCode",2000).also {
            Log.i(tag,"$it")
            if (it == 10004) {
                val updateDialog = createUpdateDialog(this)
                updateDialog.show()
            }
        }

        initEditText()
        initClickListener()
    }

    private fun initEditText() {
        mobileEditText.isFocusable = true
        mobileEditText.addTextChangedListener(nameWatcher)
    }

    private fun initClickListener() {
        nextButton.setOnClickListener(this)
    }

    private val nameWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
//            Log.i(tag, "$s $start $before $count")
            shouldAutoSplit = count==1
        }

        override fun afterTextChanged(s: Editable) {
            // 判断是删除还是输入
            if (shouldAutoSplit){
                val it = s.toString().length
                if (it == 3 || it == 8) {
                    // 123 4567
                    s?.append(" ")
                }
            }
            if (s.toString().length == 12){
                nextButton.isEnabled = true
            }
        }
    }

    // 将123 3333 3333 变成 1233333333
    private fun getPhoneNumber(editable:Editable):String{
        // 创建一个新的对象 用于操作editable对象的内容
        SpannableStringBuilder(editable.toString()).also {
            it.delete(3,4)
            it.delete(7,8)
            return textView.text.toString() + it.toString()
        }
    }

    override fun onClick(v: View?) {
        if (v == null) {
            return
        }
        when (v) {
            nextButton -> {
                if (mobileEditText.text.isEmpty()) {
                    Toast.makeText(this, "name illegal! Please retry.", Toast.LENGTH_SHORT).show()
                    return
                }
                val mobile = getPhoneNumber(mobileEditText.text)
                loginViewModel.initData(mobile)
                Log.i(tag,"$mobile")
            }
        }
    }

    private fun createUpdateDialog(context: Context): Dialog {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_update) // 替换为自定义的布局文件

        val titleTextView = dialog.findViewById<TextView>(R.id.titleTextView)
        val messageTextView = dialog.findViewById<TextView>(R.id.messageTextView)
        val updateButton = dialog.findViewById<Button>(R.id.updateButton)

        titleTextView.text = "强制更新"
        messageTextView.text = "发现新版本，请立即更新以继续使用。"

        updateButton.setOnClickListener {
            // 执行更新操作
            // 这里可以跳转到应用商店或下载最新版本的 APK 文件等操作
            // 示例：跳转到应用商店
            val appPackageName = context.packageName
            try {
                val marketIntent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName"))
                marketIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(marketIntent)
            } catch (e: ActivityNotFoundException) {
                val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName"))
                webIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(webIntent)
            }

            dialog.dismiss()
        }

        return dialog
    }

}