import java.io.File
import kotlin.math.*

fun giftShop(in1: Int): Long{
    var result = 0L

    var test = "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124"

    test.split(",").forEach {
        var start = it.split("-")[0].toLong()
        var end = it.split("-")[1].toLong()
        for (i in start..end) {
            var check = i.toString()
            if (check.length % 2 == 0) {
                val checkA = check.take(check.length/2)
                val checkB = check.takeLast(check.length/2)
                if (checkA == checkB) result += i
            }
        }
    }
    return result
}

fun main() {
    var t1 = System.currentTimeMillis()

    var solution1 = giftShop(1)
    //var solution2 = gitShop2(2)

// print solution for part 1
    println("*******************************")
    println("--- Day 2: Gift Shop ---")
    println("*******************************")
    println("Solution for part1")
    println("   you get $solution1 if you add up all of the invalid IDs")
    println()

// print solution for part 2
    //println("*******************************")
    //println("Solution for part2")
    //println("   Using password method 0x434C49434B $solution2 is the password to open the door")
    //println()

    t1 = System.currentTimeMillis() - t1
    println("puzzle solved in ${t1} ms")
}
