package com.emprah.advent.day0223

import com.emprah.advent.Puzzle
import com.emprah.advent.util.FileService
import mu.KotlinLogging
import org.springframework.stereotype.Component
import kotlin.math.max

private const val taskId = "Day02Part01"
private val logger = KotlinLogging.logger {}

@Component(taskId)
internal class Day02Part01(
    private val fileService: FileService,
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

        val summedGameIds = calculateSolution(fileContent)
        logger.info { "Sum of valid game ids equals $summedGameIds" }
    }

    private fun calculateSolution(fileContent: List<String>) : Int {
        val noRedCubes = 12
        val noGreenCubes = 13
        val noBlueCubes = 14

        return fileContent
            .mapNotNull { parseLine(it) }
            .filter { it.maxRedCubes <= noRedCubes && it.maxGreenCubes <= noGreenCubes && it.maxBlueCubes <= noBlueCubes }
            .fold(0) { acc, game -> acc + game.id }

    }

    private fun parseLine(line: String): Game? {
        val pattern = """Game (\d+): (?:(\d+) (red|green|blue).*)*""".toRegex()
        val matchResult = pattern.find(line)

        if (matchResult == null) {
            logger.error { "Could not parse the following line as valid game: $line" }
            return null
        }

        return mapToGame(matchResult)
    }

    private fun mapToGame(match: MatchResult): Game? {
        var maxRedCubes = 0
        var maxGreenCubes = 0
        var maxBlueCubes = 0

        try {
            for (index in 3 ..< match.groupValues.size step 2) {
                when(match.groupValues[index]) {
                    "red" -> maxRedCubes = max(maxRedCubes, match.groupValues[index - 1].toInt())
                    "green" -> maxGreenCubes = max(maxGreenCubes, match.groupValues[index - 1].toInt())
                    "blue" -> maxBlueCubes = max(maxBlueCubes, match.groupValues[index - 1].toInt())
                    else -> logger.error { "Did not find expected red|green|blue value, found ${match.groupValues[index]} instead" }
                }
            }

            return Game(
                match.groupValues[1].toInt(),
                maxRedCubes,
                maxGreenCubes,
                maxBlueCubes)

        } catch (e: NumberFormatException) {
            logger.error(e) { "Unexpected non-number value found" }
            return null
        }
    }
}

private data class Game(val id: Int, val maxRedCubes: Int, val maxGreenCubes: Int, val maxBlueCubes: Int)