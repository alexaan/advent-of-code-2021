package no.alexaan.advent.app.days

class Day3 extends Day {

    def part1() { //calcPowerConsumption
        def lines = readResourceByLine("day3")

        def bitsForIndex = (0..11).collect { [] }

        lines.each { l ->
            l.getChars().eachWithIndex { c, i -> bitsForIndex[i].add(c) }
        }

        def gammaBinary = ""
        def epsilonBinary = ""

        bitsForIndex.eachWithIndex { e, i ->
            def zeroes = e.findAll { it == "0" }.size()
            def ones = e.findAll { it == "1" }.size()
            gammaBinary += zeroes > ones ? "0" : "1"
            epsilonBinary += zeroes > ones ? "1" : "0"
        }

        int gamma = Integer.parseInt(gammaBinary, 2)
        int epsilon = Integer.parseInt(epsilonBinary, 2)

        println "power Consumption ${gamma * epsilon}"
    }

    def part2() {
        def lines = readResourceByLine("day3")

        def oxLines = lines.collect()
        def ix = 0
        while (oxLines.size() > 1) {
            def chars = oxLines.collect { it -> it.charAt(ix) }
            def zeroes = chars.findAll { it == "0" }.size()
            def ones = chars.findAll { it == "1" }.size()
            def keep = zeroes > ones ? "0" : "1"
            oxLines = oxLines.findAll { it.charAt(ix) == keep as char }
            ix++
        }

        int ox = Integer.parseInt(oxLines[0], 2)

        def co2Lines = lines.collect()
        def ix2 = 0
        while (co2Lines.size() > 1) {
            def chars = co2Lines.collect { it -> it.charAt(ix2) }
            def zeroes = chars.findAll { it == "0" }.size()
            def ones = chars.findAll { it == "1" }.size()
            def keep = zeroes <= ones ? "0" : "1"
            co2Lines = co2Lines.findAll { it.charAt(ix2) == keep as char }
            ix2++
        }

        int co2 = Integer.parseInt(co2Lines[0], 2)

        println "Life support rating ${ox * co2}"
    }
}
