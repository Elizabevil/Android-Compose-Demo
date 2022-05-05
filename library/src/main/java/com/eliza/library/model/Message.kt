package com.eliza.library.model


/*-*- coding:utf-8 -*-
 * @Author  : debi
 * @Time    : 5/5/22
 * @Software: Android Studio
 */
class Message {
    var author: String
    var body: String

    constructor(author: String, body: String) {
        this.author = author
        this.body = body
    }
}

object MessagesData {
    val Msgs: List<Message> = listOf(
        Message("asdqw", "kas2asd342qwe Messag、\nqwesdf233sdj"),
        Message("23q", "kaaqweasdqwqweqwesdfesdfsdsj"),
        Message("qwe", "ksqweqqw Message\nqwesdfwqweqwesdfesdfasj"),
        Message("23qw", "kaqweqasdwe Messagsdfdsj"),
        Message("q2w", "kqweqwesdfasasj"),
        Message("qhasgwqw", "qweq Mes\nsagwesdfvkasj"),
        Message("asqfw", ""),
        Message("qasf1212w", "qwdasdfeqwesdfkaghsj"),
        Message("qasf1212w", "qwdasdfeqwesdfkaghsj"),
        Message("12asqfw", "qweqasdwas\ndfaesdfkaghsj"),
        Message("aasdsdw", "fghfqasdwe Messagq\nwesdfgh"),
        Message("aasdsdw", "fghfqaasdsdwe Messagq\nwesdfgh")
    )
}
