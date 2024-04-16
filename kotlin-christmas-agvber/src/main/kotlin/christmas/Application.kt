package christmas

import christmas.domain.Restaurant
import christmas.ui.InputView
import christmas.ui.OutputView

private val inputView = InputView()
private val outputView = OutputView()
private val restaurant = Restaurant()

fun main() {
    outputView.printWelcome()

    inputView.readDate { day ->
        runCatching { restaurant.putVisitDay(day) }.asResult()
    }

    inputView.readOrderMenu { menus ->
        runCatching { restaurant.putOrderMenus(menus) }.asResult()
    }

    outputView.printEventPreviewTitle(restaurant.getVisitDay())
    outputView.printMenu(restaurant.getOrderMenus())

    val preSalePrice = restaurant.getPreSalePrice()
    outputView.printTotalPrePrice(preSalePrice)

    val presentMenu = restaurant.getPresentMenu(preSalePrice)
    outputView.printPresentMenu(presentMenu)

    val benefitDetails = restaurant.getBenefitDetails(preSalePrice)
    outputView.printBenefitDetails(benefitDetails)

    val totalBenefitDetails = benefitDetails.values.sum()
    outputView.printDiscountAmount(totalBenefitDetails)

    val totalSalePrice = restaurant.getSalePrice(totalBenefitDetails)
    outputView.printSalePriceAmount(totalSalePrice)

    val badge = restaurant.getBadge(totalSalePrice)
    outputView.printEventBadge(badge)
}

private fun<T> Result<T>.asResult(): Boolean {
    onSuccess { return true }
    onFailure { outputView.printErrorLog(it) }
    return false
}