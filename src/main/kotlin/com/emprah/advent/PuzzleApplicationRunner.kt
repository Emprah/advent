package com.emprah.advent

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}

@Component
class PuzzleApplicationRunner(@Qualifier("Day04Part02") private val puzzle: Puzzle) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        logger.info{"Running puzzle"}
        puzzle.solve()
    }
}