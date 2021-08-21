package com.wang.franch.ui

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.wang.franch.BR
import com.wang.franch.R
import com.wang.franch.adapter.BaseBindingAdapter
import com.wang.franch.adapter.BindingViewHolder
import com.wang.franch.adapter.LoadmoreAdapter
import com.wang.franch.base.ActivityBase
import com.wang.franch.bean.BoughtRecord
import com.wang.franch.databinding.ActivityBoughtRecordBinding
import com.wang.franch.viewmodel.BusinessViewModel

/**
 * 采购记录
 *
 * @Author Leiyu
 * @CreateDate 2021/3/13
 */
class ActivityBoughtRecord : ActivityBase(), LoadmoreAdapter.OnLoadMoreListener {

    private var page = 1
    private lateinit var viewModel: BusinessViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityBoughtRecordBinding.inflate(layoutInflater)
        binding.toolbarInfo = toolbarInfo()?.also {
            it.centerText = getString(R.string.bought_record)
        }
        binding.rvBoughtRecords.layoutManager = LinearLayoutManager(this)
        binding.rvBoughtRecords.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        val adapter = object :
            LoadmoreAdapter<BoughtRecord>(R.layout.item_bought_record_complex, ArrayList()) {
            override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
                super.onBindViewHolder(holder, position)
                holder.getBinding().setVariable(BR.activity, this@ActivityBoughtRecord)
                holder.getBinding().setVariable(BR.item, getItem(position))
                holder.getBinding().executePendingBindings()
            }
        }
        adapter.setLoadmoreListener(this)
        binding.rvBoughtRecords.adapter = adapter
        setContentView(binding.root)
        viewModel = BusinessViewModel()
        viewModel.purchaseRecord.observe(this, Observer {
            if (page == 1) {
                adapter.updateData(it)
            } else {
                adapter.loadMore(it)
            }
        })
        viewModel.getPurchaseList(page, 10)
    }

    fun viewReceipt(item: BoughtRecord) {
        val intent = Intent(this, ActivityWeb::class.java)
        intent.putExtra("title", item.cooperativeSimpleName)
        intent.putExtra("url", item.invoice)
        startActivity(intent)
    }

    override fun loadMore() {
        page++
        viewModel.getPurchaseList(page, 10)
    }
}