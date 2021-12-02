package no.alexaan.advent.app.days

class Dec2 {

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

    def static multiplyPositionAndDepth() {
        def lines = readFileLineByLine("dec2")

        def x = 0
        def y = 0

        lines.each { l ->
            {
                def adjustment = Integer.parseInt(l.substring(l.length() - 1, l.length()))
                if (l.startsWith("forward")) {
                    x += adjustment
                    //println "Forward ${adjustment} to ${x}"
                } else if (l.startsWith("down")) {
                    y += adjustment
                    //println "Down ${adjustment} to ${y}"
                } else if (l.startsWith("up")) {
                    y -= adjustment
                    //println "Up ${adjustment} to ${y}"
                }
            }
        }

        //println "Multiplying ${x} and ${y} to ${x * y}"
        x * y
    }

    def static multiplyPositionAndDepthWithAim() {
        def lines = readFileLineByLine("dec2")

        def x = 0
        def y = 0
        def aim = 0

        lines.each { l ->
            {
                def adjustment = Integer.parseInt(l.substring(l.length() - 1, l.length()))
                if (l.startsWith("forward")) {
                    x += adjustment
                    y += adjustment * aim
                    //println "Forward ${adjustment} with aim ${aim} to ${x} ${y}"
                } else if (l.startsWith("down")) {
                    aim += adjustment
                    //println "Aim ${adjustment} to ${aim}"
                } else if (l.startsWith("up")) {
                    aim -= adjustment
                    //println "Aim ${adjustment} to ${aim}"
                }
            }
        }

        //println "Multiplying ${x} and ${y} to ${x * y}"
        x * y
    }
}
