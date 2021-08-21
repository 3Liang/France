package com.wang.franch.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * 接口请求实例提供
 *
 * @Author Leiyu
 * @CreateDate 2021/2/14
 */
object ApiServiceProvider {
    private val API: String = "http://8.209.78.60:8082"
    var apiService: ApiService

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(API)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)
    }
}