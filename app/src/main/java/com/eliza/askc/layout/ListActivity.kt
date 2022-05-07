package com.eliza.askc.layout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.eliza.comps.library.model.User


/*-*- coding:utf-8 -*-
 * @Author  : debi
 * @Time    : 4/19/22
 * @Software: Android Studio
 */
class ListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView()
        setContent {
            ListUser(userList = ListData.userList)
        }
    }

    @Composable
    fun ListUser(userList: List<User>) {
        LazyColumn {
            items(userList) { user ->
                MessageCard(user)
            }
        }
    }

    @Composable
    fun MessageCard(user: User) {
        var isExpanded by remember {
            mutableStateOf(false)
        }
        Spacer(modifier = Modifier.width(10.dp))

        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = user.toString(),
                modifier = Modifier.padding(4.dp),
                color = Color.LightGray,
                style = MaterialTheme.typography.body2,//字体
                maxLines = if (isExpanded) Int.MAX_VALUE else 1,//只显示一
            )
        }


    }

//    @Preview
    @Composable
    fun Masin() {
        ListUser(userList = ListData.userList)

    }
}


object ListData {
    val userList = listOf<User>(
        User("1", 1),
        User("2", 2),
        User("3", 3),
        User("4", 4),
        User("5", 5),
        User("6", 6),
        User("7", 7),
        User("8", 8),

        )
}