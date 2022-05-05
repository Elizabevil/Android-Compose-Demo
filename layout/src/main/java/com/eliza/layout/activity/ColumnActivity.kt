package com.eliza.layout.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.eliza.layout.ui.theme.AskcTheme
import com.eliza.library.model.Message

class ColumnActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AskcTheme {
            }
        }
    }
}