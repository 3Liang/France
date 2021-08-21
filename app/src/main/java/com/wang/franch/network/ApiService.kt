package com.wang.franch.network

import com.wang.franch.bean.*
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.*

/**
 * 网络请求接口定义类
 *
 * @Author Leiyu
 * @CreateDate 2021/2/6
 */
interface ApiService {
    @FormUrlEncoded
    @POST("user/login")
    fun login(
        @Field("jobNumber") jobNumber: String,
        @Field("password") password: String
    ): Observable<BaseResponse<User>>

    @FormUrlEncoded
    @POST("user/changePassword")
    fun changePass(
        @Field("appUserId") appUserId: String,
        @Field("oldPassword") oldPassword: String,
        @Field("newPassword") newPassword: String
    ): Observable<BaseResponse<String>>

    @GET("recharge/list")
    fun getReceivedBillList(
        @Query("appUserId") appUserId: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Observable<BasePageResponse<List<ReceiveRecord>>>

    @GET("consumer/list")
    fun getPurchaseList(
        @Query("appUserId") appUserId: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Observable<BasePageResponse<List<BoughtRecord>>>

    @GET("sendGoods/list")
    fun getDeliverList(
        @Query("appUserId") appUserId: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Observable<BasePageResponse<List<DeliverRecord>>>

    @GET("goods/listNoPage")
    fun getGoodsList(
        @Query("appUserId") appUserId: String
    ): Observable<BaseResponse<List<Goods>>>

    @FormUrlEncoded
    @POST("sendGoods/sendGoods")
    fun deliverGoods(
        @Field("appUserId") appUserId: String,
        @Field("goodsIds") goodsIds: String,
        @Field("totalAmount") totalAmount: Float
    ): Observable<BaseResponse<String>>

    @GET("consumer/gatherInfo")
    fun getAccountInfo(
        @Query("appUserId") appUserId: String
    ): Observable<BaseResponse<Account>>

    @GET("sendGoods/goodsList/{sendGoodsRecordId}")
    fun getFormInfo(
        @Path("sendGoodsRecordId") sendGoodsRecordId: Int
    ): Observable<BaseResponse<List<Goods>>>

    @Multipart
    @POST("cloudDisk/uploadFile")
    fun uploadReceipt(@Part params: List<MultipartBody.Part>): Observable<BaseResponse<String>>

    @Multipart
    @POST("sendGoods/uploadCustomsClearanceFormPdf")
    fun uploadForm(@Part params: List<MultipartBody.Part>): Observable<BaseResponse<String>>

    @GET("recharge/getRechargeQrCode")
    fun getChargeCode(
        @Query("appUserId") appUserId: String,
        @Query("totalFee") totalFee: String
    ): Observable<BaseResponse<Charge>>
}