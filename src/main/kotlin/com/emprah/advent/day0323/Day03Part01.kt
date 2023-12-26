package com.emprah.advent.day0323

import com.emprah.advent.Puzzle
import com.emprah.advent.util.FileService
import mu.KotlinLogging
import org.springframework.stereotype.Component

private const val taskId = "Day03Part01"
private val logger = KotlinLogging.logger {}

@Component(taskId)
class Day03Part01 (
    private val fileService: FileService,
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

        val sumOfPartNumbers = getSumOfValidPartNumbers(fileContent)
        logger.info { "Sum of all valid part numbers equals $sumOfPartNumbers" }
    }

    private fun getSumOfValidPartNumbers(fileContent: List<String>): Int {
        val (partNumbers, symbols) = parseEngineSpec(fileContent)

        return partNumbers
            .filter { isValidPart(it, symbols) }
            .sumOf { it.value }
    }

    private fun parseEngineSpec(input: List<String>): EngineSpec {
        val partNumbers = mutableListOf<PartNumber>()
        val symbols = mutableListOf<Symbol>()

        for ((lineNumber, line) in input.withIndex()) {
            for (numberMatch in line.matchesOf("""\d+""")) {
                partNumbers.add(
                    PartNumber(numberMatch.value.toInt(10), lineNumber, numberMatch.range.first))
            }
            for (symbolMatch in line.matchesOf("""[^\d.]""")) {
                symbols.add(
                    Symbol(symbolMatch.value[0], lineNumber, symbolMatch.range.first))
            }
        }

        return EngineSpec(partNumbers, symbols)
    }

    private fun String.matchesOf(pattern: String): Sequence<MatchResult> {
        val regex = pattern.toRegex()
        return regex.findAll(this)
    }

    private fun isValidPart(part: PartNumber, symbols: List<Symbol>): Boolean {
        return symbols
            .filter { it.line == part.line - 1 || it.line == part.line || it.line == part.line + 1 }
            .filter { part.column - 1 <= it.column && it.column <= part.lastColumn + 1 }
            .isNotEmpty()
    }
}

private data class EngineSpec (
    val partNumbers: List<PartNumber>,
    val symbols: List<Symbol>,
)

private data class PartNumber (
    val value: Int,
    val line: Int,
    val column: Int,
) {
    val lastColumn = column + value.toString().length - 1
}

private data class Symbol(
    val value: Char,
    val line: Int,
    val column: Int,
)
