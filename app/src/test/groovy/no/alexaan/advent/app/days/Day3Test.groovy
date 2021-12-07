package no.alexaan.advent.app.days

import spock.lang.Specification

class Day3Test extends Specification {

    def 'should produce output #expected for part1 given resource #resource'() {
        expect:
            expected == new Day3().part1(resource)
        where:
            resource      || expected
            "sample/day3" || 198
            "input/day3"  || 4139586
    }

    def 'should produce output #expected for part2 given resource #resource'() {
        expect:
            expected == new Day3().part2(resource)
        where:
            resource      || expected
            "sample/day3" || 230
            "input/day3"  || 1800151
    }
}
