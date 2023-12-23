package com.emprah.advent.day0123

import org.springframework.stereotype.Service

@Service
internal object CalibrationCalculator {

    fun calculateCalibration(input: List<String>) =
        input
            .map { getLineCalibrationValue(it) }
            .sum()

    private fun getLineCalibrationValue(line: String) : Int {
        val firstDigit = line.find { it.isDigit() }
        val lastDigit = line.findLast { it.isDigit() }

        try {
            return String(listOfNotNull(firstDigit, lastDigit).toCharArray())
                .toInt(10)
        } catch (e: NumberFormatException) {
            return 0
        }
    }
}