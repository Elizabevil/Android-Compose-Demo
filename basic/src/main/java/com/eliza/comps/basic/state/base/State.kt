package com.eliza.comps.basic.state.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/*
https://developer.android.google.cn/jetpack/compose/state?hl=zh-cn


mutableStateOf 会创建可观察的 MutableState<T>，后者是与 Compose 运行时集成的可观察类型。
    interface MutableState<T> : State<T> {
        override var value: T
    }
        value 如有任何更改，系统会安排重组读取 value 的所有可组合函数。对于 ExpandingCard，每当 expanded 发生变化时，都会导致重组 ExpandingCard。
* Remember中文是记住，它的作用也类似，它会存储变量第一次赋值的结果，之后如果使用该变量时会去获取记录的值，之后如果值没有变化也就不会再次发生重组
        val mutableState = remember { mutableStateOf(default) }
        var value by remember { mutableStateOf(default) }
                import androidx.compose.runtime.getValue
                import androidx.compose.runtime.setValue
        val (value, setValue) = remember { mutableStateOf(default) }

* */
/**
 * Hello content
 *  由于 Compose 是声明式工具集，因此更新它的唯一方法是通过新参数调用同一可组合项。
 *  这些参数是界面状态的表现形式。每当状态更新时，都会发生重组。
 */
@Composable
private fun HelloContent() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Hello!",
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.h5
        )
        OutlinedTextField(
            value = "",
            onValueChange = { },
            label = { Text("Name") }
        )
    }
}

@Preview
@Composable
fun asd() {
//    HelloContent()
    HelloContent2()
}

/*
* 虽然 remember 可帮助您在重组后保持状态，但不会帮助您在配置更改后保持状态。
* 为此，您必须使用 rememberSaveable。rememberSaveable 会自动保存可保存在 Bundle 中的任何值。
* 对于其他值，您可以将其传入自定义 Saver 对象。
* */
@Composable
private fun HelloContent2() {
    Column(modifier = Modifier.padding(16.dp)) {
        /* by 委托
        *           import androidx.compose.runtime.getValue
                    import androidx.compose.runtime.setValue
        * */
        var name by remember { mutableStateOf("") }
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
/**
 * 如果您在 Compose 中使用 LiveData 等其他可观察类型，应该先将其转换为 State<T>，
 * 然后再使用诸如 LiveData<T>.observeAsState() 之类的可组合扩展函数在可组合项中读取它。
 *
 * 在 Compose 中将可变对象（如 ArrayList<T> 或 mutableListOf()）用作状态会导致用户在您的应用中看到不正确或陈旧的数据。
不可观察的可变对象（如 ArrayList<T> 或可变数据类）不能由 Compose 观察，因而 Compose 不能在它们发生变化时触发重组。
我们建议您使用可观察的数据存储器（如 State<List<T>>）和不可变的 listOf()，而不是使用不可观察的可变对象。
 */
/*

除了``MutableState来存储状态，我们也可以使用我们熟悉的LiveData,Flow,RxJava`。

observeAsState函数是LiveData的扩展函数，将LiveData对象转化成State对象。
    val liveData= MutableLiveData<String>()
    val text by liveData.observeAsState()
* Flow
    * val value: String by stateFlow.collectAsState()
* RxJava
    val completed by completable.subscribeAsState()

*Compose通过扩展函数，将已有框架的可观察类转化成State<T>对象，并由可组合函数读取，
可以通过扩展函数将自己的可观察类转化成State<T>对象。
    如果需要手动触发重组，例如在从服务器获取数据的情况下，通过使用currentRecomposeScope.invalidate()。
* */