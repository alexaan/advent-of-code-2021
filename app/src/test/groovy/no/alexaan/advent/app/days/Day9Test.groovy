package no.alexaan.advent.app.days

import spock.lang.Specification

class Day9Test extends Specification {
    def 'should produce output #expected for part1 given resource #resource'() {
        expect:
            expected == new Day9().part1(resource)
        where:
            resource       || expected
            "sample/day9"  || 15
            "input/day9"   || 575
    }

    def 'should produce output #expected for part2 given resource #resource'() {
        expect:
            expected == new Day9().part2(resource)
        where:
            resource       || expected
            "sample/day9"  || 1134
            "input/day9"   || 1019700
    }
}
