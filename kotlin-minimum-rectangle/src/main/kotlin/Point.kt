/**
 * A point with x and y coordinate.
 */
data class Point(val x: Int = 0, val y: Int = 0) {

    init {
        if (x < 0) {
            throw IllegalArgumentException("x must be greater than 0. Current value is $x")
        }

        if (y < 0) {
            throw IllegalArgumentException("y must be greater than 0. Current value is $y")
        }
    }

}
