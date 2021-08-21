package com.wang.franch.helper

import com.google.gson.Gson

/**
 * 统一Gson的实例获取方式，方便以后添加参数
 */
object GsonHelper {
    /**
     * @return 获取Gson实例
     */
    fun gson(): Gson {
        return Gson()
    }
}