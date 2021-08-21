package com.wang.franch.ui

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

/**
 * 权限请求基类
 *
 * @Author Leiyu
 * @CreateDate 2021/2/24
 */
open class ActivityPermission : AppCompatActivity() {
    private val PERMISSION_REQUEST_CODE = 3

    /**
     * 对应权限是否被授予
     *
     * @param permission 危险权限
     * @return true被授予，false相反
     */
    protected fun isPermissionGranted(permission: String?): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            permission!!
        ) == PackageManager.PERMISSION_GRANTED
    }

    protected fun checkAndRequestPermission(permission: String?) {
        if (isPermissionGranted(permission)) {
            onPermissionGranted(permission)
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(permission),
                PERMISSION_REQUEST_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                onPermissionGranted(permissions[0])
            } else {
                onPermissionDenied(permissions[0])
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    /**
     * 权限授予回调
     *
     * @param permission 请求的危险权限
     */
    protected open fun onPermissionGranted(permission: String?) {}

    /**
     * 权限拒绝授予回调
     *
     * @param permission 请求的危险权限
     */
    protected open fun onPermissionDenied(permission: String?) {}
}