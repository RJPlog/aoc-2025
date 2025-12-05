import java.io.File
import kotlin.math.*

fun cafeteria(in1: Int): Long{
    var puzzleInput = mutableListOf<String>()
	
	File("day2505_puzzle_input.txt").forEachLine {
		puzzleInput.add(it)
	}
	puzzleInput.remove("")
    
    val (IDRangesRaw, ingredients) = puzzleInput.partition {it.contains("-")}    
    val IDRanges : MutableList<Pair<Long,Long>> = IDRangesRaw.map {it -> Pair(it.split("-")[0].toLong(),it.split("-")[1].toLong())}.sortedBy {it.first}.toMutableList()
    
    // consolidate overlapping ID ranges
	var IDRangesCon = mutableListOf<Pair<Long,Long>>()
	
	var newRangeFirst = IDRanges[0].first
	var newRangeSecond = IDRanges[0].second

	for (i in 1..IDRanges.size-1) {
		var RangeFirst = IDRanges[i].first
		var RangeSecond = IDRanges[i].second
 		
		if (RangeFirst <= newRangeSecond) {
			newRangeSecond = maxOf(newRangeSecond,RangeSecond)
		}
		if (newRangeSecond < RangeFirst) {
			IDRangesCon.add(Pair(newRangeFirst, newRangeSecond))
			newRangeFirst = RangeFirst
			newRangeSecond = RangeSecond
		}
	}
	if(!IDRangesCon.contains(Pair(newRangeFirst, newRangeSecond))) IDRangesCon.add(Pair(newRangeFirst, newRangeSecond))
    
	// calculate results
    var countFresh = 0L
	
	if (in1 == 1) {
		ingredients.forEach {
        val ingredient = it.toLong()
        IDRangesCon.forEach {
            if (ingredient <= it.second && ingredient >= it.first) {
                countFresh += 1L
            }
        }
    } 
    return countFresh
	} else if (in1 == 2) {
        IDRangesCon.forEach {
            countFresh += it.second-it.first+1L
        }
        return countFresh
    }
	return -1L
}


fun main() {
    var t1 = System.currentTimeMillis()
 
//  solution for part 1
	val solution1 = cafeteria(1)
    println("*******************************")
    println("--- Day 5: Cafeteria ---")
    println("*******************************")
    println("Solution for part1")
    println("   $solution1 ingredient IDs are fresh")
    println() 

//  solution for part 2
	val solution2 = cafeteria(2)
    println("*******************************")
    println("Solution for part2")        
    println("   $solution2 IDs are considered as fresh ")
    println()

    t1 = System.currentTimeMillis() - t1
    println("puzzle solved in ${t1} ms")
}
