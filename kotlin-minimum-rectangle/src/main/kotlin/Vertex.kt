import kotlin.math.max
import kotlin.math.min

enum class Corner {
    /**
     * Upper left
     */
    UL,

    /**
     * Upper right
     */
    UR,

    /**
     * Down left
     */
    DL,

    /**
     * Down right
     */
    DR
}

/**
 * A vertex in the grid.
 * It is assumed that the vertex is concave.
 */
data class Vertex(val x: Int, val y: Int, val corner: Corner) {
}

/**
 * An edge connecting two concave vertices in the grid.
 * It is assumed that the edge is either horizontal or vertical.
 */
data class Edge(val id: Int, val vertex1: Vertex, val vertex2: Vertex) {

    fun intersect(other: Edge): Boolean {
        val minX = min(vertex1.x, vertex2.x)
        val maxX = max(vertex1.x, vertex2.x)
        val minY = min(vertex1.y, vertex2.y)
        val maxY = max(vertex1.y, vertex2.y)
        val otherMinX = min(other.vertex1.x, other.vertex2.x)
        val otherMaxX = max(other.vertex1.x, other.vertex2.x)
        val otherMinY = min(other.vertex1.y, other.vertex2.y)
        val otherMaxY = max(other.vertex1.y, other.vertex2.y)

        return vertex1.x == vertex2.x && minY <= other.vertex1.y && other.vertex1.y <= maxY && otherMinX <= vertex1.x && vertex1.x <= otherMaxX
                || vertex1.y == vertex2.y && minX <= other.vertex1.x && other.vertex1.x <= maxX && otherMinY <= vertex1.y && vertex1.y <= otherMaxY
    }

}