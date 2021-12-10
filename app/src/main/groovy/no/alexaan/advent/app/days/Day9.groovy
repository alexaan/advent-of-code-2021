package no.alexaan.advent.app.days

class Day9 extends Day {

    def part1(String resource) {

        def numberMatrix = toNumberMatrix(resource)

        def lowPoints = findLowPoints(numberMatrix)

        lowPoints.collect { it.riskLevel() }.sum()
    }

    def part2(String resource) {
        def numberMatrix = toNumberMatrix(resource)

        def lowPoints = findLowPoints(numberMatrix)

        List<List<Point>> basinPoints = []

        lowPoints.each { lp ->
            basinPoints.add(findBasinPoints(numberMatrix, lp))
        }

        def pointsForLargestBasins = basinPoints.sort(b -> -b.size()).take(3)

        def product = pointsForLargestBasins.inject(1) { result, bp ->
            result * bp.size()
        }

        product
    }

    static List<List<Integer>> toNumberMatrix(String resource) {
        def lines = readResourceByLine(resource)

        def numberMatrix = [] as List<List<Integer>>

        lines.each { l ->
            numberMatrix.add(
                l.split("").collect(s -> Integer.parseInt(s))
            )
        }
        numberMatrix
    }

    List<Point> findLowPoints(List<List<Integer>> numberMatrix) {
        def lowPoints = []
        numberMatrix.eachWithIndex { List<Integer> nl, y ->
            nl.eachWithIndex { int num, x ->
                def point = mapToPoint(numberMatrix, x, y)
                if (point.isLowPoint()) {
                    lowPoints.add(point)
                }
            }
        }
        lowPoints
    }

    Point mapToPoint(List<List<Integer>> numberMatrix, int x, int y) {
        def val = numberMatrix[y][x]
        def left = x > 0 ? numberMatrix[y][x - 1] : null
        def right = numberMatrix[y][x + 1]
        def top = y > 0 ? numberMatrix[y - 1][x] : null
        def bottom = numberMatrix[y + 1] ?[x]

        def p = new Point()
        p.x = x
        p.y = y
        p.val = val
        p.leftVal = left
        p.topVal = top
        p.rightVal = right
        p.bottomVal = bottom
        p
    }

    List<Point> findBasinPoints(List<List<Integer>> numberMatrix, Point point) {

        def basinPoints = [point]

        if (point.leftVal != 9 && point.leftVal > point.val) {
            basinPoints.addAll(findBasinPoints(numberMatrix, mapToPoint(numberMatrix, point.x - 1, point.y)))
        }

        if (point.topVal != 9 && point.topVal > point.val) {
            basinPoints.addAll(findBasinPoints(numberMatrix, mapToPoint(numberMatrix, point.x, point.y - 1)))
        }

        if (point.rightVal != 9 && point.rightVal > point.val) {
            basinPoints.addAll(findBasinPoints(numberMatrix, mapToPoint(numberMatrix, point.x + 1, point.y)))
        }

        if (point.bottomVal != 9 && point.bottomVal > point.val) {
            basinPoints.addAll(findBasinPoints(numberMatrix, mapToPoint(numberMatrix, point.x, point.y + 1)))
        }

        basinPoints.unique { a, b ->
            a.x <=> b.x ?: a.y <=> b.y
        }
    }

    def class Point {
        int x
        int y
        int val
        Integer leftVal
        Integer topVal
        Integer rightVal
        Integer bottomVal

        def riskLevel() {
            val + 1
        }

        def isLowPoint() {
            lowerThan(val, leftVal) && lowerThan(val, rightVal) && lowerThan(val, topVal) && lowerThan(val, bottomVal)
        }

        def lowerThan(int candidate, Integer target) {
            return target == null || candidate < target
        }
    }
}
