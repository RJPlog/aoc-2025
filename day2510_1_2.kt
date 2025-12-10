import java.io.File
import kotlin.math.*

var permutationList = mutableListOf<String>()

fun permutations(in1: Int, in2: String, range: Int) {
    if (in1 == 0) {
        for (i in 0..range) {
            permutationList.add(in2 + i.toString())
        }
	} else {
        for (i in 0..range) {
            permutations(in1-1, in2 +i.toString(), range )
        }
    }
}

fun factory(in1: Int): Int {
       
    val indicatorLights = mutableListOf<String>()
    val buttonWiring = mutableListOf<String>()
	
		File("day2510_puzzle_input.txt").forEachLine {
        indicatorLights.add(it.substringBefore("]").drop(1))
        buttonWiring.add(it.substringAfter("] ").substringBefore(" {"))
    }
    //println(indicatorLights)
    //println(buttonWiring)
    
    var totalButtonsPressed = 0
    for (i in 0..indicatorLights.size-1) {
        println(i)
        var currentButtonList = buttonWiring[i].split(" ").map {it.drop(1).dropLast(1)}
        println("  ${indicatorLights[i]} $currentButtonList")
        
        // create all permutations
        permutationList.clear()
		//println("xx ${buttonWiring[i]} ${buttonWiring[i].split(" ").size}")
        permutations(currentButtonList.size-1,"", 2)
        //println(permutationList[0])     
             
        // calculate lightbar result for each permutation
        
        var minResult = 0
        permutationList.forEach {
        //listOf("111000","010102", "000011").forEach{
           var switchResult = MutableList(indicatorLights[i].length) {0}
           for(j in 0..it.length -1) {
               val nPressed = it[j].toString().toInt()
               currentButtonList[j].split(",").forEach {
                   switchResult[it.toString().toInt()] +=nPressed
                   //println("   j: $j it: $it nPressed: $nPressed result $switchResult")
               }
               
           }
           var  result = switchResult.map {it -> if (it % 2 == 0) '.' else '#'}.joinToString("")
            //println("$it    $switchResult $result")
           if (result == indicatorLights[i]) {
               		if (minResult == 0) {
                        minResult = it.fold(0) {a, n -> a + n.toString().toInt()}
                    } else {
                        minResult = minOf(minResult, it.fold(0) {a, n -> a + n.toString().toInt()})
                    }
                    // calc min number of pressed buttons
                } 
           
           //println("minResult $minResult")
           
           
        }
		if (minResult == 0) println("xxxx")
        totalButtonsPressed += minResult
        println("   totalButtonsPressed $totalButtonsPressed")

    }
    
	return totalButtonsPressed
}

fun factory2(in1: Int): Int {
       
    val joltageLevel = mutableListOf<String>()
    val buttonWiring = mutableListOf<String>()
	
		File("day2510_puzzle_input.txt").forEachLine {
        joltageLevel.add(it.substringAfter("{").dropLast(1))
        buttonWiring.add(it.substringAfter("] ").substringBefore(" {"))
    }
    //println(indicatorLights)
    //println(buttonWiring)
    
    var totalButtonsPressed = 0
    for (i in 0..joltageLevel.size-1) {
        println(i)
        var currentButtonList = buttonWiring[i].split(" ").map {it.drop(1).dropLast(1)}
        println("  ${joltageLevel[i]} $currentButtonList")
        
        // create all permutations
        permutationList.clear()
		//println("xx ${buttonWiring[i]} ${buttonWiring[i].split(" ").size}")
        permutations(currentButtonList.size-1,"", 5)
        //println(permutationList[0])     
             
        // calculate lightbar result for each permutation
        
        var minResult = 0
        permutationList.forEach {
        //listOf("111000","010102", "000011").forEach{
           var switchResult = MutableList(joltageLevel[i].replace(",","").length) {0}
           for(j in 0..it.length -1) {
               val nPressed = it[j].toString().toInt()
               currentButtonList[j].split(",").forEach {
                   switchResult[it.toString().toInt()] +=nPressed
                   //println("   j: $j it: $it nPressed: $nPressed result $switchResult")
               }
               
           }
           //var  result = switchResult.map {it -> if (it % 2 == 0) '.' else '#'}.joinToString("")
           //println("$it    ${switchResult.joinToString(",")} ${joltageLevel[i]}")
           if (switchResult.joinToString(",") == joltageLevel[i]) {
               		if (minResult == 0) {
                        minResult = it.fold(0) {a, n -> a + n.toString().toInt()}
                    } else {
                        minResult = minOf(minResult, it.fold(0) {a, n -> a + n.toString().toInt()})
                    }
                    // calc min number of pressed buttons
                } 
           
           //println("minResult $minResult")
           
           
        }
		if (minResult == 0) println("xxxx")
        totalButtonsPressed += minResult
        println("   totalButtonsPressed $totalButtonsPressed")

    }
    
	return totalButtonsPressed
}

fun main() {
    
    var t1 = System.currentTimeMillis()
 
//  solution for part 1
	val solution1 = factory(1)
    println("*******************************")
    println("--- Day 10: Factory ---")
    println("*******************************")
    println("Solution for part1")
    println("   $solution1")
    println()

//  solution for part 2
	val solution2 = factory2(2)
    println("*******************************")
    println("Solution for part2")        
    println("   $solution2")
    println() 

    t1 = System.currentTimeMillis() - t1
    println("puzzle solved in ${t1} ms")
}
