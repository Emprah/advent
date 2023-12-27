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

    fun getCardCountWithDuplication(input: List<String>): Any {
        val cardsWithCounts = input
            .map { toScratchcard(it) }
            .map { Pair(it, 1) }
            .toMutableList()

        for ((i, cardWithCount) in cardsWithCounts.withIndex()) {
            if (cardWithCount.first.hits > 0) {
                for (j in IntRange(1, cardWithCount.first.hits)) {
                    val entryToUpdate = cardsWithCounts.getOrNull(i + j)

                    if (entryToUpdate != null) {
                        cardsWithCounts[i + j] = entryToUpdate.copy(second = entryToUpdate.second + cardWithCount.second)
                    }
                }
            }
        }

        return cardsWithCounts
            .sumOf { it.second }
    }

    private fun toScratchcard(line: String): Scratchcard {
        val sections = line.split(':', '|')

        val id = sections[0].allNumbers().first()
        val winningNumbers = sections[1].allNumbers().toSet()
        val guesses = sections[2].allNumbers().toSet()

        return Scratchcard(id, winningNumbers, guesses)
    }

    private fun calculatePoints(card: Scratchcard): Int {
        return if (card.hits == 0)
            0
        else
            2.0.pow(card.hits - 1).toInt()
    }

}

private data class Scratchcard(
    val id: Int,
    val winningNumbers: Set<Int>,
    val guesses: Set<Int>
) {
    val hits = winningNumbers.intersect(guesses).size
}