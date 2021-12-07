package no.alexaan.advent.app.days

class Day6 extends Day {

    def part1(String resource) {
        def lines = readResourceByLine(resource)

        def fishAgeInput = lines[0].split(",").collect { it.toInteger() }
        def fishAgeOutput = fishAgeInput.collect()

        (0..79).each { day ->
            def newFishCount = 0
            fishAgeOutput = fishAgeOutput.collect { fish ->
                if (fish == 0) {
                    newFishCount += 1
                    6
                } else {
                    fish - 1
                }
            }

            newFishCount.times { fishAgeOutput.add(8) }
        }

        println "${fishAgeOutput.size()} fish after 80 days" // 366057
    }

    def part2(String resource) {
        def lines = readResourceByLine(resource)

        def fishAgeInput = lines[0].split(",").collect { it.toInteger() }

        def fishCountByAge = (0..8).collect { age ->
            fishAgeInput.findAll { it == age }.size().toBigInteger()
        }

        (0..255).each { day ->
            def fishCountByAgeCopy = fishCountByAge.collect()
            (8..0).each { dt ->
                fishCountByAgeCopy[dt] = fishCountByAge[dt + 1]
            }
            fishCountByAgeCopy[6] = fishCountByAgeCopy[6] + fishCountByAge[0]
            fishCountByAgeCopy[8] = fishCountByAge[0]
            fishCountByAge = fishCountByAgeCopy
        }

        println "${fishCountByAge.sum()} fish after 256 days" // 1653559299811
    }
}
