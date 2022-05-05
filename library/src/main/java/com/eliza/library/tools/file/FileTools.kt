package com.eliza.library.tools.file

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Environment
import android.os.storage.StorageManager
import android.os.storage.StorageManager.ACTION_MANAGE_STORAGE
import android.util.Log
import androidx.core.content.ContextCompat
import java.io.File
import java.util.*


/*-*- coding:utf-8 -*-
 * @Author  : debi
 * @Time    : 4/24/22
 * @Software: Android Studio
 */
object FileTools {

    /*
 * 创建嵌套目录
     您还可以通过以下方式创建嵌套目录或打开内部目录：在基于 Kotlin 的代码中调用 getDir()，
     * 或在基于 Java 的代码中将根目录和新目录名称传递到 File 构造函数：
 * */
//    context.getDir(dirName, Context.MODE_PRIVATE)

    /**
     * 验证存储空间的可用性
     */
    // Checks if a volume containing external storage is available
// for read and write.
    fun isExternalStorageWritable(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

    // Checks if a volume containing external storage is available to at least read.
    fun isExternalStorageReadable(): Boolean {
        return Environment.getExternalStorageState() in
                setOf(Environment.MEDIA_MOUNTED, Environment.MEDIA_MOUNTED_READ_ONLY)
    }

    /**
     * 选择物理存储位置 ==> SD Card
     */

    fun setStorageVolumes(context: Context) {
        val externalStorageVolumes: Array<out File> =
            ContextCompat.getExternalFilesDirs(context.applicationContext, null)
        val primaryExternalStorage = externalStorageVolumes[0]
    }

    fun getStorageVolumes(context: Context, filename: String) {
        val appSpecificExternalDir = File(context.getExternalFilesDir(null), filename)
    }


    /**
     * 媒体内容
    如果您的应用支持使用仅在您的应用内对用户有价值的媒体文件，最好将这些文件存储在外部存储空间中的应用专属目录中，
     */
    fun getAppSpecificAlbumStorageDir(context: Context, albumName: String): File? {
        // Get the pictures directory that's inside the app-specific directory on
        // external storage.
        val file = File(
            context.getExternalFilesDir(
                Environment.DIRECTORY_PICTURES
            ), albumName
        )
        if (!file?.mkdirs()) {
            Log.e("LOG_TAG", "Directory not created")
        }
        return file
    }

    fun showFreeStorage(context: Context, filesDir: File) {
        // App needs 10 MB within internal storage.
        val NUM_BYTES_NEEDED_FOR_MY_APP = 1024 * 1024 * 10L;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            val storageManager =
                context.applicationContext.getSystemService(StorageManager::class.java)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val appSpecificInternalDirUuid: UUID = storageManager.getUuidForPath(filesDir)
                val availableBytes: Long =
                    storageManager.getAllocatableBytes(appSpecificInternalDirUuid)
                if (availableBytes >= NUM_BYTES_NEEDED_FOR_MY_APP) {
                    storageManager.allocateBytes(
                        appSpecificInternalDirUuid, NUM_BYTES_NEEDED_FOR_MY_APP
                    )
                } else {
                    val storageIntent = Intent().apply {
                        // To request that the user remove all app cache files instead, set
                        // "action" to ACTION_CLEAR_APP_CACHE.
                        action = ACTION_MANAGE_STORAGE
                    }
                }


            }

        }
    }

}

