package com.wang.franch.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.wang.franch.R
import com.wang.franch.base.ActivityBase
import com.wang.franch.databinding.ActivityChangePassBinding
import com.wang.franch.viewmodel.UserViewModel

/**
 * 修改密码
 *
 * @Author Leiyu
 * @CreateDate 2021/2/14
 */
class ActivityChangePass : ActivityBase() {

    private lateinit var binding: ActivityChangePassBinding
    private val userViewModel: UserViewModel = UserViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangePassBinding.inflate(layoutInflater)
        binding.toolbarInfo = toolbarInfo()!!.centerText(getString(R.string.change_password))
        setContentView(binding.root)
        userViewModel.changePassResult.observe(this, Observer {
            showSnackbar(binding.etConfirmPass, it)
            finish()
        })
    }

    fun changePass(view: View) {
        val oldPassword = binding.etCurrentPass.text.toString()
        val newPassword = binding.etNewPass.text.toString()
        val confirmPassword = binding.etConfirmPass.text.toString()
        if (newPassword != confirmPassword) {
            showSnackbar(view, getString(R.string.password_not_matched))
            return
        }
        userViewModel.changePass(oldPassword, newPassword)
    }
}