package christmas.ui

class OutputView {

    fun printWelcome() {
        println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.")
    }

    fun printErrorLog(t: Throwable) {
        println(t.message ?: "[ERROR] 알 수 없는 에러가 발생 하였습니다.")
    }

    fun printEventPreviewTitle(visitDay: Int) {
        println("12월 ${visitDay}일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n")
    }
}