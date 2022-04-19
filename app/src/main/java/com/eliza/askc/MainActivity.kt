package com.eliza.askc

import android.os.Bundle
import android.os.Message
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.eliza.library.model.User

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView()
        setContent {

        }
    }

    @Composable
    fun MessageCard(name: String, user: User) {
        Text(
            text = "MessageCard:$name",
            color = Color.Cyan
        )
        Text(text = user.toString())

    }

    /**
     * 仅作为 结果预览
     */
    @Preview
    @Composable
    fun PreviewMessageCard() {
        MessageCard("as", User("As", 12))
    }
}
