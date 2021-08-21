package com.wang.franch.helper

import android.content.Context
import android.text.TextUtils
import com.wang.franch.R

/**
 * 字符串资源帮助类
 * @Author Leiyu
 * @CreateDate 2021/7/10
 */
object StringHelper {

    /**
     * 总金额
     */
    fun totalAmount(context: Context, amount: Float): String {
        return context.getString(R.string.total_amount_x, amount)
    }

    /**
     * 总余额
     */
    fun totalAvailableAmount(context: Context, availableAmount: Float): String {
        return context.getString(R.string.total_available_amount_x, availableAmount)
    }

    /**
     * 标准金额显示样式
     */
    fun standardAmount(context: Context, amount: Float): String {
        return context.getString(R.string.standard_amount, amount)
    }

    /**
     * 订单状态
     */
    fun orderStatus(context: Context, status: String): String {
        return when (status) {
            "1" -> context.getString(R.string.receipt_not_uploaded)
            "2" -> context.getString(R.string.verifying_receipt)
            "3" -> context.getString(R.string.receipt_passed)
            else -> context.getString(R.string.receipt_unpassed)
        }
    }

    /**
     * 收款状态
     */
    fun moneyStatus(context: Context, status: String): String {
        return when (status) {
            "1" -> context.getString(R.string.received_already)
            "2" -> context.getString(R.string.foreign_exchanging)
            "3" -> context.getString(R.string.center_transferring)
            "4" -> context.getString(R.string.bank_transferring)
            "5" -> context.getString(R.string.card_delivering)
            else -> context.getString(R.string.in_account)
        }
    }

    /**
     * 发货状态
     */
    fun deliverStatus(context: Context, status: String?): String {
        if (TextUtils.isEmpty(status)) {
            return context.getString(R.string.verification_unpassed)
        }
        return when (status) {
            "PASS " -> context.getString(R.string.verification_passed)
            else -> context.getString(R.string.verification_unpassed)
        }
    }
}