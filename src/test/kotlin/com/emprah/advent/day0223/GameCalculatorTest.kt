package com.emprah.advent.day0223

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class GameCalculatorTest {

    companion object {
        @JvmStatic
        private fun validGamesParams() = Stream.of(
            Arguments.of(
                "Too many red, green and blue cubes",
                listOf("Game 1: 20 red, 2 green; 1 red, 1 green, 2 blue; 3 blue, 3 red, 30 green; 1 blue, 3 green, 7 red; 5 red, 3 green, 100 blue"),
                0),
            Arguments.of(
                "Too many red cubes",
                listOf("Game 1: 20 red, 2 green; 1 red, 1 green, 2 blue; 3 blue, 3 red, 3 green; 1 blue, 3 green, 7 red; 5 red, 3 green, 1 blue"),
                0
            ),
            Arguments.of(
                "Too many green cubes",
                listOf("Game 1: 2 red, 2 green; 1 red, 1 green, 2 blue; 3 blue, 3 red, 30 green; 1 blue, 3 green, 7 red; 5 red, 3 green, 1 blue"),
                0
            ),
            Arguments.of(
                "Too many blue cubes",
                listOf("Game 1: 2 red, 2 green; 1 red, 1 green, 2 blue; 3 blue, 3 red, 3 green; 1 blue, 3 green, 7 red; 5 red, 3 green, 100 blue"),
                0
            ),
            Arguments.of(
                "Valid number of red, green and blue cubes",
                listOf("Game 1: 2 red, 2 green; 12 red, 1 green, 2 blue; 3 blue, 3 red, 3 green; 1 blue, 3 green, 7 red; 5 red, 3 green, 1 blue"),
                1
            ),
            Arguments.of(
                "Multiple games as input",
                listOf(
                    "Game 1: 2 red, 2 green; 1 red, 1 green, 2 blue; 3 blue, 3 red, 3 green; 1 blue, 3 green, 7 red; 5 red, 3 green, 1 blue",
                    "Game 2: 5 green, 4 red, 7 blue; 7 red, 4 green, 4 blue; 8 green, 11 blue, 4 red; 2 red, 18 blue, 3 green; 7 red, 15 blue",
                    "Game 3: 2 green, 4 blue; 2 red, 2 green; 6 red, 1 green; 2 red, 1 green; 2 green; 5 blue, 5 red",
                    "Game 4: Invalid line format"),
                4
            ),
        )
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("validGamesParams")
    fun calcValidGames(testcase: String, input: List<String>, expectedResult: Int) {
        val sut = GameCalculator
        val result = sut.getSumOfValidGameIds(input)
        assertThat(result).isEqualTo(expectedResult)
    }
}
