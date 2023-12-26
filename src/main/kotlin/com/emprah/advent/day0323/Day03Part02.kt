package com.emprah.advent.day0323

import com.emprah.advent.Puzzle
import com.emprah.advent.util.FileService
import mu.KotlinLogging
import org.springframework.stereotype.Component

private const val taskId = "Day03Part02"
private val logger = KotlinLogging.logger {}

@Component(taskId)
class Day03Part02 (
    private val fileService: FileService,
    private val calculator: EngineCalculator
) : Puzzle() {

    private val inputFile = "input.txt"
    private val dayPath = "23/day03/"

    override fun solve() {
        logger.info { "Solving $taskId" }

        val fileContent = fileService.getFile(inputFile, dayPath)
            ?.bufferedReader()?.readLines()

        if (fileContent == null) {
            logger.error { "Input file could not be read" }
            return
        }

        val sumOfGearRatios = calculator.getSumOfGearRatios(fileContent)
        logger.info { "Sum of all gear ratios equals $sumOfGearRatios" }
    }


}