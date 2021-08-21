package com.wang.franch.bean

/**
 * 收款二维码
 * @Author Leiyu
 * @CreateDate 2021/7/13
 */
data class Charge(
    var orderNo: String,
    var nom: String,
    var prenom: String,
    var codeImgUrl: String
)