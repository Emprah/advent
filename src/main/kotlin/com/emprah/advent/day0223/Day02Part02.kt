package com.emprah.advent.day0223

import com.emprah.advent.Puzzle
import com.emprah.advent.util.FileService
import mu.KotlinLogging
import org.springframework.stereotype.Component

private const val taskId = "Day02Part02"
private val logger = KotlinLogging.logger {}

@Component(taskId)
class Day02Part02 (
    private val fileService: FileService,
    private val gameCalculator: GameCalculator
) : Puzzle() {

    private val inputFile = "input.txt"
    private val dayPath = "23/day02/"

    override fun solve() {
        logger.info { "Solving $taskId" }

        val fileContent = fileService.getFile(inputFile, dayPath)
            ?.bufferedReader()?.readLines()

        if (fileContent == null) {
            logger.error { "Input file could not be read" }
            return
        }

        val summedGameIds = gameCalculator.getMinimalCubesValue(fileContent)
        logger.info { "Sum of power of minimum amount of cubes necessary for each game equals $summedGameIds" }
    }
}
