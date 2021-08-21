package com.wang.franch.base

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.wang.franch.R
import com.wang.franch.ToolbarInfo
import com.wang.franch.ui.ActivityPermission

/**
 * Activity基类
 *
 * @Author Leiyu
 * @CreateDate 2021/2/6
 */
open class ActivityBase : ActivityPermission() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        statusBarColor(getColor(R.color.white), true)
    }

    /**
     * 生成标题栏默认设置
     */
    protected open fun toolbarInfo(): ToolbarInfo? {
        return ToolbarInfo()
            .backgroundColor(getColor(R.color.white))
            .centerTextColor(getColor(R.color.color_262626))
            .leftClickListener(View.OnClickListener { view: View? ->
                onTitleLeftClick(view)
            })
            .rightTextColor(getColor(R.color.color_262626))
    }

    /**
     * 标题栏后退按钮的处理
     *
     * @param view 发生点击的View
     */
    protected open fun onTitleLeftClick(view: View?) {
        onBackPressed()
    }

    /**
     * 设置状态栏颜色
     *
     * @param statusBarColor 状态栏颜色
     * @param lightMode 是否为浅色
     */
    protected fun statusBarColor(statusBarColor: Int, lightMode: Boolean) {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = statusBarColor
        if (lightMode && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        }
    }

    /**
     * 显示Snackbar
     */
    protected fun showSnackbar(view: View, tip: String) {
        Snackbar.make(view, tip, Snackbar.LENGTH_SHORT).show()
    }
}