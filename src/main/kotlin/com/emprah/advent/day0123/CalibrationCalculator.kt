package com.emprah.advent.day0123

import org.springframework.stereotype.Service

@Service
internal object CalibrationCalculator {

    fun calculateCalibrationDigitsOnly(input: List<String>) =
        input
            .map { getLineValueDigitsOnly(it) }
            .sum()

    private fun getLineValueDigitsOnly(line: String) : Int {
        val firstDigit = line.find { it.isDigit() }
        val lastDigit = line.findLast { it.isDigit() }

        try {
            return String(listOfNotNull(firstDigit, lastDigit).toCharArray())
                .toInt(10)
        } catch (e: NumberFormatException) {
            return 0
        }
    }

    fun calculateCalibrationExt(input: List<String>) =
        input
            .map { getLineValueExt(it) }
            .sum()

    private fun getLineValueExt(line: String) : Int {
        val validDigitsMapped = mapOf("one" to 1, "two" to 2, "three" to 3, "four" to 4, "five" to 5, "six" to 6, "seven" to 7, "eight" to 8, "nine" to 9)
        val validDigits = validDigitsMapped.keys as Collection<String> + validDigitsMapped.values.map { it.toString() } + "0"
        
        val firstMatch = line.findAnyOf(validDigits)?.second
        val lastMatch = line.findLastAnyOf(validDigits)?.second
        val firstDigit = if (firstMatch in validDigitsMapped.keys) validDigitsMapped[firstMatch]?.digitToChar(10) else firstMatch?.get(0)
        val lastDigit = if (lastMatch in validDigitsMapped.keys) validDigitsMapped[lastMatch]?.digitToChar(10) else lastMatch?.get(0)

        try {
            return String(listOfNotNull(firstDigit, lastDigit).toCharArray())
                .toInt(10)
        } catch (e: NumberFormatException) {
            return 0
        }
    }
}