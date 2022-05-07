package com.eliza.comps.basic.state.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eliza.comps.library.tools.InfoTools

/*Compose 中的事件
    应用的每项输入都应表示为事件：点按、文本更改，甚至计时器或其他更新。当这些事件更改界面的状态时，ViewModel 应负责处理这些事件并更新界面状态。
    界面层绝不应更改事件处理脚本之外的状态，因为这样做可能会导致应用出现不一致和 bug。
        最好为状态和事件处理脚本 lambda 传递不可变值。此方法具有以下优势：
            提升可重用性。
            确保您的界面不会直接更改状态的值。
            避免并发问题，因为您可确保不会从其他线程修改状态。
            通常情况下，还可以降低代码的复杂性。*/

class StateViewModelActivity : ComponentActivity() {
    private val helloViewModel: HelloViewModel by viewModels()//初始化viewmodel,否则旋转屏幕无法保存状态
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloScreen(helloViewModel)
        }
    }

}

/**
 * observeAsState 用于监听 LiveData 的值，并返回一个 State，用于更新 Composable。
 *   observeAsState 的两种用法：
 *   有默认值
 *   无默认值
 *
 *   =====================
 * observeAsState 返回的 State 是不可变更的
 *       @Composable
 *        fun <T : Any?> LiveData<T>.observeAsState(): @Composable State<T?>
 * mutableStateOf
 *         而 mutableStateOf 返回的是 MutableState，可以修改其值。
 *         fun <T : Any?> mutableStateOf(value: T, policy: SnapshotMutationPolicy<T> = structuralEqualityPolicy()): MutableState<T>
 * MutableLiveData
 *          ViewModel 定义 MutableLiveData 来实现可变更的 LiveData。 这样就能在 Composable 中通过 ViewModel 对外开发的函数来修改 LiveData。
 * */
@Composable
private fun HelloScreen(helloViewModel: HelloViewModel = HelloViewModel()) {
    val name: String by helloViewModel.name.observeAsState("")
    val onValueChanged: (String) -> Unit = { str -> helloViewModel.onValueChanged(str) }
    TextAndTextField(name = name, onValueChange = onValueChanged)
}

@Composable
private fun TextAndTextField(name: String, onValueChange: (String) -> Unit) {

    Column(modifier = Modifier.padding(16.dp)) {

        Text(
            text = "Hello，$name",
            modifier = Modifier.padding(bottom = 6.dp),
            style = MaterialTheme.typography.h5,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis//省略
        )

        OutlinedTextField(
            value = name,
            onValueChange = onValueChange,
            label = { Text(text = "name") })
    }

}

/*
* 方案一(失败)：ViewModel 中定义 mutableStateOf, Composable 中定义 mutableStateOf。mutableStateOf 看起来是初始化那一刻用的啥值，就是啥值，无法自动更新。
方案二(可行)：ViewModel 中定义 MutableLiveData，Composable 中使用 observeAsState 监听 LiveData 的变化。
* */
/*
* LiveData--》MutableLiveData
* */

class HelloViewModel : ViewModel() {
    /*ViewModel 定义 MutableLiveData 来实现可变更的 LiveData。 这样就能在 Composable 中通过 ViewModel 对外开发的函数来修改 LiveData。*/
    private val _name = MutableLiveData<String>("default")

    /*仅开启 对外访问渠道 */
    val name: LiveData<String> get() = _name
    /* 通过对外函数--》 修改本地 private 属性 --》val name: 对外暴露 */

    fun onValueChanged(newName: String) {
        InfoTools.LogTools(this::class.java.name, "==onValueChanged:$newName")
        _name.value = newName
    }
}
