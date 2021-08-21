package com.wang.franch.bean

/**
 * 商品实体类
 *
 * @Author Leiyu
 * @CreateDate 2021/7/4
 */
data class Goods(
    var id: Int,
    var name: String,
    var createTime: Long,
    var number: String,
    var customHouse: String,
    var price: Float,
    var purePrice: Float,
    var ratePrice: Float,
    var sellPrice: Float,
    var adminUserId: Int
)