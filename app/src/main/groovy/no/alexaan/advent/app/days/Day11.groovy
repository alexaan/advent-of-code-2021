package no.alexaan.advent.app.days

class Day11 extends Day {

    def part1(String resource) {

        def matrix = initializeMatrix(resource)

        def flashedCount = 0

        (0..99).each { tick ->
            flashedCount += doOneTick(matrix)
        }
        flashedCount
    }

    def part2(String resource) {
        def matrix = initializeMatrix(resource)
        def emptyMatrix = buildMatrix(matrix.length)

        int tick = 0
        while (matrix != emptyMatrix) {
            doOneTick(matrix)
            tick++
        }

        tick
    }

    static def initializeMatrix(String resource) {
        def lines = readResourceByLine(resource)

        def matrixSize = lines[0].length()

        def matrix = buildMatrix(matrixSize)

        lines.eachWithIndex { l, i ->
            l.split("").eachWithIndex { s, ii ->
                matrix[i][ii] = s.toInteger()
            }
        }

        matrix
    }

    static def doOneTick(Integer[][] matrix) {
        matrix.eachWithIndex { row, y ->
            row.eachWithIndex { column, x ->
                matrix[y][x] += 1
            }
        }

        def flashed = []
        def anyFlashed = true
        while (anyFlashed) {
            anyFlashed = false
            matrix.eachWithIndex { row, y ->
                row.eachWithIndex { column, x ->
                    if (matrix[y][x] > 9 && !flashed.contains([y, x])) {
                        flashed.add([y, x])
                        anyFlashed = true

                        nullSafeAdd(matrix, x - 1, y) // right
                        nullSafeAdd(matrix, x, y - 1) // top
                        nullSafeAdd(matrix, x + 1, y) // left
                        nullSafeAdd(matrix, x, y + 1) // bottom
                        nullSafeAdd(matrix, x - 1, y - 1) // topRight
                        nullSafeAdd(matrix, x + 1, y - 1) // topLeft
                        nullSafeAdd(matrix, x + 1, y + 1) // bottomLeft
                        nullSafeAdd(matrix, x - 1, y + 1) // bottomRight
                    }
                }
            }
        }

        matrix.eachWithIndex { row, y ->
            row.eachWithIndex { column, x ->
                if (matrix[y][x] > 9) {
                    matrix[y][x] = 0
                }
            }
        }

        flashed.size()
    }

    static def nullSafeAdd(Integer[][] matrix, int x, int y) {
        def size = matrix[0].size()
        if (y >= 0 && y < size && x >= 0 && x < size) {
            matrix[y][x] += 1
        }
    }

    private static Integer[][] buildMatrix(int size) {
        Integer[] matrixRow = [0] * size

        def matrix = [[] * size]
        (0..(size - 1)).each { (matrix[it] = matrixRow.collect()) }
        matrix as Integer[][]
    }
}
