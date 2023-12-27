package com.emprah.advent.day0423

import com.emprah.advent.util.StringExtensions.allNumbers
import org.springframework.stereotype.Service
import kotlin.math.pow

@Service
class ScratchcardCalculator {

    fun getCardPoints(input: List<String>): Int {
        return input
            .map { toScratchcard(it) }
            .sumOf { calculatePoints(it) }
    }

    private fun toScratchcard(line: String): Scratchcard {
        val sections = line.split(':', '|')

        val id = sections[0].allNumbers().first()
        val winningNumbers = sections[1].allNumbers().toSet()
        val guesses = sections[2].allNumbers().toSet()

        return Scratchcard(id, winningNumbers, guesses)
    }

    private fun calculatePoints(card: Scratchcard): Int {
        val hits = card.winningNumbers.intersect(card.guesses).size

        return if (hits == 0)
            0
        else
            2.0.pow(hits - 1).toInt()
    }

}

private data class Scratchcard(
    val id: Int,
    val winningNumbers: Set<Int>,
    val guesses: Set<Int>
)