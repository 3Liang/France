package com.wang.franch.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wang.franch.Repository
import com.wang.franch.bean.*
import com.wang.franch.helper.UserHelper
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

/**
 * Business相关操作类
 *
 * @Author Leiyu
 * @CreateDate 2021/2/6
 */
class BusinessViewModel : ViewModel() {

    val receiveRecord: MutableLiveData<List<ReceiveRecord>> = MutableLiveData()
    val purchaseRecord: MutableLiveData<List<BoughtRecord>> = MutableLiveData()
    val deliverRecord: MutableLiveData<List<DeliverRecord>> = MutableLiveData()
    val goods: MutableLiveData<List<Goods>> = MutableLiveData()
    val deliverGoods: MutableLiveData<Boolean> = MutableLiveData()
    val uploadResult: MutableLiveData<Boolean> = MutableLiveData()
    val charge: MutableLiveData<Charge> = MutableLiveData()

    /**
     * 获取收款记录列表
     */
    @SuppressLint("CheckResult")
    fun getReceivedBillList(page: Int, limit: Int) {
        Repository.getReceivedBillList(page, limit)
            .subscribe { result ->
                if (result.isSuccessFul()) {
                    receiveRecord.value = result.datas
                }
            }
    }

    /**
     * 获取采购记录列表
     */
    @SuppressLint("CheckResult")
    fun getPurchaseList(page: Int, limit: Int) {
        Repository.getPurchaseList(page, limit)
            .subscribe { result ->
                if (result.isSuccessFul()) {
                    purchaseRecord.value = result.datas
                }
            }
    }

    /**
     * 获取发货记录列表
     */
    @SuppressLint("CheckResult")
    fun getDeliverList(page: Int, limit: Int) {
        Repository.getDeliverList(page, limit)
            .subscribe { result ->
                if (result.isSuccessFul()) {
                    deliverRecord.value = result.datas
                }
            }
    }

    /**
     * 获取采购记录列表
     */
    @SuppressLint("CheckResult")
    fun getGoodsList() {
        Repository.getGoodsList()
            .subscribe { result ->
                if (result.isSuccessFul()) {
                    goods.value = result.data
                }
            }
    }

    /**
     * 发货
     */
    @SuppressLint("CheckResult")
    fun deliverGoods(selectedGoods: List<Goods>) {
        var totalAmount = 0f
        val goodsIds = StringBuilder()
        for (item in selectedGoods) {
            totalAmount += item.price
            goodsIds.append(item.id).append(",")
        }
        goodsIds.deleteCharAt(goodsIds.length - 1)
        Repository.deliverGoods(goodsIds.toString(), totalAmount)
            .subscribe { result ->
                deliverGoods.value = result.isSuccessFul()
            }
    }

    /**
     * 获取发货商品列表
     */
    @SuppressLint("CheckResult")
    fun getFormInfo(sendGoodsRecordId: Int) {
        Repository.getFormInfo(sendGoodsRecordId)
            .subscribe { result ->
                goods.value = result.data
            }
    }

    /**
     * 上传发票
     */
    @SuppressLint("CheckResult")
    fun uploadReceipt(filepath: String) {
        val file = File(filepath)
        val builder: MultipartBody.Builder = MultipartBody.Builder()
            .addFormDataPart("appUserId", UserHelper.getUser().appUserId)
            .addFormDataPart(
                "file", file.name, RequestBody.create(
                    MediaType.parse("application/pdf"),
                    file
                )
            )
        Repository.uploadReceipt(builder.build().parts())
            .subscribe { result ->
                uploadResult.value = result.isSuccessFul()
            }
    }

    /**
     * 上传出关单
     */
    @SuppressLint("CheckResult")
    fun uploadForm(deliverId: String, trackingNumber: String, filepath: String) {
        val file = File(filepath)
        val builder: MultipartBody.Builder = MultipartBody.Builder()
            .addFormDataPart("appUserId", UserHelper.getUser().appUserId)
            .addFormDataPart("id", deliverId)
            .addFormDataPart("trackingNumber", trackingNumber)
            .addFormDataPart(
                "file", file.name, RequestBody.create(
                    MediaType.parse("application/pdf"),
                    file
                )
            )
        Repository.uploadReceipt(builder.build().parts())
            .subscribe { result ->
            }
    }

    /**
     * 获取发货商品列表
     */
    @SuppressLint("CheckResult")
    fun getChargeCode(totalAmount: String) {
        Repository.getChargeCode(totalAmount)
            .subscribe { result ->
                charge.value = result.data
            }
    }
}