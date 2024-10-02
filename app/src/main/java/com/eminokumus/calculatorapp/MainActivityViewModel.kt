package com.eminokumus.calculatorapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class Operations(val sign: String){
    SUBTRACT("-"), ADD("+"), MULTIPLY("*"), DIVIDE("/")
}

class MainActivityViewModel : ViewModel() {
    private val _input = MutableLiveData<String>()
    val input: LiveData<String> get() = _input

    private var isLastNumeric = false
    private var isIncludeDot = false
    private var operation = Operations.SUBTRACT
    private var firstNumber = ""
    private var secondNumber =""

    init {
        _input.value = ""
    }

    fun onDigitClicked(digit: CharSequence) {
        _input.value += digit
        isLastNumeric = true
    }

    fun onClearClicked() {
        _input.value = ""
        isLastNumeric = false
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

    fun onEqualClicked(){
        if (isLastNumeric){
            findResult()
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

    private fun findResult(){
        findNumbers()
        var result = 0.0
        if(operation == Operations.SUBTRACT){
            result = firstNumber.toDouble() - secondNumber.toDouble()
        } else if (operation == Operations.ADD){
            result = firstNumber.toDouble() + secondNumber.toDouble()
        }else if (operation == Operations.MULTIPLY){
            result = firstNumber.toDouble() * secondNumber.toDouble()
        }else if (operation == Operations.DIVIDE){
            result = firstNumber.toDouble() / secondNumber.toDouble()
        }
        _input.value = result.toString()

    }

    private fun findNumbers(){
        if (checkIfInputStartsWithMinus()){
            val newInput = _input.value?.substring(1) ?: ""
            val splitValues = splitNumbersAccordingToOperation(newInput)
            firstNumber = Operations.SUBTRACT.sign + splitValues[0]
            secondNumber = splitValues[1]
        }else{
            val splitValues = splitNumbersAccordingToOperation(_input.value.toString())
            firstNumber = splitValues[0]
            secondNumber = splitValues[1]
        }
    }
    private fun splitNumbersAccordingToOperation(input: String): List<String>{
        if (input.contains(Operations.SUBTRACT.sign)){
            operation = Operations.SUBTRACT
            return input.split(Operations.SUBTRACT.sign)
        }else if(input.contains(Operations.ADD.sign)){
            operation = Operations.ADD
            return input.split(Operations.ADD.sign)
        }else if(input.contains(Operations.MULTIPLY.sign)){
            operation = Operations.MULTIPLY
            return input.split(Operations.MULTIPLY.sign)
        }else if(input.contains(Operations.DIVIDE.sign)){
            operation = Operations.DIVIDE
            return input.split(Operations.DIVIDE.sign)
        }else{
            return listOf()
        }
    }

    private fun checkIfInputStartsWithMinus(): Boolean{
        return _input.value?.startsWith(Operations.SUBTRACT.sign) ?: false
    }








}