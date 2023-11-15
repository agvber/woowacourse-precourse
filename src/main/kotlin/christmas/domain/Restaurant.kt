package christmas.domain

import christmas.model.OrderMenu

class Restaurant {

    private var visitDay: Int = -1
    private var orderMenus: List<OrderMenu> = listOf()

    fun getVisitDay() = visitDay
    fun getOrderMenus() = orderMenus

}