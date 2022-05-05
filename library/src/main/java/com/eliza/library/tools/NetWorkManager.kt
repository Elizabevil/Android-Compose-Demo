package com.eliza.library.tools

import android.Manifest
import android.content.Context
import android.net.*
import android.net.wifi.WifiManager
import android.os.Build
import android.telephony.TelephonyManager
import androidx.annotation.RequiresApi

object NetWorkManager {

    @RequiresApi(Build.VERSION_CODES.M)
    inline fun <reified T : Any> getManager(context: Context): T {
        return context.applicationContext.getSystemService(T::class.java)
    }

    //WifiManager
    fun getWifiManager(context: Context): WifiManager {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            context.applicationContext.getSystemService(WifiManager::class.java)
        } else {
            context.applicationContext.getSystemService(Context.WIFI_SERVICE)
        } as WifiManager
    }

    //TelephonyManager
    fun getTelephonyManager(context: Context): TelephonyManager {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            context.applicationContext.getSystemService(TelephonyManager::class.java)
        } else {
            context.applicationContext.getSystemService(Context.TELEPHONY_SERVICE)
        } as TelephonyManager
    }

    //    ConnectivityManager：回应关于网络连接状态的查询。当网络连接发生变化时，它还会通知应用。
//    NetworkInfo：描述指定类型的网络接口的状态（目前为移动网络或 Wi-Fi）。
    //ConnectivityManager
    fun getConnectivityManager(context: Context): ConnectivityManager {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            context.applicationContext.getSystemService(ConnectivityManager::class.java)
        } else {
            context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE)
        } as ConnectivityManager
    }

    fun getBaseNetInfo(context: Context) {
        val connMgr = getConnectivityManager(context)
        var isWifiConn: Boolean = false
        var isMobileConn: Boolean = false
        val isGet = PermissionUtils.requestPermissions(context,
            arrayOf(Manifest.permission.ACCESS_NETWORK_STATE))
        InfoTools.ToastTools(context, "获取网络权限:$isGet")
        //获取当前活跃的网络数据信息，该方法需要申请系统 ACCESS_NETWORK_STATE 权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI)?.state?.let {
                InfoTools.ToastTools(context,
                    it)
            }

        }
    }

    fun setWifiStatus(context: Context, isEnable: Boolean) {
        val wifiManager = getWifiManager(context)
        if (isEnable) {// 开启wifi
            if (!wifiManager.isWifiEnabled) {
                InfoTools.LogTools(context::class.java.name, "开启wifi");
                wifiManager.isWifiEnabled = true;

            }
        } else {
            // 关闭 wifi
            if (wifiManager.isWifiEnabled) {
                InfoTools.LogTools(context::class.java.name, "关闭wifi");
                wifiManager.isWifiEnabled = false;

            }
        }


    }


}