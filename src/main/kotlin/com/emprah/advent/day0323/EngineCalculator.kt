package com.emprah.advent.day0323

import com.emprah.advent.util.StringExtensions.matchesOf
import org.springframework.stereotype.Service

@Service
object EngineCalculator {

    fun getSumOfValidPartNumbers(fileContent: List<String>): Int {
        val (partNumbers, symbols) = parseEngineSpec(fileContent)

        return partNumbers
            .filter { isValidPart(it, symbols) }
            .sumOf { it.value }
    }

    fun getSumOfGearRatios(fileContent: List<String>): Int {
        val (partNumbers, symbols) = parseEngineSpec(fileContent)

        return symbols
            .mapNotNull { asGear(it, partNumbers) }
            .sumOf { it.ratio }
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

    private fun isValidPart(part: PartNumber, symbols: List<Symbol>): Boolean {
        return symbols
            .filter { it.line == part.line - 1 || it.line == part.line || it.line == part.line + 1 }
            .filter { part.column - 1 <= it.column && it.column <= part.lastColumn + 1 }
            .isNotEmpty()
    }
    
    private fun asGear(symbol: Symbol, parts: List<PartNumber>): Gear? {
        if (symbol.value != '*')
            return null
        
        val connectedParts = parts
            .filter { it.line == symbol.line - 1 || it.line == symbol.line || it.line == symbol.line + 1 }
            .filter { symbol.column in IntRange(it.column - 1, it.lastColumn + 1) }

        return if (connectedParts.size == 2)
            Gear(connectedParts[0].value * connectedParts[1].value)
        else
            null
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

private data class Symbol (
    val value: Char,
    val line: Int,
    val column: Int,
)

private data class Gear (
    val ratio: Int
)