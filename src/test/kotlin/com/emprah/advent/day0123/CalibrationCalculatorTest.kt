package com.emprah.advent.day0123

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class CalibrationCalculatorTest {

    companion object {
        @JvmStatic
        private fun calibrationParams() = Stream.of(
            Arguments.of("Empty line", listOf(""), 0),
            Arguments.of("Line without digits", listOf("sdf"), 0),
            Arguments.of("Line with single digit", listOf("sd5"), 55),
            Arguments.of("Line with more than two digits", listOf("j3sdf5sd4f"), 34),
            Arguments.of("Multiple lines", listOf("", "sdf", "sd5", "j3sdf5sd4f"), 89),
        )
    }
    
    @ParameterizedTest(name = "{0}")
    @MethodSource("calibrationParams")
    fun calcCalibration(testcase: String, input: List<String>, expectedResult: Int) {
        val sut = CalibrationCalculator
        val result = sut.calculateCalibration(input)
        assertThat(result).isEqualTo(expectedResult)
    }
}
