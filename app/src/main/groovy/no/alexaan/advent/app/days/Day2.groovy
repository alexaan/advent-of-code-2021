package no.alexaan.advent.app.days

class Day2 extends Day {

    def part1() {
        def lines = readResourceByLine("day2")

        def x = 0
        def y = 0

        lines.each { l ->
            {
                def adjustment = Integer.parseInt(l.substring(l.length() - 1, l.length()))
                if (l.startsWith("forward")) {
                    x += adjustment
                    //println "Forward ${adjustment} to ${x}"
                } else if (l.startsWith("down")) {
                    y += adjustment
                    //println "Down ${adjustment} to ${y}"
                } else if (l.startsWith("up")) {
                    y -= adjustment
                    //println "Up ${adjustment} to ${y}"
                }
            }
        }

        //println "Multiplying ${x} and ${y} to ${x * y}"
        x * y
        println "Multiplied position and depth ${x * y}" // 1936494
    }

    def part2() {
        def lines = readResourceByLine("day2")

        def x = 0
        def y = 0
        def aim = 0

        lines.each { l ->
            {
                def adjustment = Integer.parseInt(l.substring(l.length() - 1, l.length()))
                if (l.startsWith("forward")) {
                    x += adjustment
                    y += adjustment * aim
                    //println "Forward ${adjustment} with aim ${aim} to ${x} ${y}"
                } else if (l.startsWith("down")) {
                    aim += adjustment
                    //println "Aim ${adjustment} to ${aim}"
                } else if (l.startsWith("up")) {
                    aim -= adjustment
                    //println "Aim ${adjustment} to ${aim}"
                }
            }
        }

        //println "Multiplying ${x} and ${y} to ${x * y}"
        println "Pos and depth with aim ${x * y}" // 1997106066
    }
}
