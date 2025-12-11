import java.io.File
import kotlin.math.*

var puzzleInputMap = mutableMapOf<String,String>()

fun reactor (in1: Int): Int {
    var pathList = mutableListOf<String>()
	pathList.add("you")

    
    var searchPath = true
    while (searchPath) {
        for (i in 0..pathList.size-1) {
            if (pathList[i] != "out") {
                puzzleInputMap.getValue(pathList[i]).split(" ").forEach {
                    pathList.add(it)
                }
                pathList[i] = ""
            } 
        }
        while (pathList.contains("")) {
            pathList.removeAt(pathList.indexOf(""))
        }
 
        if (pathList.size == pathList.count {it == "out"}) searchPath = false
    }
	return pathList.size
}

fun reactor2(in1: String, in2: Int): Int {
	//println("Aufruf mit $in1 und $in2")
	var result = 0
	if (in1 != "out") {
		//println("   ${puzzleInputMap.getValue(in1)} ${puzzleInputMap.getValue(in1).split(" ").size}")
		puzzleInputMap.getValue(in1).split(" ").forEach {
			//println("      ${it}")
			var patternDetected = 0
			if (it == "dac" || it == "fft") patternDetected = 1
			result += reactor2(it, in2 + patternDetected)
		}
		return result
	}

	if (in2 == 2) {
		println()
		return 1
	} else {
		return 0
	}
}

fun main() {
    
    var t1 = System.currentTimeMillis()
 
	File("day2511_puzzle_input.txt").forEachLine {
		puzzleInputMap.put(it.split(": ")[0], it.split(": ")[1])
    }
	
/*/  solution for part 1
	val solution1 = reactor(1)
    println("*******************************")
    println("--- Day 11: Reactor ---")
    println("*******************************")
    println("Solution for part1")
    println("   $solution1")
    println() */

//  solution for part 2
	val solution2 = reactor2("svr",0)
    println("*******************************")
    println("Solution for part2")        
    println("   $solution2")
    println() 

    t1 = System.currentTimeMillis() - t1
    println("puzzle solved in ${t1} ms")
}
