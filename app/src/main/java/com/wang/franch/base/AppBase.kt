package com.wang.franch.base

import android.app.Application
import com.wang.franch.util.share_preference.SharePreferenceUtil

/**
 * 基类Application
 * @Author Leiyu
 * @CreateDate 2021/6/25
 */
class AppBase : Application() {

    override fun onCreate() {
        super.onCreate()
        SharePreferenceUtil.init(this)
    }
}