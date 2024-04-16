package christmas.ui

import christmas.data.Badge
import christmas.model.OrderMenu

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

    fun printMenu(orderMens: List<OrderMenu>) {
        printlnTitle("<주문 메뉴>") {
            orderMens.forEach { println("${it.name} ${it.count}개") }
        }
    }

    fun printTotalPrePrice(money: Int) {
        printlnTitle("<할인 전 총주문 금액>") {
            println(money.toMoneyFormatString())
        }
    }

    fun printPresentMenu(orderMens: List<OrderMenu>) {
        printlnTitle("<증정 메뉴>") {
            if (orderMens.isEmpty()) {
                println("없음\n")
                return@printlnTitle
            }
            orderMens.forEach { println("${it.name} ${it.count}개") }
        }
    }

    fun printBenefitDetails(map: Map<String, Int>) {
        printlnTitle("<혜택 내역>") {
            if (map.isEmpty()) {
                println("없음\n")
                return@printlnTitle
            }

            map.forEach { (description, price) ->
                println("$description: ${price.toMoneyFormatString()}")
            }
        }
    }

    fun printDiscountAmount(money: Int) {
        printlnTitle("<총혜택 금액>") {
            println(money.toMoneyFormatString())
        }
    }

    fun printSalePriceAmount(money: Int) {
        printlnTitle("<할인 후 예상 결제 금액>") {
            println(money.toMoneyFormatString())
        }
    }

    fun printEventBadge(badge: Badge?) {
        printlnTitle("<12월 이벤트 배지>") {
            println(badge?.string ?: "없음")
        }
    }
}

private fun printlnTitle(title: Any?, content: () -> Unit) {
    println(title)
    content()
    println()
}

private fun Int.toMoneyFormatString() = "${"%,d".format(this)}원"