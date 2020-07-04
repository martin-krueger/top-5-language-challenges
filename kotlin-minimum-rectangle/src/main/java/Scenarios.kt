data class Scenario(val grid: Grid, val expectedMinRectangles: Int)

/**
 * Creates different scenarios from stage 4.
 */
object Scenarios {

    fun scenario1(): Scenario {
        val grid = Grid(4, 4)

        grid.addPoint(Point(1, 2))

        return Scenario(grid, 1)
    }

    fun scenario2(): Scenario {
        val grid = Grid(4, 4)

        grid.addPoint(Point(0, 2))
        grid.addPoint(Point(1, 2))
        grid.addPoint(Point(0, 3))
        grid.addPoint(Point(1, 3))

        return Scenario(grid, 1)
    }

    fun scenario3(): Scenario {
        val grid = Grid(2, 3)

        grid.addPoint(Point(0, 0))
        grid.addPoint(Point(1, 0))
        grid.addPoint(Point(0, 1))
        grid.addPoint(Point(0, 2))
        grid.addPoint(Point(1, 2))

        return Scenario(grid, 3)
    }

    fun scenario4(): Scenario {
        val grid = Grid(2, 3)

        grid.addPoint(Point(0, 0))
        grid.addPoint(Point(0, 1))
        grid.addPoint(Point(1, 1))
        grid.addPoint(Point(0, 2))

        return Scenario(grid, 2)
    }

    fun scenario5(): Scenario {
        val grid = Grid(4, 4)

        grid.addPoint(Point(2, 0))
        grid.addPoint(Point(1, 1))
        grid.addPoint(Point(2, 1))
        grid.addPoint(Point(0, 2))
        grid.addPoint(Point(1, 2))
        grid.addPoint(Point(2, 2))
        grid.addPoint(Point(3, 2))
        grid.addPoint(Point(2, 3))

        return Scenario(grid, 4)
    }

    fun scenario6(): Scenario {
        val grid = Grid(5, 5)

        grid.addPoint(Point(3, 0))
        grid.addPoint(Point(4, 0))
        grid.addPoint(Point(2, 1))
        grid.addPoint(Point(3, 1))
        grid.addPoint(Point(4, 1))
        grid.addPoint(Point(0, 2))
        grid.addPoint(Point(1, 2))
        grid.addPoint(Point(2, 2))
        grid.addPoint(Point(3, 2))
        grid.addPoint(Point(4, 2))
        grid.addPoint(Point(1, 3))
        grid.addPoint(Point(2, 3))
        grid.addPoint(Point(3, 3))
        grid.addPoint(Point(2, 4))

        return Scenario(grid, 5)
    }

    fun scenario7(): Scenario {
        val grid = Grid(20, 20)

        grid.addRect(2, 4, 1, 5)
        grid.addRect(10, 16, 1, 2)
        grid.addRect(5, 13, 3, 14)
        grid.addRect(3, 4, 10, 14)
        grid.addRect(5, 9, 15, 18)
        grid.addRect(10, 12, 15, 16)
        grid.addRect(14, 15, 3, 3)
        grid.addRect(14, 16, 6, 14)
        grid.addRect(17, 17, 6, 9)
        grid.addRect(16, 16, 15, 16)

        return Scenario(grid, 9)
    }

}