package com.emprah.advent.util


object StringExtensions {

    fun String.matchesOf(pattern: String): Sequence<MatchResult> {
        val regex = pattern.toRegex()
        return regex.findAll(this)
    }

    fun String.allNumbers(): Sequence<Int> {
        val regex = """\d+""".toRegex()
        return regex
            .findAll(this)
            .map { it.value.toInt(10) }
    }
}