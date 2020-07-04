import Scenarios.scenario1
import Scenarios.scenario2
import Scenarios.scenario3
import Scenarios.scenario4
import Scenarios.scenario5
import Scenarios.scenario6
import Scenarios.scenario7
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class AlgorithmTest {

    fun testMinRectangles(scenario: Scenario) {
        val min = Algorithm(scenario.grid).minRectangles()
        assertEquals(scenario.expectedMinRectangles, min)
    }

    @Test
    fun minRectangles_scenario1() {
        testMinRectangles(scenario1())
    }

    @Test
    fun minRectangles_scenario2() {
        testMinRectangles(scenario2())
    }

    @Test
    fun minRectangles_scenario3() {
        testMinRectangles(scenario3())
    }

    @Test
    fun minRectangles_scenario4() {
        testMinRectangles(scenario4())
    }

    @Test
    fun minRectangles_scenario5() {
        testMinRectangles(scenario5())
    }

    @Test
    fun minRectangles_scenario6() {
        testMinRectangles(scenario6())
    }

    /**
     * This test fails because the GraphT returns max matching of 3 instead of 4. I did not find out why.
     */
    @Test
    fun minRectangles_scenario7() {
        testMinRectangles(scenario7())
    }

    @Test
    fun testCountConvexVertices() {
        val scenario = scenario7()
        val algorithm = Algorithm(scenario.grid)
        println(algorithm.countConvexVertices())
    }

    @Test
    fun testFindConcaveVertices() {
        val scenario = scenario7()
        val algorithm = Algorithm(scenario.grid)
        val vertices = algorithm.findConcaveVertices()

        for (vertex in vertices) {
            println(vertex)
        }
    }

    @Test
    fun testFindVerticalEdges() {
        val scenario = scenario7()
        val algorithm = Algorithm(scenario.grid)
        val vertices = algorithm.findConcaveVertices()
        val verticals = algorithm.findVerticalEdges(vertices)

        for (vertext in verticals) {
            println(vertext)
        }
    }

    @Test
    fun testFindHorizontalEdges() {
        val scenario = scenario7()
        val algorithm = Algorithm(scenario.grid)
        val vertices = algorithm.findConcaveVertices()
        val horizontals = algorithm.findHorizontalEdges(vertices)

        for (vertext in horizontals) {
            println(vertext)
        }
    }

    @Test
    fun testGraph() {
        val scenario = scenario7()
        val algorithm = Algorithm(scenario.grid)
        val vertices = algorithm.findConcaveVertices()
        val verticals = algorithm.findVerticalEdges(vertices)
        val horizontals = algorithm.findHorizontalEdges(vertices)

        val graph = algorithm.buildIntersectionGraph(verticals, horizontals)

        for (vertex in graph.vertexSet()) {
            println(vertex)
        }

        for (edge in graph.edgeSet()) {
            println(edge)
        }
    }

}
