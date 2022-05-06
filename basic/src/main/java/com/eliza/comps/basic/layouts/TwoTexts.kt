package com.eliza.comps.basic.layouts

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eliza.comps.basic.ui.theme.AskcTheme

/*
* Compose只测量子元素一次，测量两次会引发运行时异常。但是，有时在测量
        子元素之前，我们需要一些有关子元素的信息。
    Intrinsics允许您在实际测量之前查询子项。
        ●(min|max)IntrinsicWidth: 鉴于此高度，您可以正确绘制内容的最小/最大宽度是多少。
        ●(min|max)IntrinsicHeight: 鉴于此宽度，您可以正确绘制内容的最小/最大高度是多少。
* */
@Composable
fun TwoTexts(modifier: Modifier = Modifier, text1: String, text2: String) {
    Row(modifier = modifier.height(IntrinsicSize.Min)) {//容器最小值，刚好包裹内部组件 （warp）
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp)
                .wrapContentWidth(Alignment.Start),
            text = text1
        )
        Divider(
            color = Color.Yellow, modifier = Modifier
                .fillMaxHeight()//(parent)
                .width(3.dp)
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(end = 4.dp)
                .wrapContentWidth(Alignment.End),
            text = text2
        )
    }
}

@Preview
@Composable
fun TwoTextsPreview() {
    AskcTheme() {
        Surface {
            TwoTexts(text1 = "Hi", text2 = "there")
        }
    }
}
