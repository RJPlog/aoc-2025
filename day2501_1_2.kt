import java.io.File
import kotlin.math.*

fun passWord(in1: Int): Int {
    var position = 50
    var count = 0

    File("day2501_puzzle_input.txt").forEachLine {
        var steps = it.drop(1).toInt() % 100
        if (it.first() == 'R') {
            position = (position + steps) % 100
        } else {
            position = (position + (100-steps)) % 100
        }
        if (position == 0) count +=1
    }
    return count
}

fun passWord2(in1: Int): Int {
    var position = 50
    var count = 0

    File("day2501_puzzle_input.txt").forEachLine {
        var steps = it.drop(1).toInt()
        if (it.first() == 'R') {
			repeat(steps) {
				position += 1
				if (position == 100) position = 0
				if (position == 0) count += 1
			}
        } else {
			repeat(steps) {
				position -= 1
				if (position == -1) position = 99
				if (position == 0) count += 1					
			}
        }
    }
    return count
}

fun main() {
    var t1 = System.currentTimeMillis()

    var solution1 = passWord(1)
    var solution2 = passWord2(2)

// print solution for part 1
    println("*******************************")
    println("--- Day 1: Secret Entrance ---")
    println("*******************************")
    println("Solution for part1")
    println("   $solution1 is the number of times the dial is left pointing at 0")
    println()

// print solution for part 2
    println("*******************************")
    println("Solution for part2")
    println("   Using password method 0x434C49434B $solution2 is the password to open the door")
    println()

    t1 = System.currentTimeMillis() - t1
    println("puzzle solved in ${t1} ms")
}
