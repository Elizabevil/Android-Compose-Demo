package com.eliza.library.tools

import android.text.TextUtils


/*-*- coding:utf-8 -*-
 * @Author  : debi
 * @Time    : 5/2/22
 * @Software: Android Studio
 */
object TextTools {
    fun StringToInt(info: String?, defaultValue: Int = 0): Int {
        var value = defaultValue
        value =
            if (info.isNullOrEmpty() && info.isNullOrBlank()) {
                defaultValue
            } else if (TextUtils.isDigitsOnly(info)) {
                info.toInt()
            } else {
                defaultValue
            }
        return value
    }

    fun StringNullToBlank(info: String?, defaultValue: String = ""): String {
        var value =
            if (info.isNullOrEmpty()) {
                info.toString()
            } else {
                defaultValue
            }
        return value
    }


}