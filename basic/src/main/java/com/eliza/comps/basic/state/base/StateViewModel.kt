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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class StateViewModel : ComponentActivity() {
    private val helloViewModel: HelloViewModel by viewModels()//初始化viewmodel,否则旋转屏幕无法保存状态
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloScreen(helloViewModel)
        }
    }

}


@Composable
private fun HelloScreen(helloViewModel: HelloViewModel = HelloViewModel()) {
    val name: String by helloViewModel.name.observeAsState("")
    val onValueChanged: (String) -> Unit = { helloViewModel.onValueChanged(it) }
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
            overflow = TextOverflow.Ellipsis
        )

        OutlinedTextField(
            value = name,
            onValueChange = onValueChange,
            label = { Text(text = "name") })
    }

}

class HelloViewModel : ViewModel() {
    private val _name = MutableLiveData<String>("")
    val name: LiveData<String> get() = _name

    fun onValueChanged(newName: String) {
        _name.value = newName
    }
}
