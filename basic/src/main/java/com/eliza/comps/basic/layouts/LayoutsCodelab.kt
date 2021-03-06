package com.eliza.comps.basic.layouts

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.eliza.comps.basic.R
import com.eliza.comps.basic.ui.theme.AskcTheme
import kotlin.math.max

/*
 *  B站动脑学院讲解  https://www.bilibili.com/video/BV1ob4y1a7ad?p=21&spm_id_from=pageDriver
 */

val topics = listOf(
    "Arts & Crafts 1", "Beauty 2", "Books 3", "Business 4", "Comics 5", "Culinary 6",
    "Design 7", "Fashion 8", "Film 8", "History 9", "Maths 10", "Music 11", "People 12",
    "Philosophy 13",
    "Religion 14", "Social sciences 15", "Technology 16", "TV 17", "Writing 18"
)


@Composable
fun LayoutsCodelab() {
    Scaffold(
        // 标题栏
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
        Column() {
            BodyContent(Modifier.padding(innerPadding))
            Image(
                painter = painterResource(R.drawable.basic_layouts_codelab_staggeredgrid),
                contentDescription = "布局分析"
            )
        }

    }
}

/**
 * Body content
 * 布局
 * @param modifier
 */
@Composable
fun BodyContent(modifier: Modifier = Modifier) {
    Row(modifier = modifier
        .background(color = Color.LightGray)
        .padding(10.dp)
        .size(300.dp)
        .horizontalScroll(rememberScrollState()),
        content = {
            //调用并设置网格布局
            /* * @param modifier
                 * @param rows 设置列数，默认是3
                 * @param content*/
            StaggeredGrid(rows = 4) {
                //从列表中活获取数据并填充元素
                for (topic in topics) {
                    // Chip 单个卡片元素
                    Chip(modifier = Modifier.padding(8.dp), text = topic)
                }
            }
        })
}

/**
 * Staggered grid
 * 先行后列  设置交错网格布局
 * @param modifier
 * @param rows 设置列数，默认是3
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
            // 每列 元素的 x 坐标放在一个数组里
            val rowX = IntArray(rows) { 0 }

            placeables.forEachIndexed { index, placeable ->
                val row = index % rows
                placeable.placeRelative(
                    x = rowX[row],
                    y = rowY[row]
                )
                //第一列，x坐标全部为0，下 -一列的x坐标要累加上前面元素的宽度
                //设置下一列的X坐标，本列x坐标 等于 前面一列的 x坐标 + 其本身
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
