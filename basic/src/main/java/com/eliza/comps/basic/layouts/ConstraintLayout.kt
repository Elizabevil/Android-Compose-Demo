package com.eliza.comps.basic.layouts

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.eliza.comps.basic.ui.theme.AskcTheme

//ConstraintLayout 默认尺寸是 wrap_content
/*
*
        引用是使用createRefs()或createRefFor()创建的，ConstraintLayout 中的每个可组合项都需要有与之关联的引用。
        约束条件是使用constrainAs()修饰符提供的，该修饰符将引用作为参数，可让您在主体lambda中指定其约束条件。
        约束条件是使用linkTo() 或其他有用的方法指定的。
        parent是一个现有的引用，可用于指定对ConstraintLayout可组合项本身的约束条件。
* */
/**
 * Constraint layout content
 * 最简单相对布局
 */
@Composable
fun ConstraintLayoutContent() {
    ConstraintLayout {

//        引用是使用createRefs()或createRefFor()创建的，ConstraintLayout 中的每个可组合项都需要有与之关联的引用。

        val (button, text) = createRefs()

        Button(
            onClick = { /* Do something */ },
            //   约束条件是使用constrainAs()修饰符提供的，该修饰符将引用作为 第一个  参数
            //在lambda表达式中指定约束规则
            modifier = Modifier.constrainAs(button) {
//                约束条件是使用linkTo() 或其他有用的方法指定的
                top.linkTo(parent.top, margin = 1.dp)
            }
        ) {
            Text("Button")
        }

        Text("Text", Modifier.constrainAs(text) {
            top.linkTo(button.bottom, margin = 16.dp)
            centerHorizontallyTo(parent)
        })
    }
}

/**
 * Decoupled constraint layout
 * 解耦合--》组件约束关系 在外面定义
 */
@Composable
fun DecoupledConstraintLayout() {
    BoxWithConstraints {
        val constraints = if (maxWidth < maxHeight) {
            decoupledConstraints(margin = 1.dp) // 纵向 竖屏Portrait constraints
        } else {
            decoupledConstraints(margin = 200.dp) // Landscape constraints
        }

        ConstraintLayout(constraints) {
            Button(
                onClick = { /* Do something */ },
                modifier = Modifier.layoutId("button")
            ) {
                Text("Button")
            }

            Text("Text", Modifier.layoutId("text"))
        }
    }
}

/**
 * Decoupled constraints
 * 声明函数，通过传递 参数 实现动态布局
 * @param margin
 * @return
 */
private fun decoupledConstraints(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val button = createRefFor("button")
        val text = createRefFor("text")

        constrain(button) {
            top.linkTo(parent.top, margin = margin)
        }
        constrain(text) {
            top.linkTo(button.bottom, margin)
        }
    }
}

@Composable
fun ConstraintLayoutContentExample2() {
    ConstraintLayout {
        val (button1, button2, text) = createRefs()

        Button(
            onClick = { /* Do something */ },
            /*
            * button1 在父布局的上方，margin= *.dp
            * */
            modifier = Modifier.constrainAs(button1) {

                top.linkTo(parent.top, margin = 1.dp)
            }
        ) {
            Text("Button 1")
        }
        /*
            button1 的下方
            * */
        Text("Text", Modifier.constrainAs(text) {
            top.linkTo(button1.bottom, margin = 16.dp)
            centerAround(button1.end)
        })

        /**
         * Barrier 给一些子元素设置栅栏，将栅栏两侧的子元素分隔开的作用
         * 创建栅栏的函数不仅有 createEndBarrier() 方法，类似用法的总结起来有：
        createTopBarrier()、createBottomBarrier() : 创建分隔上下组件的栅栏；
        createStartBarrier()、createEndBarrier() : 创建分隔左右组件的栅栏；
        createAbsoluteLeftBarrier()、createAbsoluteRightBarrier() : 创建分隔左右组件的栅栏，满足国际化的需求。
         */
        //将button1 和text组合起来，建立一个屏障 (barrier)
        val barrier = createEndBarrier(button1, text)
        Button(
            onClick = { /* Do something */ },
            modifier = Modifier.constrainAs(button2) {
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(barrier)
            }
        ) {
            Text("Button 2")
        }
    }
}

/**
文字换行：当它的子元素过大时，ConstraintLayout 默认是可以允许子元素超出屏幕范围的
Guideline
createGuidelineFromStart(offset: Dp)：根据左侧距离父布局偏移量来设置 guideline 位置
createGuidelineFromStart(fraction: Float)：根据左侧距离父布局的百分比来设置 guideline 位置
createGuidelineFromAbsoluteLeft(offset: Dp)：国际化才使用
createGuidelineFromAbsoluteLeft(fraction: Float)
createGuidelineFromEnd(offset: Dp)
createGuidelineFromEnd(fraction: Float)
createGuidelineFromAbsoluteRight(offset: Dp)
createGuidelineFromAbsoluteRight(fraction: Float)
createGuidelineFromTop(offset: Dp)
createGuidelineFromTop(fraction: Float)
createGuidelineFromBottom(offset: Dp)
createGuidelineFromBottom(fraction: Float)
看着挺多，其实就是上下左右加上国际化的情况。
 * */
/**
Dimension 的属性一共有五种：
1. preferredWrapContent：布局大小是根据内容所设置，并受布局约束的影响。这个例子中对 Text 右边界做了限制，所以使用这个属性可以控制 Text 右边界只能到达父布局右边界，不能超出屏幕；
2. wrapContent：Dimension 的默认值，即布局大小只根据内容所设置，不受约束；
3. fillToConstraints：布局大小将展开填充由布局约束所限制的空间。也就是说，这个属性是先看看布局约束所限制的空间有多大，然后再将该子元素填充到这个有约束的空间中；
4. preferredValue：布局大小是一个固定值，并受布局约束的影响； 5. value：布局大小是一个固定值，不受约束。
此外，Dimension 还可组合设置布局大小，例如：width = Dimension.preferredWrapContent.atLeast(100.dp) 可设置最小布局大小，同样还有 atMost() 可设置最大布局大小等等。
 * */
@Composable
fun LargeConstraintLayout() {
    ConstraintLayout {
        val text = createRef()

        val guideline = createGuidelineFromStart(fraction = 0.5f)
        Text(
            "This is a very very very very very very very long text",
            Modifier.constrainAs(text) {
                linkTo(start = guideline, end = parent.end)
                /**
                 *
                文字换行
                 * */
                width = Dimension.preferredWrapContent
            }
        )
    }
}

@Preview
@Composable
fun ConstraintLayoutContentPreview() {
    AskcTheme {
        ConstraintLayoutContent()
    }
}

@Preview
@Composable
fun ConstraintLayoutContentExample2Preview() {
    AskcTheme {
        ConstraintLayoutContentExample2()
    }
}

@Preview
@Composable
fun LargeConstraintLayoutPreview() {
    AskcTheme {
        LargeConstraintLayout()
    }
}


@Preview
@Composable
fun DecoupledConstraintLayoutPreview() {
    AskcTheme {
        DecoupledConstraintLayout()
    }
}
