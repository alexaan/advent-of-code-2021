package no.alexaan.advent.app.days

class Day4 extends Day {

    def part1(String resource) {
        def lines = readResourceByLine(resource)

        def numbers = lines[0].split(",")

        def boards = findBoards(lines)

        def winnerBoard = null
        def winnerNumber = null

        numbers.each { number ->
            if (winnerBoard != null) {
                return
            }

            boards.each { board ->
                board.each { n ->
                    if (n[0] == number) {
                        n[1] = true
                    }
                }
            }

            boards.each { board ->
                if (hasRowWin(board)) {
                    winnerBoard = board
                    winnerNumber = number as int
                } else if (hasColumnWin(board)) {
                    winnerBoard = board
                    winnerNumber = number as int
                }
            }
        }

        def score = calcRemainingNumbersScore(winnerBoard)

        println "First winning board on num $winnerNumber with score $score gives ${winnerNumber * score}" // 33348
    }

    def part2(String resource) {
        def lines = readResourceByLine(resource)

        def numbers = lines[0].split(",")

        def boards = findBoards(lines)

        def winnerBoard = null
        def winnerNumber = null
        def lastWinner = null

        numbers.each { number ->
            if (lastWinner) {
                return
            }
            boards.each { b ->
                b.each { e ->
                    if (e[0] == number) {
                        e[1] = true
                    }
                }
            }

            def winBoards = []

            boards.eachWithIndex { board, i ->
                def win = false

                if (hasRowWin(board)) {
                    winnerBoard = board
                    winnerNumber = number as int
                    win = true
                } else if (hasColumnWin(board)) {
                    winnerBoard = board
                    winnerNumber = number as int
                    win = true
                }

                if (win && boards.size() > 1) {
                    winBoards.add(board)
                }
            }
            winBoards.each { boards.remove(it) }

            if (boards.size() == 1 && winnerBoard == boards[0]) {
                lastWinner = boards[0]
            }
        }

        def score = calcRemainingNumbersScore(lastWinner)

        println "Last winning board on num $winnerNumber with score $score gives ${winnerNumber * score}" // 8112
    }

    def static findBoards(List<String> lines) {
        def boardLineIdx = 2

        def boards = []
        while (boardLineIdx < lines.size()) {
            def nums = []
            (boardLineIdx..boardLineIdx + 4).each {
                nums.addAll(lines[it].split(" ").findAll { !it.isBlank() })
            }
            boards.add(nums.collect { n -> [n, false] })

            boardLineIdx += 6
        }
        boards
    }

    static calcRemainingNumbersScore(List winnerBoard) {
        def score = 0
        winnerBoard.each { n ->
            if (n[1] == false) {
                score += n[0] as int
            }
        }
        score
    }

    static hasRowWin(List board) {
        def hasWinningRow = false
        (0..4).each { rowIndex ->
            if (hasWinningRow) {
                return
            }
            def row = [
                board[0 + rowIndex * 5],
                board[1 + rowIndex * 5],
                board[2 + rowIndex * 5],
                board[3 + rowIndex * 5],
                board[4 + rowIndex * 5]
            ]
            if (row.every { it[1] == true }) {
                //println "winner by row board $b"
                hasWinningRow = true
            }
        }
        return hasWinningRow
    }

    static def hasColumnWin(List board) {
        def hasWinningColumn = false

        (0..4).each { columnIndex ->
            if (hasWinningColumn) {
                return
            }
            def column = [
                board[columnIndex + (5 * 0)],
                board[columnIndex + (5 * 1)],
                board[columnIndex + (5 * 2)],
                board[columnIndex + (5 * 3)],
                board[columnIndex + (5 * 4)]
            ]
            if (column.every { it[1] == true }) {
                //println "winner by column board $b"
                hasWinningColumn = true
            }

        }
        return hasWinningColumn
    }
}
