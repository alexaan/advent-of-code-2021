package no.alexaan.advent.app

import no.alexaan.advent.app.days.Day

class App {

    def static prompt = "> Day number? (or leave empty to run all, exit to quit)"

    static void main(String[] args) {

        println prompt

        while (true) {
            def day = System.in.newReader().readLine()
            if (day.isEmpty() || day == "all") {
                //runAll()
                println "Currently disabled"
            } else if (day == "exit") {
                println "Shutting down.."
                break
            } else {
                try {
                    (ClassLoader.systemClassLoader.loadClass(
                        "no.alexaan.advent.app.days.Day${day}"
                    ) as Class<? extends Day>)
                        .newInstance()
                        .run()
                } catch (ClassNotFoundException ignored) {
                    println "Invalid day ${day}"
                }
            }

            println ""
            println prompt
        }
    }
}
