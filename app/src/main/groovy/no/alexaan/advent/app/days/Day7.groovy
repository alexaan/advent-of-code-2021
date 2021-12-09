package no.alexaan.advent.app.days


import java.math.RoundingMode

class Day7 extends Day {

    def part1(String resource) {
        def lines = readResourceByLine(resource)

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

        println "cheapest fuel cost is $minCost"
        minCost
    }

    def part2(String resource) {
        def lines = readResourceByLine(resource)

        def initialPositions = lines[0].split(",").collect { it.toInteger() }

        def avg = new BigDecimal(initialPositions.average() as String)
        def avgUp = avg.setScale(0, RoundingMode.UP).intValue()
        def avgDown = avg.setScale(0, RoundingMode.DOWN).intValue()
        def costDown = 0
        def costUp = 0
        initialPositions.each { pos ->
            (0..Math.abs(avgDown - pos)).each { b -> costDown += b }
            (0..Math.abs(avgUp - pos)).each { b -> costUp += b }
        }

        def minCost = Math.min(costDown, costUp)

        println "cheapest fuel cost is $minCost"
        minCost
    }
}