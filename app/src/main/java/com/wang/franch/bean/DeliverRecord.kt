package com.wang.franch.bean

/**
 * 发货记录bean类
 *
 * 发货记录 {
 * completeSendGoodsNoNumber (string, optional): 发票编号 ,
 * createTime (integer, optional): 发货时间 ,
 * customsClearanceFormPdf (string, optional): 出关单 pdf ,
 * id (integer, optional),
 * jobNumber (string, optional): 发货人员工编码 ,
 * orderNo (string, optional): 发货码 ,
 * reviewMemo (string, optional): 发票审核备注 ,
 * reviewStatus (string, optional): 出关单 的审核状态 PASS 审核通过 REJECT 审核拒绝 ,
 * reviewTime (integer, optional): 出关单 审核时间 ,
 * sellInvoicePdf (string, optional): 销售发票 pdf ,
 * sellTotalAmount (number, optional): 销售总金额 ,
 * totalAmount (number, optional): 总金额 ,
 * totalSellAmount (number, optional): 总的商品销售金额 ,
 * trackingNumber (string, optional): 快递单号 ,
 * uploadCustomsClearanceFormTime (integer, optional): 出关单 上传时间
 * }
 *
 * @Author Leiyu
 * @CreateDate 2021/3/13
 */
data class DeliverRecord(
    var createTime: Long,
    var reviewTime: Long,
    var uploadCustomsClearanceFormTime: Long,
    var id: Int,
    var jobNumber: String,
    var orderNo: String,
    var number: String,
    var reviewMemo: String,
    var sellTotalAmount: Float,
    var totalAmount: Float,
    var totalSellAmount: Float,
    var sellInvoicePdf: String,
    var reviewStatus: String,
    var customsClearanceFormPdf: String,
    var completeSendGoodsNoNumber: String,
    var trackingNumber: String
)