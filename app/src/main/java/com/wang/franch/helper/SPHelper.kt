package com.wang.franch.helper

import com.wang.franch.bean.User
import com.wang.franch.util.share_preference.SharePreferenceUtil
import com.wang.franch.util.share_preference.SharePreferenceUtil.getInt
import com.wang.franch.util.share_preference.SharePreferenceUtil.getString
import com.wang.franch.util.share_preference.SharePreferenceUtil.putValue
import com.wang.franch.util.share_preference.SharePreferenceUtil.removeValue

/**
 * 使用前确认[SharePreferenceUtil.init]方法已经在Application中初始化
 */
object SPHelper {
    private const val OPEN_COUNT = "app_open_count" //应用打开次数
    private const val TOKEN = "token" //登录令牌
    private const val USER = "user" //用户信息

    /**
     * 是否首次进入应用
     *
     * @return true 是，false 否
     */
    val isFirstOpen: Boolean
        get() = getInt(OPEN_COUNT) == 0

    /**
     * 增加应用打开次数
     */
    fun addOpenCount() {
        putValue(
            OPEN_COUNT,
            getInt(OPEN_COUNT) + 1
        )
    }

    /**
     * 保存登录后由后台返回的登录凭证
     *
     * @param token 令牌
     */
    fun saveToken(token: String?) {
        putValue(TOKEN, token)
    }

    /**
     * @return 返回登陆令牌
     */
    val token: String?
        get() = getString(TOKEN)

    /**
     * 移除本地token，在失效或用户登出的情况
     */
    fun clearToken() {
        removeValue(TOKEN)
    }

    /**
     * 保存用户信息
     *
     * @param user 用户信息
     */
    fun saveUser(user: User?) {
        putValue(USER, GsonHelper.gson().toJson(user))
    }

    /**
     * @return 返回用户信息
     */
    val user: User
        get() = GsonHelper.gson().fromJson(
            getString(USER),
            User::class.java
        )

    /**
     * 移除本地user，在登录令牌token失效或用户登出的情况
     */
    fun clearUser() {
        removeValue(USER)
    }
}