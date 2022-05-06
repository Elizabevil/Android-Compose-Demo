package com.eliza.comps.basic.layouts.base

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eliza.comps.basic.layouts.firstBaselineToTop
import com.eliza.comps.basic.ui.theme.AskcTheme

/*
* Compose 自带 Material 组件用于快速开发一个符合 Material Design 标准的 APP，最顶端的组件是 Scaffold
* 这个 Scaffold 组件的功能就跟它的翻译一样，用于构建一个基本的 Material Design 布局框架。它提供了诸如 TopAppBar、BottomAppBar、FloatingActionButton 和 Drawer 等常见的组件。
* */

// code 6
@Composable
private fun LayoutInCompose() {
    var selectedItem by remember { mutableStateOf(0) }
    val navItems = listOf("Songs", "Artists", "Playlists")

    Scaffold(
        topBar = {  // topBar 属性用于设置 AppBar
            TopAppBar(
                title = {  // 可设置标题
                    Text(text = "LayoutInCompose")
                },
                actions = {  // 设置 AppBar 上的按钮 Button
                    IconButton(onClick = { /*TODO*/ }) {
                        // Icon 系统为我们提供了许多常见的 Icon
                        Icon(Icons.Filled.Favorite, contentDescription = null)
                    }
                }
            )
        },
        bottomBar = {  // bottomBar 可用于设置 BottomNavigation
            BottomNavigation() {
                navItems.forEachIndexed { index, item ->
                    BottomNavigationItem(
                        icon = { Icon(Icons.Filled.Face, contentDescription = null) },
                        label = { Text(item) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index }
                    )
                }
            }
        }
    ) {
        BodyContent(
            modifier = Modifier
                .padding(it)
                .padding(8.dp)
        )
    }
}

@Composable
private fun BodyContent(modifier: Modifier) {
    Column(modifier = modifier) {
        Text(text = "Hi there!")
        Text(text = "Thanks for watching this")
    }
}


@Preview
@Composable
fun TextWithPaddingToBaselinePreview() {
    AskcTheme {
        LayoutInCompose()
    }
}