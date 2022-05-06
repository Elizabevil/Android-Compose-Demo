package com.eliza.comps.basic.layouts

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.eliza.comps.basic.ui.theme.AskcTheme
import kotlin.math.max

val topics = listOf(
    "Arts & Crafts 1", "Beauty 2", "Books 3", "Business 4", "Comics 5", "Culinary 6",
    "Design 7", "Fashion 8", "Film 8", "History 9", "Maths 10", "Music 11", "People 12",
    "Philosophy 13",
    "Religion 14", "Social sciences 15", "Technology 16", "TV 17", "Writing 18"
)

@Composable
fun LayoutsCodelab() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "LayoutsCodelab")
                },
                actions = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(Icons.Filled.Favorite, contentDescription = null)
                    }
                }
            )
        }
    ) { innerPadding ->
        BodyContent(Modifier.padding(innerPadding))
    }
}

@Composable
fun BodyContent(modifier: Modifier = Modifier) {
    Row(modifier = modifier
        .background(color = Color.LightGray)
        .padding(16.dp)
        .size(600.dp)
        .horizontalScroll(rememberScrollState()),
        content = {
            StaggeredGrid {
                for (topic in topics) {
                    Chip(modifier = Modifier.padding(8.dp), text = topic)
                }
            }
        })
}

/**
 * Staggered grid
 * 先行后列
 * @param modifier
 * @param rows 默认是3
 * @param content
 * @receiver
 */
@Composable
fun StaggeredGrid(
    modifier: Modifier = Modifier,
    rows: Int = 3,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->

        // Keep track of the width of each row
        //用于保存每行的宽度值
        val rowWidths = IntArray(rows) { 0 }

        // Keep track of the max height of each row
        //用于保存每行的高度值
        val rowHeights = IntArray(rows) { 0 }

        // Don't constrain child views further, measure them with given constraints
        // List of measured children
//       获取表格的宽高
        val placeables = measurables.mapIndexed { index, measurable ->
            // Measure each child
            // 测量每一个元素
            val placeable = measurable.measure(constraints)

            // Track the width and max height of each row
            //  跟踪每行的宽度和最大高度
            // 通过取余 运算 获取下标
            /*
            row % 3
            * 0 3 6 9
            * 1
            * 2
            * */
            val row = index % rows

            // -行的宽度等于这一 行所有元素宽度之和

            rowWidths[row] += placeable.width
            // -行的 高度等于这一行所有元素 最高的元素
            rowHeights[row] = max(rowHeights[row], placeable.height)

            placeable
        }


/*
* rangeTo    创建从该值到指定的其他值的范围。
* coerceIn ：确保此值位于指定范围内。
    * 返回： 如果该值在范围内，则返回该值；
    * 如果该值小于 range.start，则返回 range.start；
    * 如果该值大于 range.endInclusive，则返回 range.endInclusive。
* */
        // Grid's width is the widest row
        //若为空 则最小宽度值，否则给一个 范围值
        val width =
            rowWidths.maxOrNull()

                ?.coerceIn(constraints.minWidth.rangeTo(constraints.maxWidth))
                ?: constraints.minWidth

        // Grid's height is the sum of the tallest element of each row
        // coerced to the height constraints
        val height = rowHeights.sumOf { it }
            .coerceIn(constraints.minHeight.rangeTo(constraints.maxHeight))

        // Y of each row, based on the height accumulation of previous rows
        val rowY = IntArray(rows) { 0 }
        for (i in 1 until rows) {
//            每一行元素的Y坐标 等于 它前一个元素的 y 坐标加其高度
//            rowY[0] = rowY[0] + rowHeights[0]
            rowY[i] = rowY[i - 1] + rowHeights[i - 1]
        }

        // Set the size of the parent layout
        layout(width, height) {
            // x co-ord we have placed up to, per row
            val rowX = IntArray(rows) { 0 }

            placeables.forEachIndexed { index, placeable ->
                val row = index % rows
                placeable.placeRelative(
                    x = rowX[row],
                    y = rowY[row]
                )
                rowX[row] += placeable.width
            }
        }
    }
}

/**
 * Chip
 * 单个卡片元素
 * @param modifier
 * @param text
 */
@Composable
fun Chip(modifier: Modifier = Modifier, text: String) {
    //一个卡片，圆角，里面包含一个Row，第-列是Box，第二列是文本
    Card(
        modifier = modifier,
        border = BorderStroke(color = Color.Blue, width = Dp.Hairline),//边框
        shape = RoundedCornerShape(8.dp)//圆形边框的弧度
    ) {
        Row(
            modifier = Modifier.padding(start = 8.dp, top = 4.dp, end = 8.dp, bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(16.dp, 16.dp)
                    .background(color = MaterialTheme.colors.secondary)
            )
            Spacer(Modifier.width(10.dp))//间隙
            Text(text = text)
        }
    }
}

@Preview
@Composable
fun ChipPreview() {
    AskcTheme {
        Chip(text = "Hi there")
    }
}

@Preview
@Composable
fun LayoutsCodelabPreview() {
    AskcTheme {
        LayoutsCodelab()
    }
}
