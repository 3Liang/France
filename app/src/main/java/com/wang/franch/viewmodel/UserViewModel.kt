package com.wang.franch.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wang.franch.Repository
import com.wang.franch.bean.Account
import com.wang.franch.bean.User
import com.wang.franch.helper.Md5Helper
import com.wang.franch.helper.UserHelper

/**
 * 用户相关操作类
 *
 * @Author Leiyu
 * @CreateDate 2021/2/6
 */
class UserViewModel : ViewModel() {
    val loginResult: MutableLiveData<User> = MutableLiveData()
    val changePassResult: MutableLiveData<String> = MutableLiveData()
    val message: MutableLiveData<String> = MutableLiveData()
    val account: MutableLiveData<Account> = MutableLiveData()

    /**
     * 登录
     */
    @SuppressLint("CheckResult")
    fun login(username: String, password: String) {
        Repository.login(username, Md5Helper.MD5(password))
            .subscribe { result ->
                if (result.isSuccessFul()) {
                    loginResult.value = result.data
                    UserHelper.login(result.data)
                } else {
                    message.value = result.message
                }
            }
    }

    /**
     * 修改密码
     */
    @SuppressLint("CheckResult")
    fun changePass(oldPassword: String, newPassword: String) {
        Repository.changePass(Md5Helper.MD5(oldPassword), Md5Helper.MD5(newPassword))
            .subscribe { result ->
                if (result.isSuccessFul()) {
                    changePassResult.value = result.data
                }
            }
    }

    /**
     * 获取用户账户信息
     */
    @SuppressLint("CheckResult")
    fun getAccountInfo() {
        Repository.getAccountInfo()
            .subscribe {
                if (it.isSuccessFul()) {
                    account.value = it.data
                }
            }
    }
}