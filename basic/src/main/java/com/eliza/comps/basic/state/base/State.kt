package com.eliza.comps.basic.state.base

import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview

/*
https://developer.android.google.cn/jetpack/compose/state?hl=zh-cn


mutableStateOf 会创建可观察的 MutableState<T>，后者是与 Compose 运行时集成的可观察类型。
    interface MutableState<T> : State<T> {
        override var value: T
    }
        value 如有任何更改，系统会安排重组读取 value 的所有可组合函数。对于 ExpandingCard，每当 expanded 发生变化时，都会导致重组 ExpandingCard。
* Remember中文是记住，它的作用也类似，它会存储变量第一次赋值的结果，之后如果使用该变量时会去获取记录的值，之后如果值没有变化也就不会再次发生重组
        val mutableState = remember { mutableStateOf(default) }
        var value by remember { mutableStateOf(default) }
        val (value, setValue) = remember { mutableStateOf(default) }

* */

@Composable
@Preview
private fun Pre() {
    val value by remember {
        mutableStateOf("abc")
    }
    materialUI(value = value, onValueChange = { newValue ->
        // 必须手动赋值
//        value = newValue
    })
}

@Composable
fun materialUI(value: String, onValueChange: (String) -> Unit) {
    TextField(value = value, onValueChange = onValueChange)
}
