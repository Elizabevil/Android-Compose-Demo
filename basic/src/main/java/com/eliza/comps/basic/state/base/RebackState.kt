package com.eliza.comps.basic.state.base

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


/*
* 在 Compose 中恢复状态
    在重新创建 activity 或进程后，您可以使用 rememberSaveable 恢复界面状态。
* rememberSaveable 可以在重组后保持状态。此外，rememberSaveable 也可以在重新创建 activity 和进程后保持状态。
* */
/** 存储状态的方式
 * 添加到 Bundle 的所有数据类型都会自动保存。如果要保存无法添加到 Bundle 的内容，您有以下几种选择。
 *    Parcelize
 *        最简单的解决方案是向对象添加 @Parcelize 注解。对象将变为可打包状态并且可以捆绑。
 * */
/**
 * 例如，以下代码会创建可打包的 City 数据类型并将其保存到状态。
 */
@Parcelize
data class City(val name: String, val country: String) : Parcelable

@Composable
private fun CityScreenParcelize() {
    var selectedCity = rememberSaveable {
        mutableStateOf(City("Madrid", "Spain"))
    }
}

@Preview
@Composable
private fun CityScreenParcelizePreview() {
    CityScreenParcelize()
}

/**
 * MapSaver
如果某种原因导致 @Parcelize 不合适，您可以使用 mapSaver 定义自己的规则，规定如何将对象转换为系统可保存到 Bundle 的一组值。
 */

private val CitySaverMapSaver = run {
    val nameKey = "Name"
    val countryKey = "Country"
    mapSaver(
        save = { mapOf(nameKey to it.name, countryKey to it.country) },
        restore = { City(it[nameKey] as String, it[countryKey] as String) }
    )
}

@Composable
private fun CityScreenMapSaver() {
    var selectedCity = rememberSaveable(stateSaver = CitySaverMapSaver) {
        mutableStateOf(City("Madrid", "Spain"))
    }
}

@Preview
@Composable
private fun CityScreenMapSaverPreview() {
    CityScreenMapSaver()
}

/**
 * ListSaver
为了避免需要为映射定义键，您也可以使用 listSaver 并将其索引用作键：
 */


private val ListSaver = listSaver<City, Any>(
    save = { listOf(it.name, it.country) },
    restore = { City(it[0] as String, it[1] as String) }
)

@Composable
private fun CityScreenListSaver() {
    var selectedCity = rememberSaveable(stateSaver = ListSaver) {
        mutableStateOf(City("Madrid", "Spain"))
    }
}

@Preview
@Composable
private fun CityScreenListSaverPreview() {
    CityScreenListSaver()
}