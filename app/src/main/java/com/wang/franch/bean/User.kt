package com.wang.franch.bean

/**
 * 用户实体类
 *
 * @Author Leiyu
 * @CreateDate 2021/6/20
 */
data class User(
    var appUserId: String,
    var headImg: String,
    var jobNumber: String,
    var phone: String,
    var nom: String,
    var prenom: String,
    var email: String,
    var integral: String,
    var roleId: String,
    var roleName: String
)