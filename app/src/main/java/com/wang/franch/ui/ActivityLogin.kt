package com.wang.franch.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.wang.franch.R
import com.wang.franch.base.ActivityBase
import com.wang.franch.databinding.ActivityLoginBinding
import com.wang.franch.viewmodel.UserViewModel

/**
 * 登录页面
 *
 * @Author Leiyu
 * @CreateDate 2021/2/6
 */
class ActivityLogin : ActivityBase() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userViewModel = UserViewModel()
        userViewModel.loginResult.observe(this, Observer {
            startActivity(Intent(this, ActivityMain::class.java))
            finish()
        })
        userViewModel.message.observe(this, Observer {
            showSnackbar(binding.textAppName, it)
            binding.group.visibility = View.VISIBLE
            binding.textLoading.visibility = View.INVISIBLE
        })
    }

    /**
     * 登录点击处理
     */
    fun confirmLogin(view: View) {
        val username = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            showSnackbar(view, getString(R.string.empty_username_or_password))
            return
        }
        userViewModel.login(username, password)
        binding.group.visibility = View.GONE
        binding.textLoading.visibility = View.VISIBLE
    }
}