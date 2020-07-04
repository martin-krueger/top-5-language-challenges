data class Grid(val width: Int, val height: Int) {

    val points: MutableSet<Point> = mutableSetOf()
    val plan: Array<Array<Boolean>>

    init {
        if (width < 0 ) {
            throw IllegalArgumentException("width=$width must be greater or equal to zero")
        }

        if (height < 0 ) {
            throw IllegalArgumentException("height=$height must be greater or equal to zero")
        }

        plan = Array(width) { Array(height) { false } }
    }

    /**
     * Adds the given point.
     */
    fun addPoint(point: Point) {
        if (point.x >= width) {
            throw IllegalArgumentException("point.x=${point.x} must be less then grid width=$width")
        }

        if (point.y >= height) {
            throw IllegalArgumentException("point.y=${point.y} must be less then grid height=$height")
        }

        plan[point.x][point.y] = true
        points.add(point)
    }

    /**
     * Add all points from the given rectangle.
     */
    fun addRect(minColumn: Int, maxColumn: Int, minRow: Int, maxRow: Int) {
        for (i in minColumn..maxColumn) {
            for (j in minRow..maxRow) {
                addPoint(Point(i, j))
            }
        }
    }

    /**
     * Returns true of the given point is in the grid, otherwise false.
     */
    fun hasPoint(point: Point): Boolean {
        return hasPoint(point.x, point.y)
    }

    /**
     * Returns true if the given coordinate is in the grid, otherwise false.
     */
    fun hasPoint(x: Int, y: Int): Boolean {
        return plan[x][y]
    }

    /**
     * Returns a string representation of the grid.
     */
    override fun toString(): String {
        var result = ""
        for (row in 0 until height) {
            for (col in 0 until width) {
                when (plan[col][row]) {
                    true -> result += "■"
                    false -> result += "□"
                }

                if (col < width - 1) {
                    result += " "
                }
            }
            result += "\n"
        }

        return result
    }

}