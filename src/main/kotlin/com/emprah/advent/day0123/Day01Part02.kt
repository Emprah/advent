package com.emprah.advent.day0123

import com.emprah.advent.Puzzle
import com.emprah.advent.util.FileService
import org.springframework.stereotype.Component

private const val taskId = "Day01Part02"

@Component(taskId)
internal class Day01Part02 (
    private val fileService: FileService,
    private val calculator: CalibrationCalculator
) : Puzzle() {

    private val inputFile = "input.txt"
    private val dayPath = "23/day01/"

    override fun solve() {
        println("Solving $taskId")

        val fileContent = fileService
            .getFile(inputFile, dayPath)
            ?.bufferedReader()?.readLines()

        if (fileContent == null) {
            println("Input file could not be read")
            return
        }

        val calibrationSum = calculator.calculateCalibrationExt(fileContent)
        println("Calibration sum is $calibrationSum")
    }
}