package com.eliza.comps.library.tools.file

import android.content.Context
import android.content.res.AssetManager
import com.eliza.comps.library.tools.InfoTools
import java.io.File
import java.io.InputStream


/**
 * 单个文件需要小于1M
 *   文件只能读，不能做写操作
 *   自带隐藏的3个文件夹资源：images 、sounds 、webkit
 *   不会被映射到R中，不能通过R.XXX.ID的方式访问，仅能通过AssetManager读取
 *   打包进apk时，不进行压缩，可以有多级目录（raw文件夹也不压缩，不可以有多级目录，会被映射到R中，放置多媒体文件）
 *   APK安装之后会放在"/data/app/ * *.apk"中，并不是一个目录，所以不能通过File操作。对数据的操作需要通过AssetsManger.open()方法得到流，
 *   然后就是对java流的操作。
 *   而对于其它的目录，如files位于"/data/data/package_name/files"，是可见、有方法获取的
 */
class FileAssetTools(val context: Context) {
    var assetManager: AssetManager = context.resources.assets

    /**
     * Get assets
     * 获取对应文件的 InputStream 流
     * @param fileName 文件名
     * @param rootDir 检索文件夹
     * @return
     */
    fun getAssets(fileName: String, rootDir: String = ""): InputStream? {
        if (isExistFile(fileName, rootDir)) {
            return assetManager.open(fileName)
        } else {
            InfoTools.LogTools(context, "in $rootDir |Not Exist：$fileName")
            return null
        }
    }

//"/assets/文件名"
    /**
     * Get asset files info
     * @param rootDir 目标目录下，默认为根目录：“”
     * @return 返回资源列表
     */
    fun getAssetFilesInfo(rootDir: String = ""): Array<String> {
        val list = assetManager.list(rootDir) as Array<String>
        if (list.isNullOrEmpty()) {
            // 当前为文件时，或者当前目录下为空
            return arrayOf<String>()
        }
        for (s in list) {
            InfoTools.LogTools(context::class, "该目录存在:$s")
        }
        return list
    }
    //某个目录是否存在 某文件
    fun isExistFile(fileName: String, filePath: String = ""): Boolean {
        val fileList = getAssetFilesInfo(filePath)
        if (fileList.isNullOrEmpty()) {
            return false
        }
        fileList.forEach { v ->
            if (fileName.equals(v)) {
                InfoTools.LogTools(context::class, "存在:$v")
                return true
            }
        }
        return false
    }

    /**
     * 遍历assets文件夹
     *
     * @param tab  格式化显示占位符
     * @param path 在assets中的路径
     */
    fun traverseAssets(tab: String, path: String) {
        val assetManager: AssetManager = assetManager
        try {
            // 获取path目录下，全部的文件、文件夹
            val list = assetManager.list(path)
            if (list == null || list.isEmpty()) {
                // 当前为文件时，或者当前目录下为空
                return
            }
            for (i in list.indices) {
                println(tab + list[i])
                var subPath: String
                subPath = if ("" == path) {
                    // 如果当前是根目录
                    list[i]
                } else {
                    // 如果当前不是根目录
                    path + File.separator + list[i]
                }
                traverseAssets(tab + "___", subPath)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}