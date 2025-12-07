import java.io.File
import kotlin.math.*

fun laboratory(in1: Int): Int{
    var puzzleInput = mutableListOf<MutableList<Char>>()
	 
	
	File("day2507_puzzle_input.txt").forEachLine {
		puzzleInput.add(it.toMutableList())	
	}
	
	var width = puzzleInput[0].size
	var height = puzzleInput.size
	
	var splitCount = 0
	
	for (y in 1..height-1) {
		for (x in 0..width-1) {
			if (puzzleInput[y-1][x] == '|' || puzzleInput[y-1][x] == 'S') {
				if (puzzleInput[y][x] == '.') {
					puzzleInput[y][x] = '|'
				} else if (puzzleInput[y][x] == '^') {
					puzzleInput[y][x-1] = '|'
					puzzleInput[y][x+1] = '|'
					splitCount += 1
				}
			}
		}
	}		
	return splitCount
}

fun main() {
    var t1 = System.currentTimeMillis()
 
//  solution for part 1
	val solution1 = laboratory(1)
    println("*******************************")
    println("--- Day 7: Laboratories ---")
    println("*******************************")
    println("Solution for part1")
    println("   The beam will be split $solution1 times")
    println() 

/*/  solution for part 2
	val solution2 = trashCompactor2(2)
    println("*******************************")
    println("Solution for part2")        
    println("   $solution2 is the grand total found by adding together all of the answers to the individual problems")
    println() */

    t1 = System.currentTimeMillis() - t1
    println("puzzle solved in ${t1} ms")
}
