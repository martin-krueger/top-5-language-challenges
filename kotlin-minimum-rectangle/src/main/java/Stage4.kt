/**
 * Prints grid and calculate minimum rectangles for different scenarios.
 */
object Stage4 {

    @JvmStatic
    fun main(args: Array<String>) {
        print(Scenarios.scenario1())
        print(Scenarios.scenario2())
        print(Scenarios.scenario3())
        print(Scenarios.scenario4())
        print(Scenarios.scenario5())
        print(Scenarios.scenario6())
        print(Scenarios.scenario7())
    }

    private fun print(scenario: Scenario) {
        println(scenario.grid)

        val min = Algorithm(scenario.grid).minRectangles()
        println("Minimum rectangles: expected: ${scenario.expectedMinRectangles} calculated: ${min}")

        println()
        println()
    }

}