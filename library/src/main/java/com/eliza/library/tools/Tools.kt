package com.eliza.library.tools


import android.content.Context
import android.util.Log
import android.widget.Toast
import java.util.logging.Level


/*-*- coding:utf-8 -*-
 * @Author  : debi
 * @Time    : 4/2/22
 * @Software: Android Studio
 */
object Tools {
    @JvmStatic
    fun LogTools(TAG: Any, info: Any, level: Int? = null) {
        level ?: Log.d(TAG.toString(), info.toString())
        level?.let {
            when (level % 3) {
                0 -> Log.i(TAG.toString(), info.toString())
                1 -> Log.d(TAG.toString(), info.toString())
                2 -> Log.e(TAG.toString(), info.toString())
                else -> Log.d(TAG.toString(), info.toString())
            }
        }
    }

    @JvmStatic
    fun ToastTools(context: Context, info: Any) {
        Toast.makeText(context, info.toString(), Toast.LENGTH_SHORT).show()
    }



}