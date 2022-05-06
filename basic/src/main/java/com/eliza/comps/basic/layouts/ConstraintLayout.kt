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

@Composable
fun DecoupledConstraintLayout() {
    BoxWithConstraints {
        val constraints = if (maxWidth < maxHeight) {
            decoupledConstraints(margin = 16.dp) // Portrait constraints
        } else {
            decoupledConstraints(margin = 32.dp) // Landscape constraints
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
文字换行
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
                /*
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
