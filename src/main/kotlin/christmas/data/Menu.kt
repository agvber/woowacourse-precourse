package christmas.data

enum class Menu(val courses: Courses, val price: Int) {
    양송이수프(Courses.APPETIZER, 6000),
    타파스(Courses.APPETIZER, 5500),
    시저샐러드(Courses.APPETIZER, 8000),

    티본스테이크(Courses.MAIN, 55000),
    바비큐립(Courses.MAIN, 54000),
    해산물파스타(Courses.MAIN, 35000),
    크리스마스파스타(Courses.MAIN, 25000),

    초코케이크(Courses.DESSERT, 15000),
    아이스크림(Courses.DESSERT, 5000),

    제로콜라(Courses.DRINK, 3000),
    레드와인(Courses.DRINK, 60000),
    샴페인(Courses.DRINK, 25000)
}

enum class Courses {
    APPETIZER, MAIN, DESSERT, DRINK,
}