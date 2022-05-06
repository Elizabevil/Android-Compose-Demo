package com.eliza.comps.basic.layouts.base

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/*
* Surface
    裁剪，根据 shape 属性描述的形状进行裁剪；
    高度，根据 elevation 属性设置容器平面的高度，让人看起来有阴影的效果；
    边框，根据 border 属性设置边框的粗细以及色值；
    背景，Surface 在 shape 指定的形状上填充颜色。
       如果颜色是 Colors.surface，则会将 LocalElevationOverlay 中设置的 ElevationOverlay 进行叠加，默认情况下只会发生在深色主题中。
       * 覆盖的颜色取决于这个 Surface 的高度，以及任何父级 Surface 设置的 LocalAbsoluteElevation。
       * 这可以确保一个 Surface 的叠加高度永远不会比它的祖先低，因为它是所有先前 Surface 的高度总和。
    内容颜色，根据 contentColor 属性给这个平面的内容指定一个首选色值，这个色值会被文本和图标组件以及点击态作为默认色值使用。
        * 当然可以被子节点设置的色值覆盖。
* */
// code 4
@Composable
private fun SurfaceShow() {
    Surface(
        shape = RoundedCornerShape(6.dp),
        border = BorderStroke(0.5.dp, Color.Green),  // 边框
        elevation = 10.dp,  // 高度
        modifier = Modifier
            .padding(10.dp),  // 外边距
//        color = Color.Black,  // 背景色
        contentColor = Color.Blue,
    ) {
        Surface(
            modifier = Modifier
                .clickable { }  // 点击事件在 padding 前，则此padding为内边距
                .padding(10.dp),
            contentColor = Color.Magenta  // 会覆盖之前 Surface 设置的 contentColor
        ) {
            Text(text = "This is a SurfaceDemo~")
        }
    }
}

@Preview
@Composable
private fun SurfaceShowPreview() {
    SurfaceShow()
}