package com.wang.franch.bean

/**
 * 账户信息
 * 采购统计信息 {
 * balance (string, optional): 余额 ,
 * historyGather (string, optional): 历史充值汇总 ,
 * lastMonthGather (string, optional): 上月采购汇总 ,
 * thisMonthGather (string, optional): 本月采购汇总 ,
 * todayGather (string, optional): 今日采购汇总 ,
 * validBalance (string, optional): 余额
}
 * @Author Leiyu
 * @CreateDate 2021/7/10
 */
data class Account(
    var balance: Float,
    var historyGather: Float,
    var lastMonthGather: Float,
    var thisMonthGather: Float,
    var todayGather: Float,
    var validBalance: Float
)