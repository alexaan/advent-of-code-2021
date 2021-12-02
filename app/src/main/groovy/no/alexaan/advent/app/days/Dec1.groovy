package no.alexaan.advent.app.days

class Dec1 {

    static List<Integer> readFileLineByLine(String filePath) {
        def lines = []
        File file = new File(filePath)
        def line
        file.withReader { reader ->
            while ((line = reader.readLine()) != null) {
                //println "${line}"
                lines.add(Integer.parseInt(line))
            }
        }
        return lines
    }

    def static countIncreasingMeasurements() {
        def lines = readFileLineByLine("dec1")
        def count = 0

        lines.eachWithIndex { Integer entry, int i ->
            if (i > 0 && entry > lines[i - 1]) {
                //println "${entry} was gt ${lines[i - 1]}"
                count++
            }
        }

        //println "Count ${count}"
        count
    }

    def static countIncreasingMeasurementGroups() {
        def lines = readFileLineByLine("dec1")
        def count = 0

        lines.eachWithIndex { Integer entry, int i ->
            if (lines[i + 2]) {
                def currGroupSum = [entry, lines[i+1], lines[i+2]].sum()
                def prevGroupSum = [entry, lines[i + 1], lines[i - 1]].sum()

                if (currGroupSum > prevGroupSum) {
                    //println "${first} + ${second} + ${third} was gt ${sfirst} + ${ssecond} + ${sthird}"
                    count++
                }
            }
        }

        //println "Count ${count}"
        count
    }
}
