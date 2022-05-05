package com.eliza.layout

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eliza.layout.coms.HelloContent
import com.eliza.layout.ui.theme.AskcTheme

class MainActivity : ComponentActivity() {
    private val context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AskcTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    MessageCard(Message("张三", "你猜猜看"))
//                    Conversation(MessagesData.Msgs)
                    HelloContent()

                }
            }
        }
    }
}

/*
* Material Design 是围绕三大要素构建的：Color、Typography、Shape。
* */

@Composable
fun BtnToActivity(context: Context, activity: Class<*>) {
    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Button(fun() {
            context.startActivity(Intent(context.applicationContext, activity::class.java))
        }) {
            Text(text = "BtnToActivity:${activity::class.java.name}")
        }
    }

}

@Preview(
    name = "Light Mode",
    showBackground = true,
)
/*
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
)
*/

@Composable
fun DefaultPreview() {
    AskcTheme {
//        Conversation(MessagesData.Msgs)
        HelloContent()
    }
}

