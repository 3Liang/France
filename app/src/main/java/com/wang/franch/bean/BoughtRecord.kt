package com.wang.franch.bean

/**
 * 采购记录bean类
 *
 * 采购信息 {
 * cooperativeSimpleName (string, optional): 合作商简称 ,
 * dateDeDepense (string, optional): 交易时间（法国时间） ,
 * invoice (string, optional): 发票pdf ,
 * montantTTC (string, optional): 价格 ,
 * status (integer, optional): 发票状态 1-未上传发票，2-待审核,3-发票审核通过，4-发票审核未通过
 * }
 * @Author Leiyu
 * @CreateDate 2021/3/13
 */
data class BoughtRecord(
    var dateDeDepense: String,
    var montantTTC: Float,
    var cooperativeSimpleName: String,
    var status: String,
    var invoice: String
)