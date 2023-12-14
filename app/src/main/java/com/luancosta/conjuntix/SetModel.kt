package com.luancosta.conjuntix

data class SetModel(
    var setA: String,
    var setB: String,
    var union: String,
    var intersection: String,
    var differenceAB: String,
    var differenceBA: String
)

class SetRepository {
    private var _joiner = SetModel(setA = "", setB = "", union = "", intersection = "", differenceAB = "", differenceBA = "")

    fun getJoiner() = _joiner

    fun updateSetA(set: String) {
        _joiner.setA = set
    }

    fun updateSetB(set: String) {
        _joiner.setB = set
    }

    fun sortSetA() {
        val newSet = _joiner.setA.split(",").map { it.trim() }.sorted()
        _joiner.setA = newSet.joinToString(separator = ",")
    }

    fun sortSetB() {
        val newSet = _joiner.setB.split(",").map { it.trim() }.sorted()
        _joiner.setB = newSet.joinToString(separator = ",")
    }

    fun handleUnion() {
        val arrayA = _joiner.setA.split(",")
        val arrayB = _joiner.setB.split(",")
        val newList = mutableListOf<String>()
        arrayA.forEach { newList.add(it) }

        arrayB.forEach { if (!newList.contains(it)) newList.add(it) }

        _joiner.union = newList.joinToString(separator = ", ")
    }

    fun handleIntersection() {
        val arrayA = _joiner.setA.split(",")
        val arrayB = _joiner.setB.split(",")
        val newList = mutableListOf<String>()

        arrayB.forEach {if (arrayA.contains(it)) newList.add(it)}

        _joiner.intersection = newList.joinToString(separator = ", ")
    }

    private fun differenceBetween(a: String, b: String): String {
        val arrayA = a.split(",")
        val arrayB = b.split(",")
        val newList = mutableListOf<String>()
        arrayA.forEach { newList.add(it) }

        arrayB.forEach {if (arrayA.contains(it)) newList.remove(it)}

        return newList.joinToString(separator = ", ")
    }

    fun handleDifferenceAB() {
        _joiner.differenceAB = differenceBetween(a = _joiner.setA, b = _joiner.setB)
    }

    fun handleDifferenceBA() {
        _joiner.differenceBA = differenceBetween(a = _joiner.setB, b = _joiner.setA)
    }

    fun handleClearAll() {
        _joiner.setA = ""
        _joiner.setB = ""
        _joiner.union = ""
        _joiner.intersection = ""
        _joiner.differenceAB = ""
        _joiner.differenceBA = ""
    }
}