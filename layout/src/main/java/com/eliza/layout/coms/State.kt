package com.eliza.layout.coms

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * https://developer.android.google.cn/jetpack/compose/state?hl=zh-cn
 */

///建议您使用可观察的数据存储器（如 State<List<T>>）和不可变的 listOf()，而不是使用不可观察的可变对象。
/*
* 其他受支持的状态类型
Jetpack Compose 并不要求您使用 MutableState<T> 存储状态。
* Jetpack Compose 支持其他可观察类型。在 Jetpack Compose 中读取其他可观察类型之前，您必须将其转换为 State<T>，以便 Jetpack Compose 可以在状态发生变化时自动重组界面。
    Compose 附带一些可以根据 Android 应用中使用的常见可观察类型创建 State<T> 的函数：
            LiveData
            Flow
            RxJava2
要点：Compose 将通过读取 State<T> 对象自动重组界面。
        如果您在 Compose 中使用 LiveData 等其他可观察类型，应该先将其转换为 State<T>，
        * 然后再使用诸如 LiveData<T>.observeAsState() 之类的可组合扩展函数在可组合项中读取它。
*
*
* */

/*
* TextField 不会像在基于 XML 的命令式视图中那样自动更新。可组合项必须明确获知新状态，才能相应地进行更新。
* 可组合项中的状态
    *  可组合函数可以使用 remember 可组合项记住单个对象。
    * 系统会在初始组合期间将由 remember 计算的值存储在组合中，并在重组期间返回存储的值。remember 既可用于存储可变对象，又可用于存储不可变对象。
* */
/*  mutableStateOf 会创建可观察的 MutableState<T>，后者是与 Compose 运行时集成的可观察类型。
   * interface MutableState<T> : State<T> {
           override var value: T
   }
*/

/*
* value 如有任何更改，系统会安排重组读取 value 的所有可组合函数。
* 对于 ExpandingCard，每当 expanded 发生变化时，都会导致重组 ExpandingCard。
在可组合项中声明 MutableState 对象的方法有三种：
        val mutableState = remember { mutableStateOf(default) }
        var value by remember { mutableStateOf(default) }
            import androidx.compose.runtime.getValue
            import androidx.compose.runtime.setValue
        val (value, setValue) = remember { mutableStateOf(default) }
* */
@Composable
fun HelloContent() {
    Column(modifier = Modifier.padding(16.dp)) {

        /*
        *  remember 可帮助您在重组后保持状态，但不会帮助您在配置更改后保持状态。
        * 为此，您必须使用 rememberSaveable。rememberSaveable 会自动保存可保存在 Bundle 中的任何值。
        * 对于其他值，您可以将其传入自定义 Saver 对象。
        * */


        var name by remember { mutableStateOf("") }//管理状态状态
        if (name.isNotEmpty()) {
            Text(
                text = "Hello, $name!",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.h5
            )
        }
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") }
        )
    }
}
