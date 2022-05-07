package com.eliza.comps.basic.state.wellness

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/*
 * mutableStateOf
     而 mutableStateOf 返回的是 MutableState，可以修改其值。
     看起来是初始化那一刻用的啥值，就是啥值，无法自动更新。mutableStateOf 看起来是初始化那一刻用的啥值，就是啥值，无法自动更新。
     *
    * 虽然 remember 可帮助您在重组后保持状态，但不会帮助您在配置更改后保持状态。
        * 为此，您必须使用 rememberSaveable。rememberSaveable 会自动保存可保存在 Bundle 中的任何值。
        * 对于其他值，您可以将其传入自定义 Saver 对象。
* */

@Composable
fun StatefulCounter(modifier: Modifier = Modifier) {
    var count by rememberSaveable { mutableStateOf(0) }
    /*
    * 使用 btn 进行 action
    * */
    StatelessCounter(
        count = count,
        onIncrement = { count++ },
        modifier = modifier
    )
}

/*
* 显示 Add one 提示与 btn action
* */
@Composable
private fun StatelessCounter(count: Int, onIncrement: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
            Text("You've had $count glasses.")
        }
        Button(
            onClick = onIncrement,
            enabled = count < 10,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Add one")
        }
    }
}
