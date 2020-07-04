import org.jgrapht.alg.matching.HopcroftKarpMaximumCardinalityBipartiteMatching
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.DefaultUndirectedGraph
import kotlin.math.max
import kotlin.math.min

/**
 * Calculates the minimum number of rectangles the given grid can be decomposed of.
 */
class Algorithm(val grid: Grid) {

    var id = 0

    fun minRectangles(): Int {
        val convexVertices = countConvexVertices()
        val concaveVertices = findConcaveVertices()

        val verticals = findVerticalEdges(concaveVertices)
        val horizontals = findHorizontalEdges(concaveVertices)

        val graph = buildIntersectionGraph(verticals, horizontals)

        if (graph.edgeSet().isEmpty()) {
            return (convexVertices + concaveVertices.size) / 2 - verticals.size - horizontals.size - 1
        }

        val verticalIds = verticals.map { edge -> edge.id }.toSet()
        val horizontalIds = horizontals.map { edge -> edge.id }.toSet()
        val maxMatching =
            HopcroftKarpMaximumCardinalityBipartiteMatching<Int, DefaultEdge>(graph, horizontalIds, verticalIds)

        return (convexVertices + concaveVertices.size) / 2 - maxMatching.matching.edges.size - 1
    }

    fun findConcaveVertices(): Set<Vertex> {
        val vertices = mutableSetOf<Vertex>()

        for (point in this.grid.points) {
            addConcaveVertex(point, vertices)
        }

        return vertices
    }

    fun countConvexVertices(): Int {
        var convexVerticesCount = 0

        for (point in this.grid.points) {
            convexVerticesCount += countConvexVertices(point)
        }

        return convexVerticesCount
    }

    fun addConcaveVertex(point: Point, vertices: MutableSet<Vertex>) {
        val top = point.y == 0
        val bottom = point.y == grid.height - 1
        val left = point.x == 0
        val right = point.x == grid.width - 1

        if (!top && !left
            && this.grid.hasPoint(point.x, point.y - 1)
            && this.grid.hasPoint(point.x - 1, point.y)
            && !this.grid.hasPoint(point.x - 1, point.y - 1)
        ) {
            vertices.add(Vertex(point.x, point.y, Corner.UL))
        }

        if (!top && !right
            && this.grid.hasPoint(point.x, point.y - 1)
            && this.grid.hasPoint(point.x + 1, point.y)
            && !this.grid.hasPoint(point.x + 1, point.y - 1)
        ) {
            vertices.add(Vertex(point.x + 1, point.y, Corner.UR))
        }

        if (!left && !bottom
            && this.grid.hasPoint(point.x, point.y + 1)
            && this.grid.hasPoint(point.x - 1, point.y)
            && !this.grid.hasPoint(point.x - 1, point.y + 1)
        ) {
            vertices.add(Vertex(point.x, point.y + 1, Corner.DL))
        }

        if (!right && !bottom
            && this.grid.hasPoint(point.x, point.y + 1)
            && this.grid.hasPoint(point.x + 1, point.y)
            && !this.grid.hasPoint(point.x + 1, point.y + 1)
        ) {
            vertices.add(Vertex(point.x + 1, point.y + 1, Corner.DR))
        }

        return
    }

    fun countConvexVertices(point: Point): Int {
        var count = 0

        val top = point.y == 0
        val bottom = point.y == grid.height - 1
        val left = point.x == 0
        val right = point.x == grid.width - 1

        if ((top || !this.grid.hasPoint(point.x, point.y - 1))
            && (left || !this.grid.hasPoint(point.x - 1, point.y))
            && (top || left || !this.grid.hasPoint(point.x - 1, point.y - 1))
        ) {
            count++
        }

        if ((top || !this.grid.hasPoint(point.x, point.y - 1))
            && (right || !this.grid.hasPoint(point.x + 1, point.y))
            && (top || right || !this.grid.hasPoint(point.x + 1, point.y - 1))
        ) {
            count++
        }

        if ((bottom || !this.grid.hasPoint(point.x, point.y + 1))
            && (left || !this.grid.hasPoint(point.x - 1, point.y))
            && (left || bottom || !this.grid.hasPoint(point.x - 1, point.y + 1))
        ) {
            count++
        }

        if ((bottom || !this.grid.hasPoint(point.x, point.y + 1))
            && (right || !this.grid.hasPoint(point.x + 1, point.y))
            && (bottom || right || !this.grid.hasPoint(point.x + 1, point.y + 1))
        ) {
            count++
        }

        return count
    }

    fun findVerticalEdges(vertices: Set<Vertex>): Set<Edge> {
        val edges = mutableSetOf<Edge>()

        for ((i, vertex) in vertices.withIndex()) {
            for ((j, other) in vertices.withIndex()) {
                if (j <= i) {
                    continue
                }

                if (isVerticalConnected(vertex, other)) {
                    edges.add(Edge(id(), vertex, other))
                }
            }
        }

        return edges
    }


    fun isVerticalConnected(vertex: Vertex, other: Vertex): Boolean {
        if (vertex == other || vertex.x != other.x) {
            return false
        }

        val minY = min(vertex.y, other.y)
        val maxY = max(vertex.y, other.y)

        for (y in minY until maxY) {
            if (!(this.grid.hasPoint(vertex.x, y) && this.grid.hasPoint(vertex.x - 1, y))) {
                return false
            }
        }

        return true
    }

    fun findHorizontalEdges(vertices: Set<Vertex>): Set<Edge> {
        val edges = mutableSetOf<Edge>()

        for ((i, vertex) in vertices.withIndex()) {
            for ((j, other) in vertices.withIndex()) {
                if (j <= i) {
                    continue
                }

                if (isHorizontalConnected(vertex, other)) {
                    edges.add(Edge(id(), vertex, other))
                }
            }
        }

        return edges
    }

    fun isHorizontalConnected(vertex: Vertex, other: Vertex): Boolean {
        if (vertex == other || vertex.y != other.y) {
            return false
        }

        val minX = min(vertex.x, other.x)
        val maxX = max(vertex.x, other.x)

        for (x in minX until maxX) {
            if (!(this.grid.hasPoint(x, vertex.y) && this.grid.hasPoint(x, vertex.y - 1))) {
                return false
            }
        }

        return true
    }

    fun buildIntersectionGraph(verticals: Set<Edge>, horizontal: Set<Edge>): DefaultUndirectedGraph<Int, DefaultEdge> {
        val graph = DefaultUndirectedGraph<Int, DefaultEdge>(DefaultEdge::class.java)

        for (h in horizontal) {
            for (v in verticals) {
                if (h.intersect(v)) {
                    graph.addVertex(h.id)
                    graph.addVertex(v.id)
                    graph.addEdge(h.id, v.id)
                }
            }
        }

        return graph
    }

    private fun id(): Int {
        return id++
    }
}