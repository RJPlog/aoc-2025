import java.io.File
import kotlin.math.*

fun printingDepartment(in1: Int): Int{
    var result = 0
    val puzzleInput = listOf("..@@.@@@@.", "@@@.@.@.@@", "@@@@@.@.@@", "@.@@@@..@.", "@@.@@@@.@@", ".@@@@@@@.@", ".@.@.@.@@@", "@.@@@.@@@@", ".@@@@@@@@.", "@.@.@@@.@.")
      
    val width = puzzleInput[0].length
    val height = puzzleInput.size
    
    val directions = listOf(Pair(-1,-1), Pair(-1,0), Pair(-1,1), Pair(0,-1), Pair(0,1), Pair(1,-1), Pair(1,0), Pair(1,1))
    val mapOfRolls = puzzleInput.joinToString("")
    
    for (y in 0..height-1) {
        for (x in 0..width-1) {
            if (mapOfRolls[x + y*height] == '@') {
                var countOfRolls = 0
                directions.forEach {
                    val testX = x + it.first
                    val testY = y + it.second
                    if (testX >= 0 && testX < width && testY >= 0 && testY < height) {

                        if (mapOfRolls[testX + testY*height] == '@') countOfRolls += 1
                    }
                }
                if (countOfRolls < 4) result += 1
            }
        }        
    }
    return result
}

fun main() {
    var t1 = System.currentTimeMillis()

	val solution1 = printingDepartment(1)
    //val solution2 = printingDepartment(12)

// print solution for part 1
    println("*******************************")
    println("--- Day 4: Printing Department ---")
    println("*******************************")
    println("Solution for part1")
    println("   $solution1 rolls of paper can be accessed by a forklift")
    println()

// print solution for part 2
    println("*******************************")
    println("Solution for part2")
    //println("   $solution2 ")  
    println()

    t1 = System.currentTimeMillis() - t1
    println("puzzle solved in ${t1} ms")
}
