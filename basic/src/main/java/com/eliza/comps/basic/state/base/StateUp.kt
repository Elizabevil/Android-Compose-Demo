package com.eliza.comps.basic.state.base


/*-*- coding:utf-8 -*-
 * @Author  : debi
 * @Time    : 5/7/22
 * @Software: Android Studio
 */
/**
状态提升
    Compose 中的状态提升是一种将状态移至可组合项的调用方以使可组合项无状态的模式。Jetpack Compose 中的常规状态提升模式是将状态变量替换为两个参数：
    value: T：要显示的当前值
    onValueChange: (T) -> Unit：请求更改值的事件，其中 T 是建议的新值

    并不局限于 onValueChange。如果更具体的事件适合可组合项，您应使用 lambda 定义这些事件，就像使用 onExpand 和 onCollapse 定义适合 ExpandingCard 的事件一样。
*/
/*
    单一可信来源：我们会通过移动状态而不是复制状态，来确保只有一个可信来源。这有助于避免 bug。
    封装：只有有状态可组合项能够修改其状态。这完全是内部的。
    可共享：可与多个可组合项共享提升的状态。如果想在另一个可组合项中执行 name 操作，可以通过变量提升来做到这一点。
    可拦截：无状态可组合项的调用方可以在更改状态之前决定忽略或修改事件。
    解耦：无状态 ExpandingCard 的状态可以存储在任何位置。例如，现在可以将 name 移入 ViewModel。
* */