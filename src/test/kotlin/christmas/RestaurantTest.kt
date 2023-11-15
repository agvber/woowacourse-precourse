package christmas

import christmas.data.Badge
import christmas.domain.Restaurant
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class RestaurantTest {
    private val restaurant: Restaurant = Restaurant()

    @Test
    fun `방문일 테스트`() {
        restaurant.putVisitDay("10")
    }

    @Test
    fun `방문일 예외 테스트`() {
        assertThrows<IllegalArgumentException>(
            message = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."
        ) {
            restaurant.putVisitDay("34")
        }
    }

    @Test
    fun `주문 예외 테스트`() {
        assertThrows<IllegalArgumentException> {
            restaurant.putOrderMenus("해산물파스타-2,레드와인-1,초코케이크-a")
        }
    }

    @Test
    fun `음료만 주문할 경우에 예외처리 테스트`() {
        assertThrows<IllegalArgumentException>(
            message = "[ERROR] 음료만 주문할 수 없습니다. 다시 입력해 주세요."
        ) {
            restaurant.putOrderMenus("제로콜라-2,레드와인-1,샴페인-1")
        }
    }

    @Test
    fun `주문 항목이 20개가 넘을 경우 예외 처리 테스트`() {
        assertThrows<IllegalArgumentException>(
            message = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."
        ) {
            restaurant.putOrderMenus("해산물파스타-20,레드와인-1")
        }
    }

}