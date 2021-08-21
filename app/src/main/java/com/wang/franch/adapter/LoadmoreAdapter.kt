package com.wang.franch.adapter

import java.util.*

/**
 * 加载更多adapter
 *
 * @Author Leiyu
 * @CreateDate 2021/7/18
 */
open class LoadmoreAdapter<T>(layoutId: Int, datas: MutableList<T>) :
    BaseBindingAdapter<T>(layoutId, datas) {

    private var listener: OnLoadMoreListener? = null
    private var loading = false

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        if (listener != null && position >= getData().size - 3) {
            loading = true
            listener!!.loadMore()
        }
    }

    /**
     * 设置加载更多监听
     */
    fun setLoadmoreListener(listener: OnLoadMoreListener) {
        this.listener = listener
    }

    fun loadMore(newAddData: List<T>) {
        if (newAddData.isNotEmpty()) {
            addAllData(newAddData)
            loading = false
        }
    }

    /**
     * 加载更多接口
     */
    interface OnLoadMoreListener {
        /**
         * 加载更多回调方法
         */
        fun loadMore()
    }
}