package com.emprah.advent.day0223

import com.emprah.advent.Puzzle
import com.emprah.advent.util.FileService
import mu.KotlinLogging
import org.springframework.stereotype.Component

private const val taskId = "Day02Part01"
private val logger = KotlinLogging.logger {}

@Component(taskId)
internal class Day02Part01(
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

        val summedGameIds = gameCalculator.getSumOfValidGameIds(fileContent)
        logger.info { "Sum of valid game ids equals $summedGameIds" }
    }
}
