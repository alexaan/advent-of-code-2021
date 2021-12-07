package no.alexaan.advent.app.days

import spock.lang.Specification

class Day7Test extends Specification {

    def 'should produce output #expected for part1 given resource #resource'() {
        expect:
            expected == new Day7().part1(resource)
        where:
            resource      || expected
            "sample/day7" || 37
            "input/day7"  || 344605
    }

    def 'should produce output #expected for part2 given resource #resource'() {
        expect:
            expected == new Day7().part2(resource)
        where:
            resource      || expected
            "sample/day7" || 168
            "input/day7"  || 93699985
    }
}
