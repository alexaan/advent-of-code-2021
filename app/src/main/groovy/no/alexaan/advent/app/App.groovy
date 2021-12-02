package no.alexaan.advent.app

import no.alexaan.advent.app.days.Dec1
import no.alexaan.advent.app.days.Dec2

class App {
    static void main(String[] args) {

        println "Day number? (or leave empty to run all, exit to quit)"

        while (true) {
            def day = System.in.newReader().readLine()
            if (day.isEmpty() || day == "all") {
                runAll()
            } else if (day == "exit") {
                println "Shutting down.."
                break
            } else {
                try {
                    this."dec${day}"()
                } catch (MissingMethodException ignored) {
                    println "Invalid day ${day}"
                }
            }

            println "Another day?"
        }
    }

    private static void runAll() {
        dec1()
        dec2()
    }

    private static void dec1() {
        println "########## Dec1 ##########"
        println "Increasing measurements count ${Dec1.countIncreasingMeasurements()}" // 1477
        println "Increasing measurement groups count ${Dec1.countIncreasingMeasurementGroups()}" // 1523
    }

    private static void dec2() {
        println "########## Dec2 ##########"
        println "Multiplied position and depth ${Dec2.multiplyPositionAndDepth()}" // 1936494
        println "Pos and depth with aim ${Dec2.multiplyPositionAndDepthWithAim()}" // 1997106066
    }
}
