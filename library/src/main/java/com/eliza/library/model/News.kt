package com.eliza.library.model


/*-*- coding:utf-8 -*-
 * @Author  : debi
 * @Time    : 4/8/22
 * @Software: Android Studio
 */
class News {
    var title: String = ""
    var content: String = ""
    var aIcon = 0

    constructor()
    constructor(title: String, content: String, aIcon: Int) {
        this.title = title
        this.content = content
        this.aIcon = aIcon
    }


}