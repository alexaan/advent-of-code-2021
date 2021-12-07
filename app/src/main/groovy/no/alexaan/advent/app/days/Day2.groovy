package no.alexaan.advent.app.days

class Day2 extends Day {

    def part1(String resource) {
        def lines = readResourceByLine(resource)

        def x = 0
        def y = 0

        lines.each { l ->
            {
                def adjustment = Integer.parseInt(l.substring(l.length() - 1, l.length()))
                if (l.startsWith("forward")) {
                    x += adjustment
                } else if (l.startsWith("down")) {
                    y += adjustment
                } else if (l.startsWith("up")) {
                    y -= adjustment
                }
            }
        }

        def result = x * y
        println "Multiplied position and depth $result"
        result
    }

    def part2(String resource) {
        def lines = readResourceByLine(resource)

        def x = 0
        def y = 0
        def aim = 0

        lines.each { l ->
            {
                def adjustment = Integer.parseInt(l.substring(l.length() - 1, l.length()))
                if (l.startsWith("forward")) {
                    x += adjustment
                    y += adjustment * aim
                } else if (l.startsWith("down")) {
                    aim += adjustment
                } else if (l.startsWith("up")) {
                    aim -= adjustment
                }
            }
        }

        def result = x * y
        println "Pos and depth with aim $result"
        result
    }
}
