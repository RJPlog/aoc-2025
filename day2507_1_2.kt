import java.io.File
import kotlin.math.*

fun laboratory(puzzleInput: MutableList<MutableList<Char>>, in2: Int): Long{
    //var puzzleInput = mutableListOf<MutableList<Char>>()
	
	var width = puzzleInput[0].size
	var height = puzzleInput.size
	
	// addon for part2
	var pathCount = MutableList(height) {MutableList<Long>(width) {0L}}
	pathCount[0][puzzleInput[0].indexOf('S')] = 1L
	
	var splitCount = 0L
	
	for (y in 1..height-1) {
		for (x in 0..width-1) {
			if (puzzleInput[y-1][x] == '|' || puzzleInput[y-1][x] == 'S') {
				if (puzzleInput[y][x] == '.') {
					puzzleInput[y][x] = '|'
					// add on for part2
					pathCount[y][x] += pathCount[y-1][x]
					
				} else if (puzzleInput[y][x] == '^') {
					puzzleInput[y][x-1] = '|'
					puzzleInput[y][x+1] = '|'
					splitCount += 1L
					
					// addon for part2					
					pathCount[y][x-1] += pathCount[y-1][x] //that was a tricky one, if you are coming from left then already in previous round the vertical number was added - don't do it twice
					pathCount[y][x+1] += pathCount[y-1][x] + pathCount[y-1][x+1]
				}
			}
		}
	}
	
	// addon for part2
	if (in2 == 2) return pathCount[height - 1].sum()

	return splitCount
}

// first idea for part2, works for example but for real data takes way to long (if really working :-))
fun quantumTachyonManifold(puzzleInput: MutableList<MutableList<Char>>, xPos: Int, yPos: Int): Int{
	if (yPos == puzzleInput.size-1) return 1
	if(puzzleInput[yPos][xPos] == '.'){
		return quantumTachyonManifold(puzzleInput, xPos, yPos + 1)
	} else if (puzzleInput[yPos][xPos] == '^') {
	    return quantumTachyonManifold(puzzleInput, xPos -1 , yPos + 1) + quantumTachyonManifold(puzzleInput, xPos +1, yPos + 1)
	}
	return 0
}

fun main() {
    var t1 = System.currentTimeMillis()
	
	var puzzleInput = mutableListOf<MutableList<Char>>()
	File("day2507_puzzle_input.txt").forEachLine {
		puzzleInput.add(it.toMutableList())	
	}
 
//  solution for part 1
	val solution1 = laboratory(puzzleInput, 1)
    println("*******************************")
    println("--- Day 7: Laboratories ---")
    println("*******************************")
    println("Solution for part1")
    println("   The beam will be split $solution1 times")
    println()
	
	puzzleInput.clear()
	File("day2507_puzzle_input.txt").forEachLine {
		puzzleInput.add(it.toMutableList())	
	}

//  solution for part 2
	//val solution2 = quantumTachyonManifold(puzzleInput, puzzleInput[0].indexOf('S'), 1)
	val solution2 = laboratory(puzzleInput, 2)
    println("*******************************")
    println("Solution for part2")        
    println("   $solution2 different timelines would a single tachyon particle end up on")
    println() 

    t1 = System.currentTimeMillis() - t1
    println("puzzle solved in ${t1} ms")
}
