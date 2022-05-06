
package com.eliza.comps.basic.layouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import kotlinx.coroutines.launch

/**
 * SimpleColumn
 * */
@Composable
fun SimpleColumn() {
    Column {
        repeat(10) {
            Text("单纯的叠加重复：Item #$it", style = MaterialTheme.typography.subtitle1)
        }
    }
}

@Preview
@Composable
fun SimpleColumnPreview() {
    SimpleColumn()
}

/**
 * Simple list
 * Modifier.verticalScroll
 */
@Composable
fun SimpleList() {
    // We save the scrolling position with this state
    // 使用 rememberScrollState 保存滚动的位置信息
    val scrollState = rememberScrollState()
    // Modifier.verticalScroll 可添加竖直方向上的滚动属性
    // 使用 Column 的 Modifier.verticalScroll 方法确实可以创建一个可滑动的 List，
    //      但是这种方法在开始时就会将所有 item 全部加载，类似于 ScrollView
    Column(Modifier.verticalScroll(scrollState)) {
        repeat(20) {
            Text("SimpleList:Item #$it", style = MaterialTheme.typography.subtitle1)
            Divider(color = Color.Black, thickness = 1.5.dp, startIndent = 5.dp)
        }
    }
}

@Preview
@Composable
fun SimpleListPreview() {
    SimpleList()
}

/**
 * 一般是使用 LazyColumn 来展示列表数据，LazyColumn 开始时并不会把所有的列表数据都加载进内存，
 * 它会先将展示在屏幕上的列表数据加载进内存，当滑动查看更多列表数据时，才会将这些数据加载到内存中。
 * 而且，LazyColumn 在内部已经实现了滑动的逻辑，不需要用 Modifier.verticalScroll 来实现
 * */
@Composable
fun LazyList() {
    // We save the scrolling position with this state
    val scrollState = rememberLazyListState()

    LazyColumn(state = scrollState) {
        items(100) {
            Text("LazyList:Item #$it", style = MaterialTheme.typography.subtitle1)
        }
    }
}

@Preview
@Composable
fun LazyListPreview() {
    LazyList()
}
/*-opt-in=kotlin.RequiresOptIn'*/
@OptIn(ExperimentalCoilApi::class)
@Composable
fun ImageListItem(index: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = rememberImagePainter(
                data = "https://developer.android.com/images/brand/Android_Robot.png"
            ),
            contentDescription = "Android Logo",
            modifier = Modifier.size(50.dp)
        )
        Spacer(Modifier.width(10.dp))
        Text("ImageListItem:Item #$index", style = MaterialTheme.typography.subtitle1)
    }
}

@Preview
@Composable
fun ImageListItemPreview() {
    ImageListItem(1)
}

@Composable
fun ImageList() {
    // We save the scrolling position with this state
    val scrollState = rememberLazyListState()

    LazyColumn(state = scrollState) {
        items(100) {
            ImageListItem(it)
        }
    }
}

@Preview
@Composable
fun ImageListPreview() {
    ImageList()
}

@Composable
fun ScrollingList() {
    val listSize = 100
    // We save the scrolling position with this state
    val scrollState = rememberLazyListState()
    // We save the coroutine scope where our animated scroll will be executed
    val coroutineScope = rememberCoroutineScope()

    Column {
        Row {
            Button(onClick = {
                coroutineScope.launch {
                    // 0 is the first item index
                    scrollState.animateScrollToItem(0)
                }
            }) {
                Text("Scroll to the top")
            }

            Button(onClick = {
                coroutineScope.launch {
                    // listSize - 1 is the last index of the list
                    scrollState.animateScrollToItem(listSize - 1)
                }
            }) {
                Text("Scroll to the end")
            }
        }

        LazyColumn(state = scrollState) {
            items(listSize) {
                ImageListItem(it)
            }
        }
    }
}

@Preview
@Composable
fun ScrollingListPreview() {
    ScrollingList()
}
