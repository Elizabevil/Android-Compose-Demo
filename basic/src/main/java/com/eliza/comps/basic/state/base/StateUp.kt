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