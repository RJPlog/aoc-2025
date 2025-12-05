import java.io.File
import kotlin.math.*

fun cafeteria(in1: Int): Int{
    var puzzleInput = mutableListOf<String>()
	
	File("day2505_puzzle_input.txt").forEachLine {
		puzzleInput.add(it)
	}
	puzzleInput.remove("")
    
    val (IDRangesRaw, ingredients) = puzzleInput.partition {it.contains("-")}    
    var IDRanges = IDRangesRaw.map {it -> Pair(it.split("-")[0].toLong(),it.split("-")[1].toLong())}.sortedBy {it.first}
    
    // here may come the need to consolidate ID ranges
    
    var countFresh = 0

    ingredients.forEach {
        val ingredient = it.toLong()
		var countDouble = false
        IDRanges.forEach {
            if (ingredient <= it.second && ingredient >= it.first && !countDouble) {
                countFresh += 1
				countDouble = true
            }
        }
    }
      
    return countFresh
}

fun main() {
    var t1 = System.currentTimeMillis()

	val solution1 = cafeteria(1)
    

//  solution for part 1
    println("*******************************")
    println("--- Day 5: Cafeteria ---")
    println("*******************************")
    println("Solution for part1")
    println("   $solution1 ingredient IDs are fresh")   //824 is to high -> guess some of the ingrdients are counted double
    println()

    /*
//  solution for part 2
	val solution2 = cafeteria(1)
    println("*******************************")
    println("Solution for part2")
    println("   $solution2 ")  
    println()
    */

    t1 = System.currentTimeMillis() - t1
    println("puzzle solved in ${t1} ms")
}
