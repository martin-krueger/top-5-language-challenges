import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class PointTest {

    @Test
    fun whenXIsNegative_thenExceptionIsThrown() {
        val ex = assertThrows(IllegalArgumentException::class.java) { Point(-1, 0) }
        assertEquals("x must be greater than 0. Current value is -1", ex.message)
    }

    @Test
    fun whenYIsNegative_thenExceptionIsThrown() {
        val ex = assertThrows(IllegalArgumentException::class.java) { Point(0, -1) }
        assertEquals("y must be greater than 0. Current value is -1", ex.message)
    }

    @Test
    fun whenXAndYArePositive_thenPointCanBeCreated_AndGetterReturnsValues() {
        val point = Point(1, 2)

        assertEquals(1, point.x)
        assertEquals(2, point.y)
    }
}