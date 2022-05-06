package com.eliza.comps.basic.layouts

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.text.font.FontWeight

// code 1
@Composable
fun PhotographerCard() {
    Column {
        Text("小明", fontWeight = FontWeight.Bold)
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text("3 分钟前", style = MaterialTheme.typography.body2)
        }
    }
}
/*
* CompositionLocal 类位于 androidx.compose.runtime 包下，总的来说是用于在 composition 树中共享变量的值。
* 如果需要将顶层的 Composable 函数中的某个变量传递到最底层的 Composable 函数，
    * 通常最简单有效的方法就是：1）定义一个全局变量，通过全局变量传值；
    * 2）中间层的 Composable 函数添加一个形参，层层传递。
    但是这两种方式都不太优雅，尤其是嵌套过深，或者数据比较敏感，不想暴露给中间层的函数时，这种情况下，就可以使用 CompositionLocal 来隐式的将数据传递给所需的 composition 树节点。
    * CompositionLocal 在本质上就是分层的，它可以将数据限定在以某个 Composable 作为根结点的子树中，而且数据默认会向下传递，当然，
        * 当前子树中的某个 Composable 函数可以对该 CompositionLocal 的数据进行覆盖，从而使得新值会在这个 Composable 层级中继续向下传递。
    * */