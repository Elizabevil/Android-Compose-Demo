package com.eliza.comps.library.tools.file

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.eliza.comps.library.tools.InfoTools


/*-*- coding:utf-8 -*-
 * @Author  : debi
 * @Time    : 5/3/22
 * @Software: Android Studio
 */
object SharedPreferencesTools {

    fun save(context: Context, File_Name: String, dataMap: Map<String, String>) {
        /**
         * 数据保存位置
         * /data/data/com.eliza.save/shared_prefs/File_Name.xml
         * */
        val editor = context.getSharedPreferences(File_Name, AppCompatActivity.MODE_PRIVATE).edit()
        dataMap.forEach { (k, v) ->
            editor.putString(k, v)
        }
        if (editor.commit()) {
            InfoTools.ToastTools(context, "保存成功")
        }
    }

    fun read(context: Context, File_Name: String): SharedPreferences? {
        return context.getSharedPreferences(File_Name, AppCompatActivity.MODE_PRIVATE)

    }
}