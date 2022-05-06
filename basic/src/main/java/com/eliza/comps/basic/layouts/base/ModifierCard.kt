package com.eliza.comps.basic.layouts.base

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eliza.comps.basic.ui.theme.AskcTheme

@Composable
private fun ModifierCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()  // 相当于 width = match_parent
            .padding(10.dp)  // 外边距为 10dp
            .clip(RoundedCornerShape(6.dp))  // 设置圆角
            .clickable { }  // 点击事件
            .padding(16.dp)  // 内边距为 16dp
    ) {
        Surface(
            modifier = Modifier.size(50.dp),  // 设置大小
            shape = CircleShape,  // 设置形状
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)  // 设置色值
        ) {
            // 加载网络图片逻辑
        }

        Column(
            modifier = Modifier
                .padding(start = 8.dp)  // 单独设置 左边距
                .align(Alignment.CenterVertically)  // 设置里面的子元素竖直方向上居中分布
        ) {
            val user = ActiveUser.current // 通过 current 方法取出当前值
            Text(user.userName, fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(user.userAge.toString(), style = MaterialTheme.typography.body2)
            }
        }
    }
}


@Preview
@Composable
private fun ModifierCardPreview() {
    AskcTheme {
        ModifierCard()
    }
}