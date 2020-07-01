data class Grid(val width: Int, val height: Int) {

    val points: MutableSet<Point> = mutableSetOf()

    init {
        if (width < 0 ) {
            throw IllegalArgumentException("width=$width must be greater or equal to zero")
        }

        if (height < 0 ) {
            throw IllegalArgumentException("height=$height must be greater or equal to zero")
        }
    }

    fun addPoint(point: Point) {
        if (point.x >= width) {
            throw IllegalArgumentException("point.x=${point.x} must be less then grid width=$width")
        }

        if (point.y >= height) {
            throw IllegalArgumentException("point.y=${point.y} must be less then grid height=$height")
        }

        points.add(point)
    }

    fun addRect(minColumn: Int, maxColumn: Int, minRow: Int, maxRow: Int) {
        for (i in minColumn..maxColumn + 1) {
            for (j in minRow..maxRow + 1) {
                addPoint(Point(i, j))
            }
        }
    }

    fun hasPoint(point: Point): Boolean {
        return points.contains(point)
    }

    override fun toString(): String {
        var result = ""
        for (row in 0..height) {
            for (col in 0..width) {
                when (hasPoint(Point(col, row))) {
                    true -> result += "■"
                    false -> result += "□"
                }

                if (col < width) {
                    result += " "
                }
            }
            result += "\n"
        }

        return result
    }

}