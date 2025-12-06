import java.io.File
import kotlin.math.*

fun trashCompactor(in1: Int): Long{
    var puzzleInput = mutableListOf<String>()
	
	var x = 0
	var y = 0
	
	File("day2506_puzzle_input.txt").forEachLine {
		var line = it.trimEnd(' ').trim(' ')
		while(line.contains("  "))  {
			line = line.replace("  "," ")
		}
		x = line.split(" ").size
		line.split(" ").forEach {
			puzzleInput.add(it)	
		}
		y += 1
	}
	
	var singleResults = puzzleInput.take(x).map {it.toLong()}.toMutableList()
	
	for (i in 1..y-2) {
		for (j in 0..x-1) {
			if (puzzleInput[j + x*(y-1)] == "+") {
				singleResults[j] = singleResults[j] + puzzleInput[j + i*x].toLong()
			} else {
				singleResults[j] = singleResults[j] * puzzleInput[j + i*x].toLong()		
			}
		}
	}
	return singleResults.sum()
}

fun trashCompactor2(in1: Int): Long{
    var puzzleInput = mutableListOf<String>()
	
	var x = 0
	var y = 0
	
	File("day2506_puzzle_input.txt").forEachLine {
		puzzleInput.add(it.reversed())
		x = it.length
		y += 1
	}
	
    var singleResult = mutableListOf<Long>()
	var totalResult = 0L
	for (i in 0..x-1) {
		var operand =""
		for (j in 0..y-2) {
			if (puzzleInput[j][i] != ' ') {
				operand += puzzleInput[j][i]
			}
		}
		if (operand != "") {
			singleResult.add(operand.toLong())
		}
		if (puzzleInput[y-1][i] == '+') {
			totalResult += singleResult.sum()
			singleResult.clear()
		} else if (puzzleInput[y-1][i] == '*') {
			totalResult += singleResult.fold(1L) { sum, element -> sum * element}
			singleResult.clear()
		}
	}
	return totalResult
}

fun main() {
    var t1 = System.currentTimeMillis()
 
//  solution for part 1
	val solution1 = trashCompactor(1)
    println("*******************************")
    println("--- Day 6: Trash Compactor ---")
    println("*******************************")
    println("Solution for part1")
    println("   $solution1 is the grand total found by adding together all of the answers to the individual problems")
    println() 

//  solution for part 2
	val solution2 = trashCompactor2(2)
    println("*******************************")
    println("Solution for part2")        
    println("   $solution2 is the grand total found by adding together all of the answers to the individual problems")
    println()

    t1 = System.currentTimeMillis() - t1
    println("puzzle solved in ${t1} ms")
}
