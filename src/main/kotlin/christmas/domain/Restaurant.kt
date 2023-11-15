package christmas.domain

import christmas.model.OrderMenu

class Restaurant {

    private var visitDay: Int = -1
    private var orderMenus: List<OrderMenu> = listOf()

    fun getVisitDay() = visitDay
    fun getOrderMenus() = orderMenus

    fun putVisitDay(inputDay: String) {
        try {
            visitDay = inputDay.toInt()
            checkVisitDay(visitDay)
        } catch (e: Exception) {
            throw IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.")
        }
    }

}

private fun checkVisitDay(day: Int) {
    if (day !in 1..31) {
        throw IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.")
    }
}
