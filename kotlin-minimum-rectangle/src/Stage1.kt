/**
 * Stage 1: Example floor plan
 *
 * Create an example floor plan with at least 10 points and a minimum rectangle partition of 4.
 *
 * 00 000000000000
 * 01 001111111000
 * 02 001111111000
 * 03 000001111100
 * 04 000001111100
 * 05 000001100000
 * 06 000001100000
 * 07 001111111000
 * 08 001111111000
 * 09 001111111000
 * 10 000000000000
 * 11 000000000000
 */
object Stage1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val plan: Array<Array<Boolean>> = Array(12) { Array(12) { false } }

        initRect(plan, 2, 8, 1, 2);
        initRect(plan, 5, 9, 3, 4);
        initRect(plan, 5, 6, 5, 6);
        initRect(plan, 2, 8, 7, 9);

        printRect(plan)
    }

    private fun initRect(plan: Array<Array<Boolean>>, minColumn: Int, maxColumn: Int, minRow: Int, maxRow: Int) {
        for (i in minColumn..maxColumn + 1) {
            for (j in minRow..maxRow + 1) {
                plan[i][j] = true
            }
        }
    }

    private fun printRect(plan: Array<Array<Boolean>>) {
        for (row in plan.indices) {
            for (col in plan[0].indices) {
                when (plan[col][row]) {
                    true -> print("1 ")
                    false -> print("0 ")
                }
            }
            println()
        }
    }

}