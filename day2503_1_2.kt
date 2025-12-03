import java.io.File
import kotlin.math.*

fun lobby(in1: Int): Long{
    var joltageTotal = 0L
    var test = listOf("987654321111111", "811111111111119", "234234234234278", "818181911112111")
    
    test.forEach {
 		var joltage = ""
        var substring = it
		for (i in in1-1 downTo 0) {
        	substring = substring.dropLast(i)
            joltage += substring.toList().sortedDescending()[0] 
            val cutIndex = substring.indexOf(substring.toList().sortedDescending()[0])
            substring =  it.drop(cutIndex+1)  // das wird immer wieder neuüberschrieben? 
            // -> first correction needed: substring instead of it
            // -> second correction: wenn nicht mehr genug da sind, müssen die in der vorhandenen 
            // reihenfolge genommen werden! -> new concept? Worked for part one only accidentially            
    	}
        joltageTotal += joltage.toLong()
    }
    return joltageTotal
}

fun main() {
    var t1 = System.currentTimeMillis()

	val solution1 = lobby(2)
    val solution2 = lobby(12)

// print solution for part 1
    println("*******************************")
    println("--- Day 3: Lobby ---")
    println("*******************************")
    println("Solution for part1")
    println("   $solution1 is the total output joltage")
    println()

// print solution for part 2
    println("*******************************")
    println("Solution for part2")
    println("   $solution2 is the total output joltage")  //173084479160332 is to high
    println()

    t1 = System.currentTimeMillis() - t1
    println("puzzle solved in ${t1} ms")
}
