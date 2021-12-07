package no.alexaan.advent.app.days

import java.nio.charset.StandardCharsets

abstract class Day {

    abstract part1(String resource)

    abstract part2(String resource)

    def run() {
        def day = this.class.simpleName
        println "--- Running ${day} ---"
        runPartWithResource("part 1", "sample", day, resource -> part1("$resource"))
        runPartWithResource("part 1", "input", day, resource -> part1("$resource"))
        runPartWithResource("part 2", "sample", day, resource -> part2("$resource"))
        runPartWithResource("part 2", "input", day, resource -> part2("$resource"))
    }

    static runPartWithResource(def label, def resourceType, def day, Closure c) {
        def start = System.currentTimeMillis()
        c.call("$resourceType/${day.toLowerCase()}")
        println "$label ($resourceType) finished in ${System.currentTimeMillis() - start}ms"
    }

    static List<String> readResourceByLine(String resource) {
        def string = new String(
            Thread.currentThread().getContextClassLoader().getResourceAsStream(resource).bytes,
            StandardCharsets.UTF_8
        )
        return string.split("\n")
    }
}
