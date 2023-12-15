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

    private fun sortedList(list: MutableList<String>): String {
        val numberList = mutableListOf<Int>()
        val stringList = mutableListOf<String>()
        list.forEach { if (it.toIntOrNull() != null) numberList.add(it.toInt()) else stringList.add(it) }
        val newList = numberList.sorted() + stringList.sorted()
        return newList.joinToString(separator = ", ")
    }

    fun handleUnion() {
        val arrayA = _joiner.setA.split(",")
        val arrayB = _joiner.setB.split(",")
        val newList = mutableListOf<String>()
        arrayA.forEach { newList.add(it) }

        arrayB.forEach { if (!newList.contains(it)) newList.add(it) }

        _joiner.union = sortedList(newList)
    }

    fun handleIntersection() {
        val arrayA = _joiner.setA.split(",")
        val arrayB = _joiner.setB.split(",")
        val newList = mutableListOf<String>()

        arrayB.forEach {if (arrayA.contains(it)) newList.add(it)}

        _joiner.intersection = sortedList(newList)
    }

    private fun differenceBetween(a: String, b: String): String {
        val arrayA = a.split(",")
        val arrayB = b.split(",")
        val newList = mutableListOf<String>()
        arrayA.forEach { newList.add(it) }

        arrayB.forEach {if (arrayA.contains(it)) newList.remove(it)}

        return sortedList(newList)
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