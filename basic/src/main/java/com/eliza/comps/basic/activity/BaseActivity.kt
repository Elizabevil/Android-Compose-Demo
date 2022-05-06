package com.eliza.comps.basic.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eliza.comps.basic.R
import com.eliza.comps.basic.coms.HelloContent
import com.eliza.comps.basic.ui.theme.AskcTheme
import com.eliza.library.model.Message


/*-*- coding:utf-8 -*-
 * @Author  : debi
 * @Time    : 5/5/22
 * @Software: Android Studio
 */
class BaseActivity : ComponentActivity() {
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
//                    HelloContent()
                    BtnToActivity(context = context, BasicsActivity::class.java)
                }
            }
        }
    }
}

/*
* Material Design 是围绕三大要素构建的：Color、Typography、Shape。
* */

@Composable
private fun BtnToActivity(context: Context, activity: Class<*>) {
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
private fun DefaultPreview() {
    AskcTheme {
//        Conversation(MessagesData.Msgs)
//        HelloContent()
    }
}

@Composable
private fun Conversation(msgs: List<Message>) {
    // LazyColumn is the Compose version of a RecyclerView.
    // The lambda passed to items() is similar to a RecyclerView.ViewHolder.
    LazyColumn {
        items(msgs) { msg ->
            MessageCard(msg)
        }
    }
}

@Composable
private fun MessageCard(msg: Message) {
    Row(modifier = Modifier.padding(8.dp)) {
        Image(
            painter = painterResource(R.drawable.img01),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                // Set image size to 40 dp
                .size(40.dp)
                // Clip image to be shaped as a circle
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.secondaryVariant, CircleShape)
        )
        // Add a horizontal space between the image and the column
        Spacer(modifier = Modifier.width(8.dp))
//        ==================================
        // 我们在此变量中跟踪消息是否扩展
        var isExpanded by remember { mutableStateOf(false) }
        // surfaceColor will be updated gradually from one color to the other
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
        )
//        ==================================

        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            /*
            * Color
            通过 MaterialTheme.colors 使用已封装主题中的颜色设置样式。
            * 您可以在需要颜色的任意位置使用主题中的这些值。
            * */
            Text(
                text = msg.author,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.body2//排版
            )
            // Add a vertical space between the author and message texts
            Spacer(modifier = Modifier.height(4.dp))
            /*
                    Shape
                * 将消息正文封装在 Surface 可组合项中。这样即可自定义消息正文的形状和高度。
                * 此外，还要为消息添加内边距，以改进布局。
            * */
            Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 1.dp,
                // surfaceColor color will be changing gradually from primary to surface
                color = surfaceColor,
                // animateContentSize will change the Surface size gradually
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
            ) {
                Text(
                    text = if (msg.body.isNullOrEmpty()) "No Message" else msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.body2,
                )
            }
        }

    }

}