package com.emprah.advent.util


object StringExtensions {

    fun String.matchesOf(pattern: String): Sequence<MatchResult> {
        val regex = pattern.toRegex()
        return regex.findAll(this)
    }
}