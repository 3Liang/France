package com.wang.franch.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wang.franch.databinding.FragmentMeBinding

/**
 * 我的
 *
 * @Author Leiyu
 * @CreateDate 2021/2/6
 */
class FragmentMe : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentMeBinding = FragmentMeBinding.inflate(inflater, container, false)
        binding.fragment = this
        return binding.root
    }

    /**
     * 修改密码
     */
    fun changePass() {
        startActivity(Intent(context, ActivityChangePass().javaClass))
    }

    /**
     * 退出登录
     */
    fun logout() {
        activity!!.finish()
        startActivity(Intent(context, ActivityLogin().javaClass))
    }
}