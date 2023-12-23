package com.emprah.advent

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class PuzzleApplicationRunner(private val puzzle: Puzzle) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        println("Running puzzle")
        puzzle.solve()
    }
}