package com.wang.franch.bean

/**
 * 收款记录bean类
 *
 * dealTimeFrench (string, optional): 交易时间（法国时间） ,
 * memo (string, optional): 备注 ,
 * merchantOrderAmount (string, optional): 商户订单总额 ,
 * moneyStatus (string, optional): 到帐状态，1-已收款，2-外汇兑换中，3-消费中心转账中，4-法国银行转账中，5-卡额分配中，6-到账 ,
 * rechargeTypeName (string, optional): 对应Méthode
 *
 * @Author Leiyu
 * @CreateDate 2021/3/13
 */
data class ReceiveRecord(
    var dealTimeFrench: String,
    var memo: String,
    var merchantOrderAmount: Float,
    var moneyStatus: String,
    var rechargeTypeName: String
)