package com.wang.franch.ui

import android.app.Activity
import android.os.Bundle
import android.widget.CompoundButton
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.wang.franch.BR
import com.wang.franch.R
import com.wang.franch.adapter.BaseBindingAdapter
import com.wang.franch.adapter.BindingViewHolder
import com.wang.franch.base.ActivityBase
import com.wang.franch.bean.Goods
import com.wang.franch.databinding.ActivityDeliverGoodsBinding
import com.wang.franch.viewmodel.BusinessViewModel

/**
 * 我要发货
 *
 * @Author Leiyu
 * @CreateDate 2021/7/4
 */
class ActivityDeliverGoods : ActivityBase(), CompoundButton.OnCheckedChangeListener {

    private lateinit var adapter: BaseBindingAdapter<Goods>
    private lateinit var viewModel: BusinessViewModel
    private val selectedGoods = ArrayList<Goods>()
    private lateinit var binding: ActivityDeliverGoodsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeliverGoodsBinding.inflate(layoutInflater)
        binding.toolbarInfo = toolbarInfo()?.also {
            it.centerText = getString(R.string.i_want_deliver)
        }
        binding.cbSelectAll.setOnCheckedChangeListener(this)
        binding.tvSubmit.setOnClickListener {
            deliverGoods()
        }
        binding.totalAmount = 0f
        binding.rvGoodsList.layoutManager = LinearLayoutManager(this)
        binding.rvGoodsList.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        adapter = object : BaseBindingAdapter<Goods>(R.layout.item_goods, ArrayList()) {
            override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
                super.onBindViewHolder(holder, position)
                holder.getBinding().setVariable(BR.activity, this@ActivityDeliverGoods)
                holder.getBinding().setVariable(BR.item, getItem(position))
                holder.getBinding().setVariable(BR.position, position)
                holder.getBinding()
                    .setVariable(BR.selected, selectedGoods.contains(getItem(position)))
                holder.getBinding().executePendingBindings()
            }
        }
        binding.rvGoodsList.adapter = adapter
        setContentView(binding.root)
        viewModel = BusinessViewModel()
        viewModel.goods.observe(this, Observer {
            adapter.updateData(it)
        })
        viewModel.getGoodsList()
        viewModel.deliverGoods.observe(this, Observer {
            if (it) {
                showSnackbar(binding.viewLine, getString(R.string.submit_successfully))
                setResult(Activity.RESULT_OK)
                finish()
            } else {
                showSnackbar(binding.viewLine, getString(R.string.submit_unsuccessfully))
            }
        })
    }

    /**
     * 列表项点击事件处理
     */
    fun handleItemClick(item: Goods, position: Int) {
        if (selectedGoods.contains(item)) {
            selectedGoods.remove(item)
        } else {
            selectedGoods.add(item)
        }
        adapter.notifyItemChanged(position)
        refreshTotalAmount()
    }

    /**
     * 全选状态变更处理
     */
    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        selectedGoods.clear()
        if (isChecked) {
            selectedGoods.addAll(adapter.getData())
        }
        adapter.notifyDataSetChanged()
        refreshTotalAmount()
    }

    /**
     * 刷新总金额
     */
    private fun refreshTotalAmount() {
        var amount = 0f
        for (it in selectedGoods) {
            amount += it.price
        }
        binding.totalAmount = amount
    }

    /**
     * 发货
     */
    private fun deliverGoods() {
        if (selectedGoods.size <= 0) {
            showSnackbar(binding.viewLine, getString(R.string.please_select_at_least_one_goods))
            return
        }
        viewModel.deliverGoods(selectedGoods)
    }
}