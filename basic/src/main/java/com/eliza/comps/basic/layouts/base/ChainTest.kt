package com.eliza.comps.basic.layouts.base


/**
 * Chain 链，与 xml 中的用法一样，就是将一系列子元素按顺序打包成一行或一列。
 * 官方将这个 api 标记为可以改进的状态，可能后续会发生变化。api 只有两个，创建横向和纵向的链：
 * createHorizontalChain()、 createVerticalChain()
        第一个参数是需要打包在一起的所有子元素的id，第二个参数是链的类型，目前有三种类型：
        Spread：所有子元素平均分布在父布局空间中，是默认类型；
        SpreadInside：第一个和最后一个分布在链条的两端，其余子元素平均分布剩下的空间；
        Packed：所有子元素打包在一起，并放在链条的中间。
 */