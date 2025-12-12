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
    //println(joltageLevel)
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
        //println(permutationList[permutationList.size-1])     
             
        // calculate lightbar result for each permutation
        
        var minResult = 0
        permutationList.forEach {
        //listOf("5051").forEach{
           var switchResult = MutableList(joltageLevel[i].split(",").size) {0}
			//println("x $switchResult ${joltageLevel[i]} ----   ${joltageLevel[i].replace(",","")}")
           for(j in 0..it.length -1) {
               val nPressed = it[j].toString().toInt()
               currentButtonList[j].split(",").forEach {
                   switchResult[it.toString().toInt()] +=nPressed
                   //println("   j: $j it: $it nPressed: $nPressed result $switchResult")
               }
               
           }
           var  result = switchResult.joinToString(",")
           //println("$it    ${switchResult.joinToString(",")} ${joltageLevel[i]} - $result")
           if (result == joltageLevel[i]) {
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

fun factory3(in1: Int): Int {
       
    val joltageLevel = mutableListOf<String>()
    val buttonWiring = mutableListOf<String>()
    
    var puzzleInput = """[#...#..##.] (0,1,2,5,7,8,9) (0,1,2,3,6,7,8) (2,3,4,5,6) (0,2,4,5,6,7,8,9) (3,5) (0,1,2,4,5,7,9) (0,2,3,6,7,8) (0,1,5,6,8,9) (0,2,3,5,7) (0,1,4,7,8) (2,3,5,6,7) {90,46,86,53,31,94,57,80,66,58}"""
	
		puzzleInput.split("\n").forEach {
        joltageLevel.add(it.substringAfter("{").dropLast(1))
        buttonWiring.add(it.substringAfter("] ").substringBefore(" {"))
    }
    //println(joltageLevel)
    //println(buttonWiring)
    //println()
    
    val buttonPressList = listOf("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o")

    for (i in 0..joltageLevel.size-1) {
        var equationMap = mutableMapOf<List<String>,Int>()
        println("looking at ${joltageLevel[i]}, ${buttonWiring[i]}")
        for (j in 0..joltageLevel[i].split(",").size-1) {
            
            var equation = mutableListOf<String>()
            for (k in 0..buttonWiring[i].split(" ").size-1) {
                //println("  cecking ${buttonWiring[i].split(" ")[k]} if contains $j")
                if (buttonWiring[i].split(" ")[k].contains(j.toString())) {
                    //println("it  ${buttonWiring[i].split(" ")[k]} , j $j")
                    equation.add(buttonPressList[k])
                    //println("eqation    $equation")
                }
            }
            equationMap.put(equation.distinct(), joltageLevel[i].split(",")[j].toInt())
        }
        println("  $equationMap")
        
        // start determine values for unknown variables a, b, c, ....
        // easiest: equations hold only one variable
        // try to reduce equation system step by step
        // start with equation with lowest value
        
        // das hier erzeugt soweit möglich minimale Equations. Geht aber nicht zwingend bis 1. -> wenn sich nichts mehr ändert, einfach die einzelwerte setzen?
        for (m in 0..15) {
            for (x in 0..equationMap.size-1) {
                val keyA = equationMap.entries.elementAt(x).key
                for (y in 0..equationMap.size-1) {
                    if (x != y) {
                        var keyB = equationMap.entries.elementAt(y).key
                        if (keyA.containsAll(keyB)) {
                            println("$keyA - $keyB = ${keyA-keyB}")
                            equationMap.put(keyA-keyB, equationMap.entries.elementAt(x).value-equationMap.entries.elementAt(y).value)
                        }
                    }
                    	
                }
            }
        }
        // and now???
        println(equationMap)
        
        println()
    }

    
	return 1
}
fun main() {
    
    var t1 = System.currentTimeMillis()
 
/*/  solution for part 1
	val solution1 = factory(1)
    println("*******************************")
    println("--- Day 10: Factory ---")
    println("*******************************")
    println("Solution for part1")
    println("   $solution1")
    println() */

//  solution for part 2
	val solution2 = factory2(2)
    println("*******************************")
    println("Solution for part2")        
    println("   $solution2")
    println() 

    t1 = System.currentTimeMillis() - t1
    println("puzzle solved in ${t1} ms")
}
