package com.eliza.comps.basic.state.base

import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eliza.comps.basic.ui.theme.AskcTheme
import kotlinx.coroutines.delay

/*
* Remember中文是记住，它的作用也类似，它会存储变量第一次赋值的结果，之后如果使用该变量时会去获取记录的值，之后如果值没有变化也就不会再次发生重组
    val name = mutableStateOf("Bob")               // 1
    val name by mutableStateOf("Bob")              // 2
    val name by remember { mutableStateOf("Bob") } // 3

* */

@Composable
@Preview
private fun Pre() {
    val value by remember {
        mutableStateOf("abc")
    }
    materialUI(value = value, onValueChange = { newValue ->
        // 必须手动赋值
        value = newValue
    })
}

@Composable
fun materialUI(value: String, onValueChange: (String) -> Unit) {
    TextField(value = value, onValueChange = onValueChange)
}
