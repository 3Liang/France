package com.wang.franch.adapter

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * DataBinding相关ViewHolder
 *
 * @Author Leiyu
 * @CreateDate 2021/3/13
 */
class BindingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var binding: ViewDataBinding = DataBindingUtil.bind(itemView)!!

    fun getBinding(): ViewDataBinding {
        return binding
    }
}