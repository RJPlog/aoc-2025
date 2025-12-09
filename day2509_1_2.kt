import java.io.File
import kotlin.math.*

data class Line(
	val xMin: Long,
    val xMax: Long,
    val yMin: Long,
    val yMax: Long
)

fun intersect(in1: Line, in2: Line): Int{
    // nice idea, but does not do the trick, still some other cases are possible which are not covered here
    // point xMin, yMin
    if ((in1.xMin < in2.xMax && in1.xMin > in2.xMin) && (in1.yMin < in2.yMax && in1.yMin > in2.yMin)) return 1
    // point xMax, yMin
    if ((in1.xMax < in2.xMax && in1.xMin > in2.xMin) && (in1.yMin < in2.yMax && in1.yMin > in2.yMin)) return 1
    // point xMin, yMax
    if ((in1.xMin < in2.xMax && in1.xMin > in2.xMin) && (in1.yMax < in2.yMax && in1.yMin > in2.yMin)) return 1
    // point xMax, yMax
    if ((in1.xMax < in2.xMax && in1.xMin > in2.xMin) && (in1.yMax < in2.yMax && in1.yMin > in2.yMin)) return 1
    return 0
}

fun movieTheater(in1: Int): Long {
    val redTiles = mutableListOf<Pair<Long,Long>>()
    
 val   puzzleInput = """7,1
11,1
11,7
9,7
9,5
2,5
2,3
7,3"""
     
    puzzleInput.split("\n").forEach{
        redTiles.add(Pair(it.split(",")[0].toLong(), it.split(",")[1].toLong()))
    }
    
    // create list of outlines for part2
    //if (in1 == 2) {
    var outLines = mutableListOf<Line>()
        redTiles.windowed(2) {
            val minX = minOf(it[0].first, it[1].first)
            val maxX = maxOf(it[0].first, it[1].first)
            val minY = minOf(it[0].second, it[1].second)
            val maxY = maxOf(it[0].second, it[1].second)
            outLines.add(Line(minX, maxX, minY, maxY))
        }
        outLines.add(Line(minOf(redTiles[0].first, redTiles[redTiles.size-1].first), maxOf(redTiles[0].first, redTiles[redTiles.size-1].first), minOf(redTiles[0].second, redTiles[redTiles.size-1].second), maxOf(redTiles[0].second, redTiles[redTiles.size-1].second)))
    //}
  
    var maxRectangle = 0L
    for (i in 0..redTiles.size-1) {
        for (j in i+1..redTiles.size-1) {
            val volume = (abs(redTiles[i].first - redTiles[j].first)+1) * (abs(redTiles[i].second - redTiles[j].second)+1)
            if (volume > maxRectangle) {
                // add check for part 2
                if (in1 == 2) {
                    val minXRec = minOf(redTiles[i].first, redTiles[j].first)
                	val maxXRec = maxOf(redTiles[i].first, redTiles[j].first)
               	 	val minYRec = minOf(redTiles[i].second, redTiles[j].second)
                	val maxYRec = maxOf(redTiles[i].second, redTiles[j].second)
                    val currentTile = Line(minXRec, maxXRec,minYRec, maxYRec)
                    //println(currentTile)
                    // check if current rectangle has any crossing with outline (does not cover a rectangle outside!)
                    var intersectionDetected = 0
                    outLines.forEach {
                        //println("   $it")
                        intersectionDetected += intersect(it, currentTile)
                    }
                    if (intersectionDetected == 0) {
                    maxRectangle = volume
                    }    
                } else
                maxRectangle = volume
            }
        }
    }
	return maxRectangle
}



fun main() {
    
    var t1 = System.currentTimeMillis()
 
//  solution for part 1
	val solution1 = movieTheater(1)
    println("*******************************")
    println("--- Day 9: Movie Theater ---")
    println("*******************************")
    println("Solution for part1")
    println("   The largest rectancle is $solution1.")  // 6833708340 is to high
    println() 

//  solution for part 2
	val solution2 = movieTheater(2)
    println("*******************************")
    println("Solution for part2")        
    println("   The largest rectancle with only red and green tiles is $solution2.")  // 17794166775 -> to high
    println() 

    t1 = System.currentTimeMillis() - t1
    println("puzzle solved in ${t1} ms")
}
