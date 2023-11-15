package christmas

import christmas.domain.Restaurant
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
}