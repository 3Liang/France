package com.wang.franch.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.wang.franch.BR
import com.wang.franch.R
import com.wang.franch.adapter.BaseBindingAdapter
import com.wang.franch.adapter.BindingViewHolder
import com.wang.franch.adapter.LoadmoreAdapter
import com.wang.franch.base.ActivityBase
import com.wang.franch.bean.DeliverRecord
import com.wang.franch.databinding.ActivityDeliverRecordBinding
import com.wang.franch.viewmodel.BusinessViewModel

/**
 * 发货记录
 *
 * @Author Leiyu
 * @CreateDate 2021/3/13
 */
class ActivityDeliverRecord : ActivityBase(), LoadmoreAdapter.OnLoadMoreListener {

    private var page = 1
    private lateinit var viewModel: BusinessViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDeliverRecordBinding.inflate(layoutInflater)
        binding.toolbarInfo = toolbarInfo()?.also {
            it.centerText = getString(R.string.deliver_record)
        }
        binding.rvDeliverRecords.layoutManager = LinearLayoutManager(this)
        binding.rvDeliverRecords.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        val adapter = object :
            LoadmoreAdapter<DeliverRecord>(R.layout.item_deliver_record, ArrayList()) {
            override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
                super.onBindViewHolder(holder, position)
                holder.getBinding().setVariable(BR.item, getItem(position))
                holder.getBinding().setVariable(BR.activity, this@ActivityDeliverRecord)
                holder.getBinding().executePendingBindings()
            }
        }
        adapter.setLoadmoreListener(this)
        binding.rvDeliverRecords.adapter = adapter
        setContentView(binding.root)
        viewModel = BusinessViewModel()
        viewModel.deliverRecord.observe(this, Observer {
            if (page == 1) {
                adapter.updateData(it)
            } else {
                adapter.loadMore(it)
            }
        })
        viewModel.getDeliverList(1, 200)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            viewModel.getDeliverList(page, 10)
        }
    }

    fun viewForm(item: DeliverRecord) {
        val intent = Intent(this, ActivityFormInfo::class.java)
        intent.putExtra("sendGoodsRecordId", item.id)
        startActivity(intent)
    }

    fun viewPdf(item: DeliverRecord) {
        val intent = Intent(this, ActivityWeb::class.java)
        intent.putExtra("title", getString(R.string.view_form))
        intent.putExtra("url", item.customsClearanceFormPdf)
        startActivity(intent)
    }

    /**
     * 发货点击处理事件
     */
    fun deliver(view: View) {
        startActivityForResult(Intent(this, ActivityDeliverGoods::class.java), 11)
    }

    override fun loadMore() {
        page++
        viewModel.getDeliverList(page, 10)
    }

}