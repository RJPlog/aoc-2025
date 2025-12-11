import java.io.File
import kotlin.math.*



fun reactor (in1: Int): Int {
    val puzzleInput = """aaa: you hhh
you: bbb ccc
bbb: ddd eee
ccc: ddd eee fff
ddd: ggg
eee: out
fff: out
ggg: out
hhh: ccc fff iii
iii: out"""
    
    var puzzleInputMap = mutableMapOf<String,String>()
    puzzleInput.split("\n").forEach {
            puzzleInputMap.put(it.split(": ")[0], it.split(": ")[1])
    }

//println("puzzleInputMap $puzzleInputMap")
    
    //var pathList = mutableListOf<MutableList<String>>()
    var pathList = mutableListOf<String>()
    //pathList.add(mutableListOf("you"))
    pathList.add("you")
    
    var searchPath = true
    while (searchPath) {
        for (i in 0..pathList.size-1) {
            //println(pathList[i])
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
        //println("$pathList")
        if (pathList.size == pathList.count {it == "out"}) searchPath = false
    }

	return pathList.size
}

fun main() {
    
    var t1 = System.currentTimeMillis()
 
//  solution for part 1
	val solution1 = reactor(1)
    println("*******************************")
    println("--- Day 11: Reactor ---")
    println("*******************************")
    println("Solution for part1")
    println("   $solution1")
    println() 

/*/  solution for part 2
	val solution2 = factory(2)
    println("*******************************")
    println("Solution for part2")        
    println("   $solution2")
    println() */

    t1 = System.currentTimeMillis() - t1
    println("puzzle solved in ${t1} ms")
}
