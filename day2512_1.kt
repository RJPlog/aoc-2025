import java.io.File
import kotlin.math.*

fun treeFarm (in1: Int): Int {
    
    val puzzleInput = """39x44: 51 35 37 44 52 44
48x48: 46 39 48 41 40 42
41x40: 28 25 35 24 19 37
41x39: 31 24 29 29 28 28
48x39: 28 29 39 42 31 39
44x46: 55 52 46 63 43 56"""
    
    val giftSpace = listOf(7, 6, 7, 5, 7, 7)
    val giftSpacePackedEnvelope = listOf(15, 12, 16, 12, 18, 18) // space needed for 2 of each
    var result = 0

    puzzleInput.split("\n").forEach {
       val A = it.substringBefore("x").toString().toInt()
       val B =  it.substringBefore(":").substringAfter("x").toString().toInt()
       val gifts = it.substringAfter(": ").split(" ").map {it.toString().toInt()}
       
       var spaceNeeded = 0
       var spaceNeededEnvelopePacked = 0
        
       for (i in 0..gifts.size-1) {
           spaceNeeded += giftSpace[i] * gifts[i]
           spaceNeededEnvelopePacked += giftSpacePackedEnvelope[i] * (gifts[i] / 2)
           spaceNeededEnvelopePacked += giftSpace[i] * (gifts[i] % 2)
       }

       if (A*B - spaceNeededEnvelopePacked > 0) {
           if (A*B - spaceNeeded < 100) println("critical space left")
           result += 1
       }
    } 
	return result
}


fun main() {
    
    var t1 = System.currentTimeMillis()
 
//  solution for part 1
	val solution1 = treeFarm(1)
    println("*******************************")
    println("--- Day 12: Christmas Tree Farm ---")
    println("*******************************")
    println("Solution for part1")
    println("   $solution1")
    println() 

    t1 = System.currentTimeMillis() - t1
    println("puzzle solved in ${t1} ms")
}
