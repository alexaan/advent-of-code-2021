package no.alexaan.advent.app.days

class Day4 extends Day {

    def part1() {
        def lines = readFileLineByLine("dec4")

        def numbers = lines[0].split(",")

        def boardStartLineIndex = 2

        def boards = []
        while (boardStartLineIndex < lines.size()) {
            def nums = []
            (boardStartLineIndex..boardStartLineIndex + 4).each {
                nums.addAll(lines[it].split(" ").findAll { !it.isBlank() })
            }
            boards.add(nums.collect { n -> [n, false] })
            boardStartLineIndex += 6
        }

        def winnerBoard = null
        def winnerNumber = null

        numbers.each { n ->
            if (winnerBoard != null) return
            boards.each { b ->
                b.each { e ->
                    if (e[0] == n) {
                        e[1] = true
                    }
                }
            }

            boards.each { b ->
                {
                    (0..4).each { rn ->
                        {
                            def row = [b[0 + rn * 5], b[1 + rn * 5], b[2 + rn * 5], b[3 + rn * 5], b[4 + rn * 5]]
                            if (row.every { it[1] == true }) {
                                println "winner by row board $b"
                                winnerBoard = b
                                winnerNumber = n as int
                            }
                        }
                    }
                    (0..4).each { cn ->
                        {
                            def column = [b[cn], b[cn + 5], b[cn + (5 * 2)], b[cn + (5 * 3)], b[cn + (5 * 4)]]
                            if (column.every { it[1] == true }) {
                                println "winner by column board $b"
                                winnerBoard = b
                                winnerNumber = n as int
                            }
                        }
                    }
                }
            }
        }

        def score = 0
        winnerBoard.each {
            n ->
                {
                    if (n[1] == false) {
                        score += n[0] as int
                    }
                }
        }

        println "number $winnerNumber and score $score gives ${winnerNumber * score}"
    }

    def part2() {
        def lines = readFileLineByLine("dec4")

        def numbers = lines[0].split(",")

        def boardStartIdx = 2

        def boards = []
        while (boardStartIdx < lines.size()) {
            def nums = []
            (boardStartIdx..boardStartIdx + 4).each {
                nums.addAll(lines[it].split(" ").findAll { !it.isBlank() })
            }
            boards.add(nums.collect { n -> [n, false] })

            boardStartIdx += 6
        }


        def winnerBoard = null
        def winnerNumber = null
        def lastWinner = null

        numbers.each { n ->
            if (lastWinner) {
                println "lastwinner $lastWinner"
                return
            }
            boards.each { b ->
                b.each { e ->
                    if (e[0] == n) {
                        e[1] = true
                    }
                }
            }

            def winBoards = []

            boards.eachWithIndex { b, i ->
                {
                    def win = false

                    (0..4).each { rn ->
                        {
                            def row = [b[0 + rn * 5], b[1 + rn * 5], b[2 + rn * 5], b[3 + rn * 5], b[4 + rn * 5]]
                            if (row.every { it[1] == true }) {
                                winnerBoard = b
                                winnerNumber = n as int
                                win = true
                            }
                        }
                    }
                    (0..4).each { cn ->
                        {
                            def column = [b[cn], b[cn + 5], b[cn + (5 * 2)], b[cn + (5 * 3)], b[cn + (5 * 4)]]
                            if (column.every { it[1] == true }) {
                                winnerBoard = b
                                winnerNumber = n as int
                                win = true
                            }
                        }
                    }

                    if (win && boards.size() > 1) {
                        winBoards.add(b)
                    }
                }
            }
            winBoards.each { boards.remove(it) }
            if (boards.size() == 1 && winnerBoard == boards[0]) {
                lastWinner = boards[0]
            }
        }

        def score = 0
        lastWinner.each {
            n ->
                {
                    if (n[1] == false) {
                        score += n[0] as int
                    }
                }
        }

        println "number $winnerNumber and score $score gives ${winnerNumber * score}"
    }
}
