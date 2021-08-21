package com.wang.franch.util.share_preference

import android.content.Context
import android.content.SharedPreferences

/**
 * 可以抽取出来使用的SharePreferenceUtil，与应用逻辑无耦合
 */
object SharePreferenceUtil {
    // 文件名
    private const val FILE_NAME = "Important"

    //上下文Context
    private var context: Context? = null

    /**
     * 使用Application的上下文Context完成赋值
     *
     * @param context 上下文
     */
    fun init(context: Context?) {
        SharePreferenceUtil.context = context
    }

    /**
     * 返回对应的SharePreferences对象
     *
     * @return [SharedPreferences]
     */
    private fun sharedPreferences(): SharedPreferences {
        return context!!.getSharedPreferences(
            FILE_NAME,
            Context.MODE_PRIVATE
        )
    }

    /**
     * 统一的入值操作
     *
     * @param key   键名
     * @param value 对应的值
     */
    fun putValue(key: String?, value: Any?) {
        val editor =
            sharedPreferences().edit()
        if (value is String) {
            editor.putString(key, value as String?)
        } else if (value is Int) {
            editor.putInt(key, (value as Int?)!!)
        } else if (value is Float) {
            editor.putFloat(key, (value as Float?)!!)
        } else if (value is Long) {
            editor.putLong(key, (value as Long?)!!)
        } else if (value is Boolean) {
            editor.putBoolean(key, (value as Boolean?)!!)
        }
        editor.commit()
    }

    /**
     * 获取本地字符串，若不存在则返回空字符串
     *
     * @param key 键值
     * @return 本地字符串
     */
    fun getString(key: String?): String? {
        return getString(key, "")
    }

    /**
     * 获取本地字符串，若不存在则返回对应默认值
     *
     * @param key          键值
     * @param defaultValue 默认值
     * @return 本地字符串
     */
    fun getString(key: String?, defaultValue: String?): String? {
        return sharedPreferences().getString(key, defaultValue)
    }

    /**
     * 获取本地整数，若不存在则返回0
     *
     * @param key 键值
     * @return 本地整数
     */
    fun getInt(key: String?): Int {
        return getInt(key, 0)
    }

    /**
     * 获取本地整数，若不存在则返回默认值
     *
     * @param key          键值
     * @param defaultValue 默认值
     * @return 本地整数
     */
    fun getInt(key: String?, defaultValue: Int): Int {
        return sharedPreferences().getInt(key, defaultValue)
    }

    /**
     * 获取本地浮点数，若不存在则返回0
     *
     * @param key 键值
     * @return 本地浮点数
     */
    fun getFloat(key: String?): Float {
        return getFloat(key, 0f)
    }

    /**
     * 获取本地浮点数，若不存在则返回默认值
     *
     * @param key          键值
     * @param defaultValue 默认值
     * @return 本地浮点数
     */
    fun getFloat(key: String?, defaultValue: Float): Float {
        return sharedPreferences().getFloat(key, defaultValue)
    }

    /**
     * 获取本地长整数，若不存在则返回0
     *
     * @param key 键值
     * @return 本地长整数
     */
    fun getLong(key: String?): Long {
        return getLong(key, 0)
    }

    /**
     * 获取本地长整数，若不存在则返回默认值
     *
     * @param key          键值
     * @param defaultValue 默认值
     * @return 本地长整数
     */
    fun getLong(key: String?, defaultValue: Long): Long {
        return sharedPreferences().getLong(key, defaultValue)
    }

    /**
     * 获取本地布尔值，若不存在则返回false
     *
     * @param key 键值
     * @return 本地布尔值
     */
    fun getBoolean(key: String?): Boolean {
        return getBoolean(key, false)
    }

    /**
     * 获取本地布尔值，若不存在则返回默认值
     *
     * @param key          键值
     * @param defaultValue 默认值
     * @return 本地布尔值
     */
    fun getBoolean(key: String?, defaultValue: Boolean): Boolean {
        return sharedPreferences().getBoolean(key, defaultValue)
    }

    /**
     * 移除key所对应的本地所存值
     *
     * @param key 键
     */
    fun removeValue(key: String?) {
        sharedPreferences().edit().remove(key).commit()
    }
}