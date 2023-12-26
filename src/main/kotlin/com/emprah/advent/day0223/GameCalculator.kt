package com.emprah.advent.day0223

import mu.KotlinLogging
import org.springframework.stereotype.Service
import kotlin.math.max

private val logger = KotlinLogging.logger {}

@Service
object GameCalculator {

    fun getSumOfValidGameIds(fileContent: List<String>) : Int {
        val availableRedCubes = 12
        val availableGreenCubes = 13
        val availableBlueCubes = 14

        return fileContent
            .mapNotNull { parseLine(it) }
            .filter { it.maxRedCubes <= availableRedCubes && it.maxGreenCubes <= availableGreenCubes && it.maxBlueCubes <= availableBlueCubes }
            .fold(0) { acc, game -> acc + game.id }

    }

    private fun parseLine(line: String): Game? {
        val gamePattern = """Game (\d+):""".toRegex()
        val gameMatch = gamePattern.find(line)
        val cubeValuePattern = """(\d+) (red|green|blue)""".toRegex()
        val cubeValueMatches = cubeValuePattern.findAll(line).toList()

        if (gameMatch == null || cubeValueMatches.isEmpty()) {
            logger.error { "Could not parse the following line as valid game: $line" }
            return null
        }

        return mapToGame(gameMatch, cubeValueMatches)
    }

    private fun mapToGame(gameMatch: MatchResult, cubeValueMatches: List<MatchResult>): Game? {
        var maxRedCubes = 0
        var maxGreenCubes = 0
        var maxBlueCubes = 0

        try {
            for (match in cubeValueMatches) {
                when (match.groupValues[2]) {
                    "red" -> maxRedCubes = max(maxRedCubes, match.groupValues[1].toInt())
                    "green" -> maxGreenCubes = max(maxGreenCubes, match.groupValues[1].toInt())
                    "blue" -> maxBlueCubes = max(maxBlueCubes, match.groupValues[1].toInt())
                }
            }

            return Game(
                gameMatch.groupValues[1].toInt(),
                maxRedCubes,
                maxGreenCubes,
                maxBlueCubes)

        } catch (e: NumberFormatException) {
            logger.error(e) { "Unexpected non-number value found" }
            return null
        }
    }

    fun getMinimalCubesValue(fileContent: List<String>): Any {
        return fileContent
            .mapNotNull { parseLine(it) }
            .sumOf { it.maxRedCubes * it.maxGreenCubes * it.maxBlueCubes }
    }
}

private data class Game(val id: Int, val maxRedCubes: Int, val maxGreenCubes: Int, val maxBlueCubes: Int)
