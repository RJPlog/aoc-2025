import java.io.File
import kotlin.math.*

data class junctionBox(
    var boxes: MutableList<Int>,
    var dist: Double
)

fun playGround(in1: Int): Int{
    var puzzleInput = listOf("162,817,812", "57,618,57", "906,360,560", "592,479,940", "352,342,300", "466,668,158", "542,29,236", "431,825,988", "739,650,466", "52,470,668", "216,146,977", "819,987,18", "117,168,530", "805,96,715", "346,949,466", "970,615,88", "941,993,340", "862,61,35", "984,92,344", "425,690,689")
	var numberOfPairs = 10

    // caculate distances
    var distances = mutableListOf<junctionBox>()
    
    for(i in 0..puzzleInput.size-1) {
        for (j in i+1..puzzleInput.size-1) {
            var distance = (puzzleInput[i].split(",")[0].toInt()-puzzleInput[j].split(",")[0].toInt()).toDouble().pow(2)
            distance += (puzzleInput[i].split(",")[1].toInt()-puzzleInput[j].split(",")[1].toInt()).toDouble().pow(2)
            distance += (puzzleInput[i].split(",")[2].toInt()-puzzleInput[j].split(",")[2].toInt()).toDouble().pow(2)
            distances.add(junctionBox(mutableListOf(i,j), sqrt(distance)))
            distances.sortBy{it.dist}
            distances = distances.take(numberOfPairs).toMutableList()
        }
    }
    distances.sortBy{it.dist}
    //println("distance: $distances.size")
    
    // built up circuits
    var circuits = mutableListOf<MutableList<Int>>()

    for (i in 0..numberOfPairs-1) {
        circuits.add(distances[i].boxes)
    }
    //println("circuits: $circuits")
    
    var i = 0
    while(i < circuits.size) {
        var circuit = circuits[i] 
        var consolidateInProgress = true
        while (consolidateInProgress) {
            consolidateInProgress = false
            for (j in i+1..circuits.size-1) {
                var newCircuit = mutableListOf<Int>()
                newCircuit.addAll(circuits[j].toMutableList())
                newCircuit.addAll(circuit.toMutableList())
                newCircuit = newCircuit.distinct().toMutableList()
                if (newCircuit.distinct().size != circuit.size + circuits[j].size) {
                    //println("same circuit $circuit / ${circuits[j]}  -> $newCircuit")
                    circuits[i] = newCircuit
                    circuit = newCircuit
                    circuits[j] = mutableListOf<Int>()
                    consolidateInProgress = true
                }
        	}
            while(circuits.contains(mutableListOf<Int>())) {
                circuits.removeAt(circuits.indexOf(mutableListOf<Int>()))
            } 
        }
        i+=1
    }
    circuits.sortByDescending {it.size}
    //println(circuits)

	return circuits[0].size * circuits[1].size * circuits[2].size
}


fun main() {
    
    var t1 = System.currentTimeMillis()
 
//  solution for part 1
	val solution1 = playGround(1)
    println("*******************************")
    println("--- Day 8: Playground ---")
    println("*******************************")
    println("Solution for part1")
    println("   you get $solution1 if you multiply together the sizes of the three largest circuits")
    println() 

/*/  solution for part 2
	val solution2 = cafeteria(2)
    println("*******************************")
    println("Solution for part2")        
    println("   $solution2 IDs are considered as fresh ")
    println() */

    t1 = System.currentTimeMillis() - t1
    println("puzzle solved in ${t1} ms")
}
