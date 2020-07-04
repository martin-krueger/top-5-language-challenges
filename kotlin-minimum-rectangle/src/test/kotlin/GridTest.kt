import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class GridTest {

    @Test
    fun whenWidthIsLessThanZero_thenAnExceptionIsThrown() {
        val ex = assertFailsWith(IllegalArgumentException::class) { Grid(-1, 0) }
        assertEquals("width=-1 must be greater or equal to zero", ex.message)
    }

    @Test
    fun whenHeightIsLessThanZero_thenAnExceptionIsThrown() {
        val ex = assertFailsWith(IllegalArgumentException::class) { Grid(0, -1) }
        assertEquals("height=-1 must be greater or equal to zero", ex.message)
    }

    @Test
    fun whenAddedPointXIsGreaterThanWidth_thenAnExceptionIsThrown() {
        val grid = Grid(1, 1)
        val ex = assertFailsWith(IllegalArgumentException::class) { grid.addPoint(Point(1, 0)) }
        assertEquals("point.x=1 must be less then grid width=1", ex.message)
    }

    @Test
    fun whenAddedPointYIsGreaterThanWidth_thenAnExceptionIsThrown() {
        val grid = Grid(1, 1)
        val ex = assertFailsWith(IllegalArgumentException::class) { grid.addPoint(Point(0, 1)) }
        assertEquals("point.y=1 must be less then grid height=1", ex.message)
    }

    @Test
    fun whenPointIsAdded_thenHasPointReturnsTrue() {
        val grid = Grid(2, 2)
        grid.addPoint(Point(0, 1))

        assertTrue { grid.hasPoint(Point(0, 1)) }
    }

    @Test
    fun whenPointIsNotAdded_thenHasPointReturnsFalse() {
        val grid = Grid(2, 2)

        assertFalse { grid.hasPoint(Point(0, 0)) }
    }

    @Test
    fun whenPointsAreAdded_toStringReturnsGridRepresentation() {
        val grid = Grid(12, 12)

        grid.addRect(4, 9, 2, 2)
        grid.addRect(2, 9, 3, 6)
        grid.addRect(7, 9, 7, 9)

        assertEquals(
            "□ □ □ □ □ □ □ □ □ □ □ □ □\n" +
                "□ □ □ □ □ □ □ □ □ □ □ □ □\n" +
                "□ □ □ □ ■ ■ ■ ■ ■ ■ ■ □ □\n" +
                "□ □ ■ ■ ■ ■ ■ ■ ■ ■ ■ □ □\n" +
                "□ □ ■ ■ ■ ■ ■ ■ ■ ■ ■ □ □\n" +
                "□ □ ■ ■ ■ ■ ■ ■ ■ ■ ■ □ □\n" +
                "□ □ ■ ■ ■ ■ ■ ■ ■ ■ ■ □ □\n" +
                "□ □ ■ ■ ■ ■ ■ ■ ■ ■ ■ □ □\n" +
                "□ □ □ □ □ □ □ ■ ■ ■ ■ □ □\n" +
                "□ □ □ □ □ □ □ ■ ■ ■ ■ □ □\n" +
                "□ □ □ □ □ □ □ ■ ■ ■ ■ □ □\n" +
                "□ □ □ □ □ □ □ □ □ □ □ □ □\n" +
                "□ □ □ □ □ □ □ □ □ □ □ □ □\n", grid.toString())
    }

}
