package com.wang.franch.ui

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.wang.franch.R
import com.wang.franch.base.ActivityBase
import com.wang.franch.databinding.ActivityAmountSettingBinding
import com.wang.franch.viewmodel.BusinessViewModel

/**
 * 金额设置页面
 *
 * @Author Leiyu
 * @CreateDate 2021/7/12
 */
class ActivityAmountSetting : ActivityBase() {

    private lateinit var binding: ActivityAmountSettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAmountSettingBinding.inflate(layoutInflater)
        binding.toolbarInfo = toolbarInfo()?.also {
            it.centerText = getString(R.string.set_amount)
        }
        setContentView(binding.root)
        binding.etAmount.requestFocus()
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
        val viewModel = BusinessViewModel()
        viewModel.charge.observe(this, Observer {
            binding.coverView.visibility = View.VISIBLE
            binding.ivCode.visibility = View.VISIBLE
            Glide.with(binding.ivCode).load(it.codeImgUrl).into(binding.ivCode)
        })
        binding.tvConfirm.setOnClickListener {
            hideSoftInputMethod()
            viewModel.getChargeCode(binding.etAmount.text.toString())
        }
    }

    private fun hideSoftInputMethod() {
        val imManager: InputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imManager.hideSoftInputFromWindow(
            binding.etAmount.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }
}