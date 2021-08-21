package com.wang.franch.helper

import java.text.SimpleDateFormat
import java.util.*

/**
 * 时间帮助类
 * @Author Leiyu
 * @CreateDate 2021/7/10
 */
object TimeHelper {
    const val COMMON_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss"

    /**
     * 格式化时间戳
     */
    fun format(format: String, timeMillis: Long): String {
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = timeMillis
        return SimpleDateFormat(format, Locale.getDefault()).format(calendar.time)
    }
}