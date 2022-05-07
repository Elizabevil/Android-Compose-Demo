package com.eliza.comps.basic.layouts.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eliza.comps.library.model.User

// code 1
@Composable
private fun PhotographerCard() {
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

// code 2
// compositionLocalOf 方法可以创建一个 CompositionLocal 实例
val ActiveUser = compositionLocalOf {
    // 设置默认值
    User("小明", 12)
    // 如果无须默认值，也可设置错误信息
//    error("No active user found!")
}

@Composable
fun PhotographerCard2() {
    Column {
        val user = ActiveUser.current // 通过 current 方法取出当前值
        Text(user.userName, fontWeight = FontWeight.Bold)
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(user.userAge.toString(), style = MaterialTheme.typography.body2)
        }

        // 通过 providers 中缀表达式可以重新对 CompositionLocal 实例赋值
        CompositionLocalProvider(ActiveUser provides User("小红", 23)) {
            val newUser = ActiveUser.current
            Text(newUser.userName, fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(newUser.userAge.toString(), style = MaterialTheme.typography.body2)
            }
        }
    }
}

// code 3
@Composable
private fun PhotographerCard3() {
    Row {
        Surface(//Surface 位于 androidx.compose.material 包中，很显然它是 Material Design 风格的，可以将它理解为一个容器，我们可以设置容器的高度（带阴影效果）、Shape形状、Background背景等
            modifier = Modifier.size(50.dp),  // 设置大小
            shape = CircleShape,  // 设置形状
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)  // 设置色值
        ) {
            // 加载网络图片逻辑
        }

        Column {
            val user = ActiveUser.current // 通过 current 方法取出当前值
            Text(user.userName, fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(user.userAge.toString(), style = MaterialTheme.typography.body2)
            }
        }
    }
}

@Preview
@Composable
private fun PhotographerCardPreview() {
    PhotographerCard()
//    PhotographerCard2()
//    PhotographerCard3()
}

