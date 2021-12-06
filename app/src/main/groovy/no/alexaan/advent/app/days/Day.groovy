package no.alexaan.advent.app.days

import java.nio.charset.StandardCharsets

abstract class Day {

    static List<String> readResourceByLine(String resource) {
        def string = new String(
            Thread.currentThread().getContextClassLoader().getResourceAsStream(resource).bytes,
            StandardCharsets.UTF_8
        )
        return string.split("\n")
    }

    def run() {
        println "${this.class.simpleName}"
        println "Part 1"
        part1()
        println "Part 2"
        part2()
    }

    abstract part1()

    abstract part2()
}
