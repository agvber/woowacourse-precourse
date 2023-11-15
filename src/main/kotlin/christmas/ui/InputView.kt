package christmas.ui

import camp.nextstep.edu.missionutils.Console

class InputView {
    fun readDate(orderCallback: (String) -> Boolean) {
        println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)")
        orderCallback(orderCallback)
    }
}

private fun orderCallback(callback: (String) -> Boolean) {
    while (true) {
        val readLine = Console.readLine()
        val isBreak = callback(readLine)
        if (isBreak) {
            break
        }
    }
}