package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.calculator.ui.theme.CalculatorTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            MaterialTheme {
                // A surface container using the 'background' color from the theme

                Scaffold(modifier = Modifier.fillMaxSize()) {
                    CalculatorWidget()
                }

            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun CalculatorWidget() {

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = Color(252, 213, 144)
    )
    val symbol = listOf<String>("+", "-", "*", "%", ".")
    val zero = listOf<String>("0", "00")
    val items = listOf(
        "C",
        "%",
        "x",
        "%",
        "7",
        "8",
        "9",
        "*",
        "4",
        "5",
        "6",
        "-",
        "1",
        "2",
        "3",
        "+",
        "00",
        "0",
        ".",
        "="
    )


    Column(
        verticalArrangement = Arrangement.Bottom, modifier = Modifier
            .background(Color(251, 214, 144))
            .fillMaxHeight()
            .padding(vertical = 30.dp)
    ) {
        val currentText = remember {
            mutableStateOf("")
        }
        Screen(sum = currentText.value)
        CalculatorItem(
            items
        ) {
            // 重复符号判断
            if ((it in (symbol) || it in zero) && currentText.value.isNotEmpty()) {
                // 0开头运算符
                if (it == "00") {
                    currentText.value = "0"
                }
                // 处理0开头的情况
                if (currentText.value.first() == '0' && currentText.value.count() == 1 && (it == "00" || it == "0")) {
                    return@CalculatorItem
                }
                // 处理运算符互斥
                if (currentText.value.last().toString() == it || (currentText.value.last()
                        .toString() in symbol && it in symbol)
                ) {
                    return@CalculatorItem
                }
            }
            // 处理功能性运算符
            when (it) {
                "=" -> {
                    currentText.value = CalculatorHelper.parseExpression(currentText.value)
                }
                "C" -> {
                    currentText.value = ""
                }
                "x" -> {
                    currentText.value =
                        if (currentText.value.count() - 1 >= 0) currentText.value.dropLast(
                            1
                        ) else ""
                }
                else -> {
                    currentText.value = currentText.value + it
                }
            }


        }

        Text(
            text = "power by pika",
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }

}