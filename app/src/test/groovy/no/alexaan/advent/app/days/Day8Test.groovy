package no.alexaan.advent.app.days

import spock.lang.Specification

class Day8Test extends Specification {
    def 'should produce output #expected for part1 given resource #resource'() {
        expect:
            expected == new Day8().part1(resource)
        where:
            resource      || expected
            "sample/day8" || 26
            "input/day8"  || 519
    }

    def 'should produce output #expected for part2 given resource #resource'() {
        expect:
            expected == new Day8().part2(resource)
        where:
            resource       || expected
            "initial/day8" || 5353
            "sample/day8"  || 61229
            "input/day8"   || 1027483
    }
}
