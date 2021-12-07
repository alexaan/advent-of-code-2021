package no.alexaan.advent.app.days

import spock.lang.Specification

class Day1Test extends Specification {

    def 'should produce output #expected for part1 given resource #resource'() {
        expect:
            expected == new Day1().part1(resource)
        where:
            resource      || expected
            "sample/day1" || 7
            "input/day1"  || 1477
    }

    def 'should produce output #expected for part2 given resource #resource'() {
        expect:
            expected == new Day1().part2(resource)
        where:
            resource      || expected
            "sample/day1" || 5
            "input/day1"  || 1523
    }
}
