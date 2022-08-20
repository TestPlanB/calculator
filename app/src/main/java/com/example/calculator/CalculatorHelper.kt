package com.example.calculator

import java.math.BigDecimal
import java.util.*


object CalculatorHelper {
    private val operationListChar = listOf<Char>('+', '-', '*', '%')

    // 支持浮点数运算的计算器算法
    fun parseExpression(input: String): String {
        // 找到所有的算数
        val nums = input.split("+", "-", "*", "%")
        // 找到操作符号
        val operators = input.filter {
            it in operationListChar
        }
        // 建立一个操作栈
        val stk = Stack<BigDecimal>()
        // 算数起始index
        var index = 0
        // 操作符号起始index，因为第一个操作符号默认是+，所有从-1开始
        var signIndex = -1
        var sign = '+'
        while (index < nums.count()) {
            when (sign) {
                '+' -> stk.push(nums[index].toBigDecimal())
                '-' -> stk.push(-nums[index].toBigDecimal())
                '*' -> {
                    val pre = stk.pop()
                    stk.push(pre * nums[index].toBigDecimal())
                }
                '%' -> {
                    val pre = stk.pop()
                    stk.push(pre / nums[index].toBigDecimal())
                }
            }
            // 更改index
            index += 1
            signIndex += 1
            // 保护取下一个操作符号时溢出
            if (signIndex < operators.count()) {
                sign = operators[signIndex]
            }
        }

        var res = BigDecimal(0)
        // 循环计算stk里面的数据即可
        while (!stk.empty()) {
            res += stk.peek()
            stk.pop()
        }
        return res.toString()
    }


}

