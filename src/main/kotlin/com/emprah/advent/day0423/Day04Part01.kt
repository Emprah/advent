package com.emprah.advent.day0423

import com.emprah.advent.Puzzle
import com.emprah.advent.util.FileService
import mu.KotlinLogging
import org.springframework.stereotype.Component

private const val taskId = "Day04Part01"
private val logger = KotlinLogging.logger {}

@Component(taskId)
class Day04Part01 (
    private val fileService: FileService,
    private val calculator: ScratchcardCalculator
) : Puzzle() {

    private val inputFile = "input.txt"
    private val dayPath = "23/day04/"

    override fun solve() {
        logger.info { "Solving $taskId" }

        val fileContent = fileService.getFile(inputFile, dayPath)
            ?.bufferedReader()?.readLines()

        if (fileContent == null) {
            logger.error { "Input file could not be read" }
            return
        }

        val cardPoints = calculator.getCardPoints(fileContent)
        logger.info { "Sum of all scratchcard points equals $cardPoints" }
    }
}