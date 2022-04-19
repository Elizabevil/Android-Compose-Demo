package com.eliza.library.model

/*-*- coding:utf-8 -*-
 * @Author  : debi
 * @Time    : 4/2/22
 * @Software: Android Studio
 */
class Stu {
    var name: String = ""
    var age: Int = 0
    var id: Int = 0

    /**
     * fastJson 解析
     */
    constructor()
    constructor(id: Int) : this() {
        this.id = id
    }

    constructor(
        id: Int,
        name: String,
        age: Int
    ) : this(id) {
        this.id = id
        this.name = name
        this.age = age
    }

}