package com.wang.franch

import com.wang.franch.bean.*
import com.wang.franch.helper.UserHelper
import com.wang.franch.network.ApiServiceProvider
import com.wang.franch.network.BasePageResponse
import com.wang.franch.network.BaseResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody

/**
 *负责数据从本地或网络的获取
 *
 * @Author Leiyu
 * @CreateDate 2021/6/20
 */
object Repository {
    /**
     * 登录
     */
    fun login(jobNumber: String, password: String): Observable<BaseResponse<User>> {
        return ApiServiceProvider.apiService.login(jobNumber, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     * 修改密码
     */
    fun changePass(oldPassword: String, newPassword: String): Observable<BaseResponse<String>> {
        return ApiServiceProvider.apiService.changePass(
            UserHelper.getUser().appUserId,
            oldPassword,
            newPassword
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     * 获取收款记录列表
     */
    fun getReceivedBillList(
        page: Int,
        limit: Int
    ): Observable<BasePageResponse<List<ReceiveRecord>>> {
        return ApiServiceProvider.apiService.getReceivedBillList(
            UserHelper.getUser().appUserId,
            page,
            limit
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     * 获取采购记录列表
     */
    fun getPurchaseList(page: Int, limit: Int): Observable<BasePageResponse<List<BoughtRecord>>> {
        return ApiServiceProvider.apiService.getPurchaseList(
            UserHelper.getUser().appUserId,
            page,
            limit
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     * 获取发货记录列表
     */
    fun getDeliverList(page: Int, limit: Int): Observable<BasePageResponse<List<DeliverRecord>>> {
        return ApiServiceProvider.apiService.getDeliverList(
            UserHelper.getUser().appUserId,
            page,
            limit
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     * 获取用户名下商品列表
     */
    fun getGoodsList(): Observable<BaseResponse<List<Goods>>> {
        return ApiServiceProvider.apiService.getGoodsList(UserHelper.getUser().appUserId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     * 发货
     */
    fun deliverGoods(goodsIds: String, totalAmount: Float): Observable<BaseResponse<String>> {
        return ApiServiceProvider.apiService.deliverGoods(
            UserHelper.getUser().appUserId,
            goodsIds,
            totalAmount
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     * 获取用户账户信息
     */
    fun getAccountInfo(): Observable<BaseResponse<Account>> {
        return ApiServiceProvider.apiService.getAccountInfo(UserHelper.getUser().appUserId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     * 获取发货商品列表
     */
    fun getFormInfo(sendGoodsRecordId: Int): Observable<BaseResponse<List<Goods>>> {
        return ApiServiceProvider.apiService.getFormInfo(sendGoodsRecordId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     * 上传发票
     */
    fun uploadReceipt(params: List<MultipartBody.Part>): Observable<BaseResponse<String>> {
        return ApiServiceProvider.apiService.uploadReceipt(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     * 上传出关单
     */
    fun uploadForm(params: List<MultipartBody.Part>): Observable<BaseResponse<String>> {
        return ApiServiceProvider.apiService.uploadForm(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     * 获取收款二维码
     */
    fun getChargeCode(totalAmount: String): Observable<BaseResponse<Charge>> {
        return ApiServiceProvider.apiService.getChargeCode(
            UserHelper.getUser().appUserId,
            totalAmount
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}