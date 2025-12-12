import java.io.File
import kotlin.math.*

data class Node (
	var id: String,
    var count: Long,
    var reached: Int
)

data class NodeRed (
	var id: String,
    var reached: Int
)

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

fun reactor2 (in1: Int): Long {
    val puzzleInput = """svr: aaa bbb
aaa: fft
fft: ccc
bbb: tty
tty: ccc
ccc: ddd eee
ddd: hub
hub: fff
eee: dac
dac: fff
fff: ggg hhh
ggg: out
hhh: out"""
    
    var puzzleInputMap = mutableMapOf<String,String>()
    puzzleInput.split("\n").forEach {
            puzzleInputMap.put(it.split(": ")[0], it.split(": ")[1])
    }

	println("puzzleInputMap $puzzleInputMap")
    
	var pathList = mutableListOf<Node>()
	pathList.add(Node("svr",1,0))

	var pathSearch = true
    var n = 0
    while (pathSearch) {
        pathSearch = false
        n += 1
        println(n)
        for (i in 0..pathList.size-1) {
            val id = pathList[i].id
            if (id != "out") {
                val count = pathList[i].count
                var reached = pathList[i].reached
                pathList[i] = Node("",0,0)
                puzzleInputMap.getValue(id).split(" ").forEach {
                    if (it =="fft") reached += 1 
                    if (it == "dac") reached += 10
                    pathList.add(Node(it, count, reached))
                }
            }
        }
        while (pathList.contains(Node("",0,0))) {
            pathList.removeAt(pathList.indexOf(Node("",0,0)))
        }
    // now consolidate -> add all nodes with same name together, add up counts but distinguish with different types of reached (or delete the ones without reached, if you have on with has reached?)
        var pathListMap = mutableMapOf<NodeRed,Long>()
        for (j in 0..pathList.size-1) {
            var id = pathList[j].id
            var count = pathList[j].count
            var reached = pathList[j].reached

            if (pathListMap.containsKey(NodeRed(id,reached))) {
                pathListMap.put(NodeRed(id,reached), pathListMap.getValue(NodeRed(id,reached)) + count)
            } else {
                pathListMap.put(NodeRed(id,reached), count)
            } 
            pathList[j] = Node("",0,0)
        } 
        for ((key,count) in pathListMap) {
            pathList.add(Node(key.id, count, key.reached))
        }
        while (pathList.contains(Node("",0,0))) {
            pathList.removeAt(pathList.indexOf(Node("",0,0)))
        } 
        pathList.forEach {
            if (it.id != "out") pathSearch = true
        }
    	
	    //println(pathList)
    }

    
    var result = -1L

    println("xx $n")
    pathList.forEach {
        if (it.reached == 11) result = it.count
    }
    println(pathList)
    return result
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

//  solution for part 2
	val solution2 = reactor2(2)
    println("*******************************")
    println("Solution for part2")        
    println("   $solution2")
    println()

    t1 = System.currentTimeMillis() - t1
    println("puzzle solved in ${t1} ms")
}
