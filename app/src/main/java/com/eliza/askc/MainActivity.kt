package com.eliza.askc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.eliza.comps.library.model.User

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView()


        setContent {
            MessageCard(name = "Sa", user = User("As", 12))
        }
    }

    @Composable
    fun MessageCard(name: String, user: User) {
        Row(Modifier.padding(all = 8.dp)) {
            Column() {
                Text(
                    text = "MessageCard:$name",
                    color = Color.Cyan
                )

                Text(text = user.toString())
            }
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)

            )
        }

    }

    @Composable
    fun ToOtherPage() {
        val navController = rememberNavController()
        val backstackEntry = navController.currentBackStackEntryAsState()
        //???????????????????????????
        val route = backstackEntry.value?.destination?.route
//        3.???????????????NavHost

        NavHost(navController = navController, startDestination ="onePage" ){
            composable("onePage") { FirstPage(navController = navController) }
        }
    }

    /**
     *FirstPage????????????
     */
    @Composable
    fun FirstPage(navController: NavController) {
        Column {
            Text(text = "FirstPage??????")
            Button(onClick = {
                // ?????????SecondPage????????????
                navController.navigate("second_page")
            }) {
                Text(text = "???SecondPage")
            }
        }
    }


    /**
     * ????????? ????????????
     */
//    @Preview
    @Composable
    fun PreviewMessageCard() {
        MessageCard("as", User("As", 12))
    }
}
