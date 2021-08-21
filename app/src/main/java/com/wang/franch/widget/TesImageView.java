package com.wang.franch.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;

/**
 * description
 *
 * @Author Leiyu
 * @CreateDate 2021/7/23
 */
public class TesImageView extends ImageView {
    public TesImageView(Context context) {
        super(context);
    }

    public TesImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TesImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TesImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onDrawForeground(Canvas canvas) {

    }
}
