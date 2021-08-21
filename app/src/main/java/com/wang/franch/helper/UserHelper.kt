package com.wang.franch.helper

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.wang.franch.bean.User
import com.wang.franch.helper.SPHelper.clearToken
import com.wang.franch.helper.SPHelper.clearUser
import com.wang.franch.helper.SPHelper.saveUser

/**
 * 用户相关帮助类
 */
object UserHelper {
    private val user: MutableLiveData<User> = MutableLiveData() //该登录用户信息
    fun getUser(): User {
        if (user.value == null) {
            user.value = SPHelper.user
        }
        return user.value!!
    }

    /**
     * 监听用户信息变化
     *
     * @param owner    在此声明周期内进行监听
     * @param observer 监听者
     */
    fun observe(
        owner: LifecycleOwner?,
        observer: Observer<User?>?
    ) {
        user.observe(owner!!, observer!!)
    }

    /**
     * 用户是否已经登录
     *
     * @return 登录为true，未登录为false
     */
    val isLogin: Boolean
        get() {
            user.value = SPHelper.user
            return user.value != null
        }

    /**
     * 用户登录后的相关操作
     *
     * @param user 登录的用户
     */
    fun login(user: User?) {
        UserHelper.user.value = user
        saveUser(user)
    }

    /**
     * 用户登出的相关操作
     */
    fun logout() {
        user.value = null
        clearToken()
        clearUser()
    }

    /**
     * 更新用户信息
     *
     * @param user 新用户信息
     */
    fun updateUser(user: User?) {
        UserHelper.user.value = user
        saveUser(user)
    }
}