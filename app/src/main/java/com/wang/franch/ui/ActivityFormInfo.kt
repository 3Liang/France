package com.wang.franch.ui

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.wang.franch.BR
import com.wang.franch.R
import com.wang.franch.adapter.BaseBindingAdapter
import com.wang.franch.adapter.BindingViewHolder
import com.wang.franch.base.ActivityBase
import com.wang.franch.bean.Goods
import com.wang.franch.databinding.ActivityFormInfoBinding
import com.wang.franch.helper.StringHelper
import com.wang.franch.viewmodel.BusinessViewModel

/**
 * 出关单信息
 * @Author Leiyu
 * @CreateDate 2021/7/11
 */
class ActivityFormInfo : ActivityBase() {

    private lateinit var viewModel: BusinessViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFormInfoBinding.inflate(layoutInflater)
        binding.toolbarInfo = toolbarInfo()?.also {
            it.centerText = getString(R.string.form_info)
        }
        binding.rvList.layoutManager = LinearLayoutManager(this)
        val adapter = object :
            BaseBindingAdapter<Goods>(R.layout.item_form_info, ArrayList()) {
            override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
                super.onBindViewHolder(holder, position)
                holder.getBinding().setVariable(BR.item, getItem(position))
                holder.getBinding().executePendingBindings()
            }
        }
        binding.rvList.adapter = adapter
        setContentView(binding.root)
        viewModel = BusinessViewModel()
        viewModel.goods.observe(this, Observer {
            adapter.updateData(it)
            binding.viewLine.visibility = View.VISIBLE
            var amount = 0f
            for (item in it) {
                amount += item.price
            }
            binding.tvTotalAmount.text = StringHelper.totalAmount(this, amount)
        })
        viewModel.getFormInfo(intent.getIntExtra("sendGoodsRecordId", 0))
    }
}