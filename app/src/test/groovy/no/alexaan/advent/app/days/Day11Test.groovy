package no.alexaan.advent.app.days

import spock.lang.Specification

class Day11Test extends Specification {
    def 'should produce output #expected for part1 given resource #resource'() {
        expect:
            expected == new Day11().part1(resource)
        where:
            resource        || expected
            "initial/day11" || 259
            "sample/day11"  || 1656
            "input/day11"   || 1627
    }

    def 'should produce output #expected for part2 given resource #resource'() {
        expect:
            expected == new Day11().part2(resource)
        where:
            resource        || expected
            "sample/day11"  || 195
            "input/day11"   || 329
    }
}
