package no.alexaan.advent.app.days

import spock.lang.Specification

class Day2Test extends Specification {

    def 'should produce output #expected for part1 given resource #resource'() {
        expect:
            expected == new Day2().part1(resource)
        where:
            resource      || expected
            "sample/day2" || 150
            "input/day2"  || 1936494
    }

    def 'should produce output #expected for part2 given resource #resource'() {
        expect:
            expected == new Day2().part2(resource)
        where:
            resource      || expected
            "sample/day2" || 900
            "input/day2"  || 1997106066
    }

}
