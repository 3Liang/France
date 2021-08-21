package com.wang.franch

import android.view.View
import androidx.appcompat.widget.Toolbar

/**
 * Toolbar的配置类，提供相关配置信息
 */
class ToolbarInfo {
    var backgroundColor = 0 //背景颜色
    var leftIconId = R.drawable.fr_left_arrow //导航按钮图标
    var centerText : String? = null //标题文字内容
    var centerTextColor = 0//标题文字颜色
    var rightText : String? = null //右边文字内容
    var rightTextColor = 0 //右边文字颜色
    var menuId  = 0//Toolbar 操作菜单
    var leftClickListener : View.OnClickListener? = null//导航按钮事件
    var rightTextClickListener : View.OnClickListener? = null//右边文字点击事件
    var onMenuItemClickListener : Toolbar.OnMenuItemClickListener? = null//操作菜单点击事件

    fun backgroundColor(backgroundColor: Int): ToolbarInfo {
        this.backgroundColor = backgroundColor
        return this
    }

    fun leftIconId(leftIconId: Int): ToolbarInfo {
        this.leftIconId = leftIconId
        return this
    }

    fun centerText(centerText: String?): ToolbarInfo {
        this.centerText = centerText
        return this
    }

    fun centerTextColor(centerTextColor: Int): ToolbarInfo {
        this.centerTextColor = centerTextColor
        return this
    }

    fun rightText(rightText: String?): ToolbarInfo {
        this.rightText = rightText
        return this
    }

    fun rightTextColor(rightTextColor: Int): ToolbarInfo {
        this.rightTextColor = rightTextColor
        return this
    }

    fun menuId(menuId: Int): ToolbarInfo {
        this.menuId = menuId
        return this
    }

    fun leftClickListener(leftClickListener: View.OnClickListener?): ToolbarInfo {
        this.leftClickListener = leftClickListener
        return this
    }

    fun rightTextClickListener(rightTextClickListener: View.OnClickListener?): ToolbarInfo {
        this.rightTextClickListener = rightTextClickListener
        return this
    }

    fun menuItemClickListener(onMenuItemClickListener: Toolbar.OnMenuItemClickListener?): ToolbarInfo {
        this.onMenuItemClickListener = onMenuItemClickListener
        return this
    }
}