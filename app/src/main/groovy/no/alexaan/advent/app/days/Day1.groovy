package no.alexaan.advent.app.days

class Day1 extends Day {

    def part1(String resource) {
        def lines = readResourceByLine(resource).collect { it as int }
        def count = 0

        lines.eachWithIndex { int entry, int i ->
            if (i > 0 && entry > lines[i - 1]) {
                count++
            }
        }

        println "Increasing measurements count $count"
        count
    }

    def part2(String resource) {
        def lines = readResourceByLine(resource).collect { it as int }
        def count = 0

        lines.eachWithIndex { int entry, int i ->
            if (lines[i + 2]) {
                def currGroupSum = [entry, lines[i + 1], lines[i + 2]].sum()
                def prevGroupSum = [entry, lines[i + 1], lines[i - 1]].sum()

                if (currGroupSum > prevGroupSum) {
                    count++
                }
            }
        }

        println "Increasing measurement groups count $count"
        count
    }
}
