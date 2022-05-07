package com.eliza.comps.basic.state.base

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
状态提升
 * state hoisting
 *   一个具有高可重用性的compose function应该是stateless的，即不应该在内部包含状态。
 *   我们应该把状态提到外部去，来提升可重用性
 *
 *  Compose 中的状态提升是一种将状态移至可组合项的调用方以使可组合项无状态的模式。
 *       一来调用方可以通过状态修改组合项，而不止是组合项自身调用。
 *       二来不同组合项可以复用同个状态。
 *  Jetpack Compose 中的常规状态提升模式是将状态变量替换为两个参数：
 *      value: T：要显示的当前值
 *      onValueChange: (T) -> Unit：请求更改值的事件，其中 T 是建议的新值
 *      ·并不局限于 onValueChange。如果更具体的事件适合可组合项，您应使用 lambda 定义这些事件，就像使用 onExpand 和 onCollapse 定义适合 ExpandingCard 的事件一样。
 *
 * * 状态应至少提升到使用该状态（读取）的所有可组合项的最低共同父项。
 * * 状态应至少提升到它可以发生变化（写入）的最高级别。
 * * 如果两种状态发生变化以响应相同的事件，它们应一起提升。
 */
/*
    单一可信来源：我们会通过移动状态而不是复制状态，来确保只有一个可信来源。这有助于避免 bug。
    封装：只有有状态可组合项能够修改其状态。这完全是内部的。
    可共享：可与多个可组合项共享提升的状态。如果想在另一个可组合项中执行 name 操作，可以通过变量提升来做到这一点。
    可拦截：无状态可组合项的调用方可以在更改状态之前决定忽略或修改事件。
    解耦：无状态 ExpandingCard 的状态可以存储在任何位置。例如，现在可以将 name 移入 ViewModel。
* */
@Preview
@Composable
private fun HelloScreenPreview() {
    HelloScreen()
}
/*
状态下降、事件上升的这种模式称为“单向数据流”。
    在这种情况下，状态会从 HelloScreen 下降为 HelloContent，事件会从 HelloContent 上升为 HelloScreen。
    通过遵循单向数据流，您可以将在界面中显示状态的可组合项与应用中存储和更改状态的部分解耦。
* */
/**
 *  通过从 HelloContent 中提升出状态，更容易推断该可组合项、在不同的情况下重复使用它，以及进行测试。
 * HelloContent 与状态的存储方式解耦。解耦意味着，如果您修改或替换 HelloScreen，不必更改 HelloContent 的实现方式。
 */
@Composable
private fun HelloScreen() {
    var name by rememberSaveable { mutableStateOf("") }
    HelloContent(name = name, onNameChange = { name = it })
}

/**
 * Hello content
 * 从上层获取参数
 *
 * @param name
 * @param onNameChange
 * @receiver
 */
@Composable
private fun HelloContent(name: String, onNameChange: (String) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Hello, $name",
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.h5
        )
        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text("Name") }
        )
    }
}