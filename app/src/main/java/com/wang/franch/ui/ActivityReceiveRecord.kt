package com.wang.franch.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.wang.franch.BR
import com.wang.franch.R
import com.wang.franch.adapter.BindingViewHolder
import com.wang.franch.adapter.LoadmoreAdapter
import com.wang.franch.base.ActivityBase
import com.wang.franch.bean.ReceiveRecord
import com.wang.franch.databinding.ActivityReceiveRecordBinding
import com.wang.franch.viewmodel.BusinessViewModel

/**
 * 收款记录
 *
 * @Author Leiyu
 * @CreateDate 2021/3/14
 */
class ActivityReceiveRecord : ActivityBase(), LoadmoreAdapter.OnLoadMoreListener {

    private var page = 1
    private lateinit var viewModel: BusinessViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityReceiveRecordBinding.inflate(layoutInflater)
        binding.toolbarInfo = toolbarInfo()?.also {
            it.centerText = getString(R.string.receive_record)
        }
        binding.rvReceiveRecords.layoutManager = LinearLayoutManager(this)
        binding.rvReceiveRecords.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        val adapter =
            object : LoadmoreAdapter<ReceiveRecord>(R.layout.item_receive_record, ArrayList()) {
                override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
                    super.onBindViewHolder(holder, position)
                    holder.getBinding().setVariable(BR.item, getItem(position))
                    holder.getBinding().executePendingBindings()
                }
            }
        adapter.setLoadmoreListener(this)
        binding.rvReceiveRecords.adapter = adapter
        setContentView(binding.root)
        viewModel = BusinessViewModel()
        viewModel.receiveRecord.observe(this, Observer {
            if (page == 1) {
                adapter.updateData(it)
            } else {
                adapter.loadMore(it)
            }
        })
        viewModel.getReceivedBillList(page, 10)
    }

    override fun loadMore() {
        page++
        viewModel.getReceivedBillList(page, 10)
    }
}