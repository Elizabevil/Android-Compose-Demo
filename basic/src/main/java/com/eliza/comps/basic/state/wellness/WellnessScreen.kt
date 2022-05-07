package com.eliza.comps.basic.state.wellness

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
/*
* 状态容器可组合，且 ViewModel 与普通状态容器的责任不同，
    * 因此屏幕级可组合项可以既包含用于提供对业务逻辑的访问权限的 ViewModel，
    * 又包含用于管理其界面逻辑和界面元素状态的状态容器。
* 由于 ViewModel 的生命周期比状态容器长，因此状态容器可以根据需要将 ViewModel 视为依赖项。
* */
@Composable
fun WellnessScreen(
    modifier: Modifier = Modifier,
    wellnessViewModel: WellnessViewModel = viewModel()
) {
    Column(modifier = modifier) {
        StatefulCounter()

        WellnessTasksList(
            list = wellnessViewModel.tasks,
            onCheckedTask = { task, checked ->
                wellnessViewModel.changeTaskChecked(task, checked)
            },
            onCloseTask = { task ->
                wellnessViewModel.remove(task)
            }
        )
    }
}
