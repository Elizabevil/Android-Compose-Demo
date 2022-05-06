package com.eliza.comps.basic.layouts

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.eliza.comps.basic.ui.theme.AskcTheme

/*
* 自定义修饰符
* */
fun Modifier.firstBaselineToTop(
    firstBaselineToTop: Dp
) = this.then(
    layout { measurable, constraints ->
        //测量元素
        val placeable = measurable.measure(constraints)

        // Check the composable has a first baseline
//        检查可组合物是否有第一个基线
        check(placeable[FirstBaseline] != AlignmentLine.Unspecified)

        //测量之后，获取元素的基线值
        val firstBaseline = placeable[FirstBaseline]

        // Height of the composable with padding - first baseline
        //元素新的Y坐标=新基线值-旧基线值
        /* roundToPx： 通过四舍五入将 Dp 转换为 Int*/
        val placeableY = firstBaselineToTop.roundToPx() - firstBaseline
        val height = placeable.height + placeableY

        /*
        * 最后要返回这一项，设置元素的宽高
        * */
        layout(placeable.width, height) {
            // Where the composable gets placed
            placeable.placeRelative(0, placeableY)
        }
    }
)

@Preview
@Composable
fun TextWithPaddingToBaselinePreview() {
    AskcTheme {
        Text("Hi there!", Modifier.firstBaselineToTop(30.dp))
    }
}

@Preview
@Composable
fun TextWithNormalPaddingPreview() {
    AskcTheme {
        Text("Hi there!", Modifier.padding(top = 32.dp))
    }
}
