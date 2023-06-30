package com.example.myapplication.ui.home.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.App
import com.example.myapplication.databinding.RoleActivityBinding
import com.example.myapplication.ui.local.SPUtils
import com.example.myapplication.ui.network.model.Headers
import com.example.myapplication.ui.network.model.Role


class RoleActivity : AppCompatActivity() {

    private val tag : String  = this::class.java.simpleName

    private lateinit var binding: RoleActivityBinding

    private lateinit var recruiterImageView: ImageView
    private lateinit var candidateImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RoleActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        var role = SPUtils.getInt(App.instance, Headers.ROLE, 0)
        Log.i(tag,role.toString())
        if(role != 0){
            jumpToLoginActivity(role)
        }
        recruiterImageView = binding.recruiterImageView
        candidateImageView = binding.candidateImageView

        recruiterImageView.setOnClickListener {
            jumpToLoginActivity(Role.RECRUITER.id)
        }

        candidateImageView.setOnClickListener {
            jumpToLoginActivity(Role.CANDIDATE.id)
        }
    }

    private fun jumpToLoginActivity(role : Int) {
        SPUtils.putInt(App.instance, Headers.ROLE,role)
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

}