package no.alexaan.advent.app.days

abstract class Day {

    static List<String> readFileLineByLine(String filePath) {
        def lines = []
        File file = new File(filePath)
        def line
        file.withReader { reader ->
            while ((line = reader.readLine()) != null) {
                //println "${line}"
                lines.add(line)
            }
        }
        return lines
    }

    def run() {
        println "${this.class.simpleName}"
        println "Part 1"
        part1()
        println "Part 2"
        part2()
    }

    abstract part1()

    abstract part2()
}
