package com.wang.franch;

import androidx.appcompat.widget.Toolbar;
import androidx.databinding.BindingAdapter;

/**
 * DataBinding所需类
 */
public class BindingAdapterUtility {

    @BindingAdapter("toolbarInfo")
    public static void toolbar(Toolbar toolbar, ToolbarInfo info) {
        if (info.getLeftIconId() != 0) {
            toolbar.setNavigationIcon(info.getLeftIconId());
            toolbar.setNavigationOnClickListener(info.getLeftClickListener());
        } else {
            toolbar.setNavigationIcon(null);
        }
        if (info.getMenuId() != 0)
            toolbar.inflateMenu(info.getMenuId());
        toolbar.setOnMenuItemClickListener(info.getOnMenuItemClickListener());
    }
}
