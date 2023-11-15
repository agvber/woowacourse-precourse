package christmas.domain

import christmas.data.Courses
import christmas.data.Menu
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

    fun putOrderMenus(inputOrderMenus: String) {
        try {
            val splitList = inputOrderMenus.split(",")
            orderMenus = splitList.toOrderMenu()
            checkDuplicateOrderMenu(orderMenus)
            checkMaximumOrderMenu(orderMenus)
            checkOnlyDrink(orderMenus)
        } catch (e: Exception) {
            val errorMessage = e.message ?: "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."
            throw IllegalArgumentException(errorMessage)
        }
    }

    fun getPreSalePrice(): Int {
        return orderMenus.sumOf { (it.name.price) * it.count }
    }

}

private fun checkVisitDay(day: Int) {
    if (day !in 1..31) {
        throw IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.")
    }
}

private fun List<String>.toOrderMenu(): List<OrderMenu> {
    val menus = Menu.values()
    return this.map { stringList ->
        val values = stringList.split("-")
        OrderMenu(
            name = menus.first { values[0] == it.name },
            count = values[1].toInt().also {
                if (it < 1) {
                    throw IllegalArgumentException()
                }
            }
        )
    }
}

private fun checkDuplicateOrderMenu(orderMenus: List<OrderMenu>) {
    val orderNames = orderMenus.map { it.name }
    val orderNameSet = orderNames.toSet()

    if (orderNames.size != orderNameSet.size) {
        throw IllegalArgumentException()
    }
}

private fun checkMaximumOrderMenu(orderMenus: List<OrderMenu>) {
    if (orderMenus.sumOf { it.count } > 20) {
        throw IllegalArgumentException()
    }
}

private fun checkOnlyDrink(orderMenus: List<OrderMenu>) {
    val findItem = orderMenus.find { it.name.courses != Courses.DRINK }

    if (findItem == null) {
        throw IllegalArgumentException("[ERROR] 음료만 주문할 수 없습니다. 다시 입력해 주세요.")
    }
}
