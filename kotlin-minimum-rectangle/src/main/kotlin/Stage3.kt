/**
 * Create and print a grid.
 */
object Stage3 {

    @JvmStatic
    fun main(args: Array<String>) {
        val grid = Grid(12, 12)

        grid.addRect(4, 9, 2, 2)
        grid.addRect(2, 9, 3, 6)
        grid.addRect(7, 9, 7, 9)

        println(grid)
    }

}