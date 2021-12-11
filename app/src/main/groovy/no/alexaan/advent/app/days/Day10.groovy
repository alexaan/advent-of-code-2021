package no.alexaan.advent.app.days

class Day10 extends Day {

    def static closingBracketInfo = [
        ")": [openBracket: "(", illegalScore: 3, addScore: 1],
        "]": [openBracket: "[", illegalScore: 57, addScore: 2],
        "}": [openBracket: "{", illegalScore: 1197, addScore: 3],
        ">": [openBracket: "<", illegalScore: 25137, addScore: 4]
    ]

    def static openBracketInfo = [
        "(": [closingBracket: ")"],
        "[": [closingBracket: "]"],
        "{": [closingBracket: "}"],
        "<": [closingBracket: ">"]
    ]

    def part1(String resource) {
        def lines = readResourceByLine(resource)

        def scoredLines = scoreLines(lines)

        scoredLines.collect { l -> l["illegalScore"] }.sum()
    }

    def part2(String resource) {
        def lines = readResourceByLine(resource)

        def scoredLines = scoreLines(lines)

        def lineAddScores = []

        scoredLines.each { l ->
            if (l["illegalScore"] > 0) {
                return
            }
            def closingBracketsToAdd = ""
            (l["open"] as String).reverse().each { s ->
                closingBracketsToAdd += openBracketInfo[s]["closingBracket"]
            }

            BigInteger lineAddScore = 0
            closingBracketsToAdd.split("").each { s ->
                lineAddScore = lineAddScore * 5 + closingBracketInfo[s]["addScore"]
            }
            lineAddScores.add(lineAddScore)
        }

        def middleScore = lineAddScores.sort()[(lineAddScores.size() / 2).intValue()]
        middleScore
    }

    def static scoreLines(List<String> lines) {

        def outputLines = []

        lines.eachWithIndex { l, i ->
            String open = ""
            def illegalScore = 0
            l.split("").eachWithIndex { s, ii ->

                if (illegalScore > 0) {
                    return
                }

                if ("([{<".contains(s)) {
                    open += s
                }

                if (")]}>".contains(s)) {
                    if (open.endsWith(closingBracketInfo[s]["openBracket"])) {
                        open = open.substring(0, open.length() - 1)
                    } else {
                        illegalScore += closingBracketInfo[s]["illegalScore"]
                    }
                }
            }

            outputLines.add([line: l, illegalScore: illegalScore, open: open])
        }
        outputLines
    }
}
