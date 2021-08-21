package com.wang.franch.network

/**
 * 网络请求结果基类
 *
 * @Author Leiyu
 * @CreateDate 2021/6/20
 */
data class BasePageResponse<T>(
    val code: Int,
    val message: String,
    val count: Int,
    val pageNumber: Int,
    val datas: T
) {
    fun isSuccessFul(): Boolean {
        return code == 200
    }
}