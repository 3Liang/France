package com.wang.franch.helper

import android.content.Context
import java.io.File

/**
 * 文件帮助类
 * @Author Leiyu
 * @CreateDate 2021/7/11
 */
object FileHelper {

    fun imageFile(context: Context): File {
        return File(
            context.getExternalFilesDir("img"),
            System.currentTimeMillis().toString() + ".jpg"
        )
    }

    fun pdfFile(context: Context): File {
        return File(
            context.getExternalFilesDir("pdf"),
            System.currentTimeMillis().toString() + ".pdf"
        )
    }
}