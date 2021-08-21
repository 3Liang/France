package com.wang.franch.widget

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

/**
 * 跑马灯效果TextView
 *
 * @Author Leiyu
 * @CreateDate 2021/3/13
 */
class MarqueeTextView : AppCompatTextView {
    constructor(context: Context) : super(context) {
        initAttributes()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttributes()
    }

    constructor(context: Context, attrs: AttributeSet?, style: Int) : super(context, attrs, style) {
        initAttributes()
    }

    private fun initAttributes() {
        isSingleLine = true
        marqueeRepeatLimit = -1
        ellipsize = TextUtils.TruncateAt.MARQUEE
    }

    override fun isFocused(): Boolean {
        return true
    }
}