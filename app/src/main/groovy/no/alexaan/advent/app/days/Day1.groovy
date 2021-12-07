package no.alexaan.advent.app.days

class Day1 extends Day {

    def part1(String resource) {
        def lines = readResourceByLine(resource).collect { it as int }
        def count = 0

        lines.eachWithIndex { int entry, int i ->
            if (i > 0 && entry > lines[i - 1]) {
                //println "${entry} was gt ${lines[i - 1]}"
                count++
            }
        }

        //println "Count ${count}"
        println "Increasing measurements count $count" // 1477
    }

    def part2(String resource) {
        def lines = readResourceByLine(resource).collect { it as int }
        def count = 0

        lines.eachWithIndex { int entry, int i ->
            if (lines[i + 2]) {
                def currGroupSum = [entry, lines[i + 1], lines[i + 2]].sum()
                def prevGroupSum = [entry, lines[i + 1], lines[i - 1]].sum()

                if (currGroupSum > prevGroupSum) {
                    //println "${first} + ${second} + ${third} was gt ${sfirst} + ${ssecond} + ${sthird}"
                    count++
                }
            }
        }

        println "Increasing measurement groups count $count" // 1523
    }
}
