package com.eliza.comps.basic.layouts.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout


/**
Chain 链，与 xml 中的用法一样，就是将一系列子元素按顺序打包成一行或一列。
 * * 官方将这个 api 标记为可以改进的状态，可能后续会发生变化。api 只有两个，创建横向和纵向的链：
 * * createHorizontalChain()、 createVerticalChain()
 *   第一个参数是需要打包在一起的所有子元素的id，第二个参数是链的类型，目前有三种类型：
 *      Spread：所有子元素平均分布在父布局空间中，是默认类型；
 *      SpreadInside：第一个和最后一个分布在链条的两端，其余子元素平均分布剩下的空间；
 *      Packed：所有子元素打包在一起，并放在链条的中间。
 */
// code 14
@Composable
private fun ConstraintLayoutChainDemo() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (box1, box2, box3) = createRefs()
        createHorizontalChain(box1, box2, box3, chainStyle = ChainStyle.Spread)

        Box(modifier = Modifier
            .size(100.dp)
            .background(Color.Red)
            .constrainAs(box1) {})
        Box(modifier = Modifier
            .size(100.dp)
            .background(Color.Green)
            .constrainAs(box2) {})
        Box(modifier = Modifier
            .size(100.dp)
            .background(Color.Blue)
            .constrainAs(box3) {})
    }
}

@Preview
@Composable
private fun ConstraintLayoutChainPreview() {
    ConstraintLayoutChainDemo()
}
