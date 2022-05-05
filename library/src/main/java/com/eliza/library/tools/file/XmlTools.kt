package com.eliza.library.tools.file

import android.util.Log
import com.eliza.library.model.User
import org.xml.sax.Attributes
import org.xml.sax.SAXException
import org.xml.sax.helpers.DefaultHandler

class SaxHelper : DefaultHandler() {
    private var user: User? = null
    private var users: ArrayList<User?>? = null

    //当前解析的元素标签
    private var tagName: String? = null

    /**
     * 当读取到文档开始标志是触发，通常在这里完成一些初始化操作
     */
    @Throws(SAXException::class)
    override fun startDocument() {
        users = ArrayList<User?>()
        Log.i("SAX", "读取到文档头,开始解析xml")
    }

    /**
     * 读到一个开始标签时调用,第二个参数为标签名,最后一个参数为属性数组
     */
    @Throws(SAXException::class)
    override fun startElement(
        uri: String?, localName: String, qName: String?,
        attributes: Attributes
    ) {
        if (localName == "user") {
            user = User("", 0)
//            user.setId(attributes.getValue("id").toInt())
            Log.i("SAX", "开始处理user元素~")
        }
        tagName = localName
    }

    /**
     * 读到到内容,第一个参数为字符串内容,后面依次为起始位置与长度
     */
    @Throws(SAXException::class)
    override fun characters(ch: CharArray?, start: Int, length: Int) {
        //判断当前标签是否有效
        if (tagName != null) {
            val data = String(ch!!, start, length)
            //读取标签中的内容
            user?.let {
                if (tagName == "name") {
                    it.userName = data
                    Log.i("SAX", "处理name元素内容")
                } else if (tagName == "age") {
                    it.userAge = data.toInt()
                    Log.i("SAX", "处理age元素内容")
                }
                it
            }
        }
    }

    /**
     * 处理元素结束时触发,这里将对象添加到结合中
     */
    @Throws(SAXException::class)
    override fun endElement(uri: String?, localName: String, qName: String?) {
        if (localName == "user") {
            users!!.add(user)
            user = null
            Log.i("SAX", "处理user元素结束~")
        }
        tagName = null
    }

    /**
     * 读取到文档结尾时触发，
     */
    @Throws(SAXException::class)
    override fun endDocument() {
        super.endDocument()
        Log.i("SAX", "读取到文档尾,xml解析结束")
    }

    //获取users集合
    fun getUsers(): ArrayList<User?>? {
        return users
    }
}