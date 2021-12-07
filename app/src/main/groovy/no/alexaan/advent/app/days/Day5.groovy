package no.alexaan.advent.app.days

class Day5 extends Day {

    def part1(String resource) {
        def lines = readResourceByLine(resource)

        Integer[][] matrix = buildMatrix()

        lines.each {
            def lineNumbers = this.extractLineNumbers(it)

            def (xFrom, xTo, yFrom, yTo) = [lineNumbers[0], lineNumbers[2], lineNumbers[1], lineNumbers[3]]

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

    def part2(String resource) {
        def lines = readResourceByLine(resource)

        Integer[][] matrix = buildMatrix()

        lines.each {
            def lineNumbers = this.extractLineNumbers(it)

            def (xFrom, xTo, yFrom, yTo) = [lineNumbers[0], lineNumbers[2], lineNumbers[1], lineNumbers[3]]

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
        (xFrom..xTo).each { x -> matrix[yFrom][x] += 1 }
    }

    static def addVerticalLine(Integer[][] matrix, def xFrom, def xTo, def yFrom, def yTo) {
        // println "Add vertical $xFrom, $yFrom -> $xTo, $yTo"
        (yFrom..yTo).each { y -> matrix[y][xFrom] += 1 }
    }

    static def addDiagonalLine(Integer[][] matrix, def xFrom, def xTo, def yFrom, def yTo) {
        // println "Add diagonal $xFrom, $yFrom -> $xTo, $yTo"
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
        def lineNumbers = []
        def pairs = line.split("->")
        pairs.each { pair -> pair.split(",").each { s -> lineNumbers.add(s.toInteger()) } }
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
