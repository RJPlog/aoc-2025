import java.io.File
import kotlin.math.*

fun movieTheater(in1: Int): Long {
    val redTiles = mutableListOf<Pair<Long,Long>>()
	
	File("day2509_puzzle_input.txt").forEachLine {
        redTiles.add(Pair(it.split(",")[0].toLong(), it.split(",")[1].toLong()))
    }
    
    var maxRectangle = 0L
    for (i in 0..redTiles.size-1) {
        for (j in i+1..redTiles.size-1) {
            val volume = (abs(redTiles[i].first - redTiles[j].first)+1) * (abs(redTiles[i].second - redTiles[j].second)+1)
            if (volume > maxRectangle) {
                maxRectangle = volume
            }
        }
    }

	return maxRectangle
}

fun main() {
    
    var t1 = System.currentTimeMillis()
 
//  solution for part 1
	val solution1 = movieTheater(1)
    println("*******************************")
    println("--- Day 9: Movie Theater ---")
    println("*******************************")
    println("Solution for part1")
    println("   The largest rectancle is $solution1.")  // 6833708340 is to high
    println()

/*/  solution for part 2
	val solution2 = movieTheater(2)
    println("*******************************")
    println("Solution for part2")        
    println("   $solution2")
    println() */ 

    t1 = System.currentTimeMillis() - t1
    println("puzzle solved in ${t1} ms")
}
