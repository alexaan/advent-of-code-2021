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
        def start = System.currentTimeMillis()
        println "--- Running ${this.class.simpleName} ---"
        println "---- Part 1 ----"
        part1()
        println "Part 1 finished in ${System.currentTimeMillis() - start}ms"
        start = System.currentTimeMillis()
        println "---- Part 2 ----"
        part2()
        println "Part 2 finished in ${System.currentTimeMillis() - start}ms"
    }

    abstract part1()

    abstract part2()
}
