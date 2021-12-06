package no.alexaan.advent.app.days

class Day5 extends Day {

    def part1() {
        def lines = readFileLineByLine("dec5")

        Integer[][] matrix = buildMatrix()

        lines.each {
            def lineNumbers = this.extractLineNumbers(it)

            def xFrom = lineNumbers[0]
            def xTo = lineNumbers[2]

            def yFrom = lineNumbers[1]
            def yTo = lineNumbers[3]

            if (xFrom == xTo || yFrom == yTo) {
                if (xFrom != xTo) {
                    addHorizontalLine(matrix, xFrom, xTo, yFrom, yTo)
                }

                if (yFrom != yTo) {
                    addVerticalLine(matrix, xFrom, xTo, yFrom, yTo)
                }
            }
        }

        def overlaps = countOverlaps(matrix)

        println "Overlapping horizontal + vertical lines: $overlaps" // 5092
    }

    def part2() {
        def lines = readFileLineByLine("dec5")

        Integer[][] matrix = buildMatrix()

        lines.each {
            def lineNumbers = this.extractLineNumbers(it)

            def xFrom = lineNumbers[0]
            def xTo = lineNumbers[2]

            def yFrom = lineNumbers[1]
            def yTo = lineNumbers[3]

            if (xFrom != xTo && yFrom != yTo) {
                addDiagonalLine(matrix, xFrom, xTo, yFrom, yTo)
            } else if (xFrom != xTo) {
                addHorizontalLine(matrix, xFrom, xTo, yFrom, yTo)
            } else if (yFrom != yTo) {
                addVerticalLine(matrix, xFrom, xTo, yFrom, yTo)
            }
        }

        def overlaps = countOverlaps(matrix)

        println "Overlapping horizontal + vertical + diagonal lines: $overlaps" // 20484
    }

    static def addHorizontalLine(Integer[][] matrix, def xFrom, def xTo, def yFrom, def yTo) {
        // println "Add horizontal $xFrom, $yFrom -> $xTo, $yTo"
        (xFrom..xTo).each { matrix[yFrom][it] += 1 }
    }

    static def addVerticalLine(Integer[][] matrix, def xFrom, def xTo, def yFrom, def yTo) {
        // println "Considering vertical $xFrom $yFrom -> $xTo $yTo"
        (yFrom..yTo).each { matrix[it][xFrom] += 1 }
    }

    static def addDiagonalLine(Integer[][] matrix, def xFrom, def xTo, def yFrom, def yTo) {
        // println "Considering diagonal $xFrom $yFrom -> $xTo $yTo"
        def y = yFrom
        def yDirection = yFrom > yTo ? -1 : 1
        (xFrom..xTo).each { x ->
            matrix[y][x] += 1
            y += (1 * yDirection)
        }
    }

    private static Integer[][] buildMatrix() {
        Integer[] matrixRow = [0] * 1000

        def matrix = [[] * 1000]
        (0..999).each { (matrix[it] = matrixRow.collect()) }
        matrix
    }

    /**
     * Input "260,605 -> 260,124"
     * Output [260,605, 260, 124]
     */
    def extractLineNumbers(String line) {
        def pairs = line.split("->")
        def lineNumbers = []
        pairs.each { it.split(",").each { lineNumbers.add(it.toInteger()) } }
        lineNumbers
    }

    static def countOverlaps(Integer[][] matrix) {
        int overlaps = 0
        matrix.eachWithIndex { x, ix ->
            x.eachWithIndex { i, ii ->
                if (i > 1) {
                    //println "overlap at $ix $ii"
                    overlaps++
                }
            }
        }
        overlaps
    }
}
