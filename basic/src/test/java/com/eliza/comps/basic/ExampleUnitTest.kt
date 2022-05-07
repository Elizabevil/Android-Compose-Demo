package com.eliza.comps.basic

import org.junit.Test

import org.junit.Assert.*
import kotlin.reflect.KProperty

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun By_isCorrect() {
        var ss = Derived(object : Base {
            override fun basePrint() {
                print("By_isCorrectBy_isCorrectBy_isCorrect")
            }
        })
        ss.basePrint()
    }

    @Test
    fun Byds() {
        var baseImp = BaseImp(100000)
        Derived(baseImp).basePrint()  //输出100
    }

    @Test
    fun ByPro() {
        var e = Example()
        println(e.p) //访问该属性 调用getValue函数
        e.p = "rururn" //调用setValue()函数
        println(e.p)
    }
}

//创建接口
interface Base {
    fun basePrint()
}

//实现此接口的被委托的类
class BaseImp(val x: Int) : Base {
    override fun basePrint() {
        print(x)
    }
}

//通过关键字by建立委托类
class Derived(b: Base) : Base by b //by子句表示，将b保存在Derived的对象实例内部，而且编译器将会生成继承自Base接口的所有方法，并将调用转发给b。

//定义包含属性委托的类
class Example {
    var p: String by Delegate()
}

//委托的类
open class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef,这里委托了${property.name} 属性"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$thisRef 的 ${property.name} 属性赋值为 $value")
    }

}
