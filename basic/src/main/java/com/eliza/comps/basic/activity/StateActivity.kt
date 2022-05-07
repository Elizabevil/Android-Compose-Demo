package com.eliza.comps.basic.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.eliza.comps.basic.state.WellnessScreen
import com.eliza.comps.basic.ui.theme.AskcTheme


/*-*- coding:utf-8 -*-
 * @Author  : debi
 * @Time    : 5/5/22
 * @Software: Android Studio
 */
class StateActivity : ComponentActivity() {
    private val context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AskcTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    MessageCard(Message("张三", "你猜猜看"))
//                    Conversation(MessagesData.Msgs)
//                    HelloContent()
                    WellnessScreen()//state

                }
            }
        }
    }
}

/*
* Material Design 是围绕三大要素构建的：Color、Typography、Shape。
* */
