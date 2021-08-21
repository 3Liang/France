package com.wang.franch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * DataBinding相关RecyclerView适配器类
 *
 * @Author Leiyu
 * @CreateDate 2021/3/13
 */
open class BaseBindingAdapter<T> : RecyclerView.Adapter<BindingViewHolder> {
    private var datas: List<T> = ArrayList()
    private var layoutId: Int

    constructor(layoutId: Int, datas: List<T>) {
        this.layoutId = layoutId
        this.datas = datas
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        return BindingViewHolder(
            LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
    }

    fun getItem(position: Int): T {
        return datas[position]
    }

    fun updateData(newDatas: List<T>) {
        this.datas = newDatas
        notifyDataSetChanged()
    }

    fun getData(): List<T> {
        return datas
    }

    fun addAllData(newDatas: List<T>) {
        (this.datas as MutableList).addAll(newDatas)
        notifyDataSetChanged()
    }
}