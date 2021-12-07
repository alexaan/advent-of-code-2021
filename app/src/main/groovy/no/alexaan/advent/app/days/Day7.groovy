package no.alexaan.advent.app.days

class Day7 extends Day {

    def part1() {
        def lines = readResourceByLine("day7")

        def initialPositions = lines[0].split(",").collect { it.toInteger() }
        def min = initialPositions.min()
        def max = initialPositions.max()

        def minCost = null
        (min..max).each { pos ->
            def cost = 0
            def positions = initialPositions.collect()
            positions.each { p ->
                cost += Math.abs(p - pos)
            }

            if (minCost == null || minCost > cost) {
                minCost = cost
            }
        }

        println "cheapest fuel cost is $minCost" // 344605
    }

    def part2() {
        def lines = readResourceByLine("day7")

        def initialPositions = lines[0].split(",").collect { it.toInteger() }

        def avg = new BigDecimal(initialPositions.average() as String).intValue()
        def cost = 0
        initialPositions.each { pos ->
            def base = Math.abs(avg - pos)
            (0..base).each { b -> cost += b }
        }

        println "cheapest fuel cost is $cost" // 93699985
    }
}
