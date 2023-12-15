package com.luancosta.conjuntix

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SetViewModel: ViewModel() {
    private val _repository: SetRepository = SetRepository()

    private val _setA = mutableStateOf(_repository.getJoiner().setA)
    private val _setB = mutableStateOf(_repository.getJoiner().setB)
    private val _union = mutableStateOf(_repository.getJoiner().union)
    private val _intersection = mutableStateOf(_repository.getJoiner().intersection)
    private val _differenceAB = mutableStateOf(_repository.getJoiner().differenceAB)
    private val _differenceBA = mutableStateOf(_repository.getJoiner().differenceBA)


    val setA: MutableState<String> = _setA
    val setB: MutableState<String> = _setB
    val union: MutableState<String> = _union
    val intersection: MutableState<String> = _intersection
    val differenceAB: MutableState<String> = _differenceAB
    val differenceBA: MutableState<String> = _differenceBA

    fun handleSetA(set: String) {
        _repository.updateSetA(set)
        _setA.value = _repository.getJoiner().setA
    }

    fun handleSetB(set: String) {
        _repository.updateSetB(set)
        _setB.value = _repository.getJoiner().setB
    }

    fun union() {
        _repository.handleUnion()
        _union.value = _repository.getJoiner().union
    }

    fun intersection() {
        _repository.handleIntersection()
        _intersection.value = _repository.getJoiner().intersection
    }

    fun differenceAB() {
        _repository.handleDifferenceAB()
        _differenceAB.value = _repository.getJoiner().differenceAB
    }

    fun differenceBA() {
        _repository.handleDifferenceBA()
        _differenceBA.value = _repository.getJoiner().differenceBA
    }

    fun clearAll() {
        _repository.handleClearAll()
        _setA.value = _repository.getJoiner().setA
        _setB.value = _repository.getJoiner().setB
        _union.value = _repository.getJoiner().union
        _intersection.value = _repository.getJoiner().intersection
        _differenceAB.value = _repository.getJoiner().differenceAB
        _differenceBA.value = _repository.getJoiner().differenceBA
    }

    fun getAllOperation() {
        union()
        intersection()
        differenceAB()
        differenceBA()
    }
}