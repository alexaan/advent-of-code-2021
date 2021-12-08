package no.alexaan.advent.app.days

class Day8 extends Day {

    def part1(String resource) {
        def lines = readResourceByLine(resource)

        def pairs = lines.collect { it.split(" \\| ") }
        def outputs = []
        pairs.each { p -> outputs.add(p[1].split(" ")) }

        int count = 0
        outputs.each { o ->
            o.each { i ->
                if (i.toString().length() in [2, 4, 3, 7]) {
                    count++
                }
            }
        }
        println "count $count"
        count
    }

    def part2(String resource) {
        def lines = readResourceByLine(resource)
        def pairs = lines.collect { it.split(" \\| ") }
        def outputNumbersList = []
        pairs.each { p ->
            def inputs = p[0].split(" ")
            def one = inputs.find { it.length() == 2 }
            def four = inputs.find { it.length() == 4 }
            def seven = inputs.find { it.length() == 3 }
            def eight = inputs.find { it.length() == 7 }

            def fiveLength = inputs.findAll { it.length() == 5 } // 2, 3, 5
            def sixLength = inputs.findAll { it.length() == 6 } // 0, 6, 9

            def six = sixLength.find { s -> containsNCharsFrom(s, one, 1) }
            def nine = sixLength.find { s -> containsNCharsFrom(s, four, 4) && s != six }
            def zero = sixLength.find { s -> s != six && s != nine }

            def three = fiveLength.find { s -> containsNCharsFrom(s, one, 2) }
            def five = fiveLength.find { s -> containsNCharsFrom(s, four, 3) && s != three }
            def two = fiveLength.find { it != three && it != five }

            //println "Numbers $one $two $three $four $five $six $seven $eight $nine"

            def outputCharsList = p[1].split(" ")
            def outputNumberString = ""
            outputCharsList.each { o ->
                def matched = false
                [zero, one, two, three, four, five, six, seven, eight, nine].eachWithIndex { n, i ->
                    if (matched) {
                        return
                    }

                    if (containsSameCharsUnsorted(o, n)) {
                        outputNumberString += i
                        matched = true
                    }
                }
            }

            outputNumbersList.add(outputNumberString.toInteger())
        }
        def outputNumbersSummed = outputNumbersList.sum()
        println "outputNumbersSummed $outputNumbersSummed"
        outputNumbersSummed
    }

    static def containsNCharsFrom(String candidate, String target, int n) {
        def count = 0
        target.split("").each {
            if (candidate.contains(it)) {
                count++
            }
        }
        count == n
    }

    static def containsSameCharsUnsorted(String candidate, String target) {
        return candidate.length() == target.length() &&
            candidate.split("").every { target.contains(it) } &&
            target.split("").every { candidate.contains(it) }
    }
}
