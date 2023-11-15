package christmas.domain

import christmas.data.Badge
import christmas.data.Courses
import christmas.data.Menu
import christmas.model.OrderMenu
import christmas.utils.startDays
import java.time.DayOfWeek
import java.time.LocalDate

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

    fun getPresentMenu(preSalePrice: Int): List<OrderMenu> {
        val list = mutableListOf<OrderMenu>()
        if (preSalePrice >= 120000) {
            list.add(OrderMenu(Menu.샴페인, 1))
        }
        return list
    }

    fun getBenefitDetails(preSalePrice: Int): Map<String, Int> {
        val resultMap = mutableMapOf<String, Int>()
        if (preSalePrice > 10000) {
            val date = LocalDate.of(2023, 12, visitDay)

            if (date.dayOfWeek == DayOfWeek.FRIDAY || date.dayOfWeek == DayOfWeek.SATURDAY) { // 이건 OCP 원칙을 꺠도 좋을 것 같다
                val mainCourseCount = orderMenus.count { it.name.courses == Courses.MAIN }
                val discount = (mainCourseCount * 2023).unaryMinus()
                resultMap["주말 할인"] = discount
            } else {
                val dessertCourseCount = orderMenus.count { it.name.courses == Courses.DESSERT }
                val discount = (dessertCourseCount * 2023).unaryMinus()
                resultMap["평일 할인"] = discount
            }

            resultMap["크리스마스 디데이 할인"] = getChristmasDDaySale(visitDay).unaryMinus()

            if (visitDay in startDays) {
                resultMap["특별 할인"] = -1000
            }

            if (preSalePrice >= 120000) {
                resultMap["증정 이벤트"] = Menu.샴페인.price.unaryMinus()
            }
        }
        return resultMap
    }

    fun getSalePrice(saleTotalPrice: Int): Int {
        val sum = orderMenus.sumOf { (it.name.price) * it.count }
        return sum - saleTotalPrice
    }

    fun getBadge(saleTotalPrice: Int): Badge? {
        if (saleTotalPrice >= 20000) {
            return Badge.SANTA
        }

        if (saleTotalPrice >= 10000) {
            return Badge.TREE
        }

        if (saleTotalPrice >= 5000) {
            return Badge.STAR
        }

        return null
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

private fun getChristmasDDaySale(visitDay: Int): Int {
    return 1000 + (visitDay - 1) * 100
}