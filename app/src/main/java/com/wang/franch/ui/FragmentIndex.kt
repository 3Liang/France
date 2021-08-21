package com.wang.franch.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.wang.franch.BR
import com.wang.franch.bean.BoughtRecord
import com.wang.franch.R
import com.wang.franch.adapter.BaseBindingAdapter
import com.wang.franch.adapter.BindingViewHolder
import com.wang.franch.databinding.FragmentIndexBinding
import com.wang.franch.viewmodel.BusinessViewModel
import com.wang.franch.viewmodel.UserViewModel

/**
 * 首页
 *
 * @Author Leiyu
 * @CreateDate 2021/2/6
 */
class FragmentIndex : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentIndexBinding = FragmentIndexBinding.inflate(inflater, container, false)
        binding.rvBoughtRecords.layoutManager = LinearLayoutManager(context)
        val adapter = object :
            BaseBindingAdapter<BoughtRecord>(R.layout.item_bought_record_simple, ArrayList()) {
            override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
                super.onBindViewHolder(holder, position)
                holder.getBinding().setVariable(BR.item, getItem(position))
                holder.getBinding().executePendingBindings()
            }
        }
        binding.rvBoughtRecords.adapter = adapter
        val viewModel = BusinessViewModel()
        viewModel.purchaseRecord.observe(this, Observer {
            adapter.updateData(it)
        })
        viewModel.getPurchaseList(1, 10)
        val userViewModel = UserViewModel()
        userViewModel.account.observe(this, Observer {
            binding.account = it
        })
        userViewModel.getAccountInfo()
        binding.btnReceive.setOnClickListener {
            startActivity(
                Intent(context, ActivityAmountSetting::class.java)
            )
        }
        binding.tvReceiveRecord.setOnClickListener {
            startActivity(
                Intent(context, ActivityReceiveRecord::class.java)
            )
        }
        binding.btnDeliver.setOnClickListener {
            startActivity(
                Intent(context, ActivityDeliverRecord::class.java)
            )
        }
        binding.btnScan.setOnClickListener {
            startActivity(
                Intent(context, ActivityImageView::class.java)
            )
        }
        binding.tvMore.setOnClickListener {
            startActivity(
                Intent(context, ActivityBoughtRecord::class.java)
            )
        }
        return binding.root
    }
}