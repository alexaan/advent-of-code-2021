package no.alexaan.advent.app.days

import spock.lang.Specification

class Day10Test extends Specification {
    def 'should produce output #expected for part1 given resource #resource'() {
        expect:
            expected == new Day10().part1(resource)
        where:
            resource       || expected
            "sample/day10" || 26397
            "input/day10"  || 278475
    }

    def 'should produce output #expected for part2 given resource #resource'() {
        expect:
            expected == new Day10().part2(resource)
        where:
            resource       || expected
            "sample/day10" || 288957
            "input/day10"  || 3015539998
    }
}
