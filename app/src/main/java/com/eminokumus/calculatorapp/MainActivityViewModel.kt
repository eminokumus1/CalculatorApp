package com.eminokumus.calculatorapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    private val _input = MutableLiveData<String>()
    val input: LiveData<String> get() = _input

    private var isLastNumeric = false
    private var isIncludeDot = false

    init {
        _input.value = ""
    }

    fun onDigitClicked(digit: CharSequence) {
        _input.value += digit
        isLastNumeric = true
    }

    fun onClearClicked() {
        _input.value = ""
    }

    fun onDotClicked() {
        if (isLastNumeric && !isIncludeDot) {
            _input.value += "."
            isIncludeDot = true
            isLastNumeric = false
        }
    }

    fun onOperationClicked(operation: CharSequence) {
        if (isLastNumeric && !isOperatorAdded(_input.value.toString())){
            _input.value += operation
            isLastNumeric = false
        }
    }

    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") ||
                    value.contains("*") ||
                    value.contains("+") ||
                    value.contains("-")
        }
    }
}