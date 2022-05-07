package com.eliza.comps.basic.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.eliza.comps.basic.layouts.LayoutsCodelab
import com.eliza.comps.basic.ui.theme.AskcTheme


/*-*- coding:utf-8 -*-
 * @Author  : debi
 * @Time    : 5/5/22
 * @Software: Android Studio
 */
/*You need to use a Theme.AppCompat theme (or descendant) with this activity.*/
class LayoutsExActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AskcTheme {
                LayoutsCodelab()
            }
        }
    }
}

@Composable
private fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    AskcTheme {
        Greeting("Android")
    }
}
