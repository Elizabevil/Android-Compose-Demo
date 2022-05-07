package com.eliza.comps.library.tools

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object PermissionUtils {
    var REQUEST_CODE = 2022 //任意标识

    /**
     * 权限检查
     * @param ct 当前Activity
     * @param permissions 需要检查的权限
     * @return boolean
     */
    fun requestPermissions(ct: Context, permissions: Array<String>): Boolean {
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(
                    ct,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                //申请权限 参数分别是 上下文、权限集合(String)、请求码
                ActivityCompat.requestPermissions((ct as Activity), permissions, REQUEST_CODE)
                return false
            }
        }
        return true
    }

    /**
     * Is get permissions
     * 是否获取 某权限
     * @param ct 当前Activity
     * @param permissions 需要检查的权限
     */
    fun isGetPermissions(ct: Context, permissions: Array<String>) {
        for (i in permissions) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ct.checkSelfPermission(i) == PackageManager.PERMISSION_GRANTED) {
                    InfoTools.LogTools(ct.packageName, "HAVE::$i")
                } else {
                    InfoTools.LogTools(ct.packageName, "NO::$i", 2)
                }
            }
        }
    }
}
