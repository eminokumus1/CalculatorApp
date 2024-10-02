package com.eminokumus.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eminokumus.calculatorapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = MainActivityViewModel()

        viewModel.input.observe(this) { newInput ->
            binding.inputText.text = newInput
        }
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        setDigitsOnClickListeners()
        setClearOnClickListener()
        setDotOnClickListener()
        setOperationsOnClickListeners()
        setEqualOnClickListener()
    }

    private fun setDigitsOnClickListeners() {
        binding.run {
            btnZero.setOnClickListener{viewModel.onDigitClicked(btnZero.text)}
            btnOne.setOnClickListener{viewModel.onDigitClicked(btnOne.text)}
            btnTwo.setOnClickListener{viewModel.onDigitClicked(btnTwo.text)}
            btnThree.setOnClickListener{viewModel.onDigitClicked(btnThree.text)}
            btnFour.setOnClickListener{viewModel.onDigitClicked(btnFour.text)}
            btnFive.setOnClickListener{viewModel.onDigitClicked(btnFive.text)}
            btnSix.setOnClickListener{viewModel.onDigitClicked(btnSix.text)}
            btnSeven.setOnClickListener{viewModel.onDigitClicked(btnSeven.text)}
            btnEight.setOnClickListener{viewModel.onDigitClicked(btnEight.text)}
            btnNine.setOnClickListener{viewModel.onDigitClicked(btnNine.text)}
        }
    }
    private fun setClearOnClickListener(){
        binding.btnClear.setOnClickListener{viewModel.onClearClicked()}
    }

    private fun setDotOnClickListener(){
        binding.btnDot.setOnClickListener{viewModel.onDotClicked()}
    }

    private fun setOperationsOnClickListeners(){
        binding.run {
            btnDivide.setOnClickListener{viewModel.onOperationClicked(btnDivide.text.toString())}
            btnMultiply.setOnClickListener{viewModel.onOperationClicked(btnMultiply.text.toString())}
            btnMinus.setOnClickListener{viewModel.onOperationClicked(btnMinus.text.toString())}
            btnPlus.setOnClickListener{viewModel.onOperationClicked(btnPlus.text.toString())}
        }
    }
    private fun setEqualOnClickListener(){
        binding.btnEqual.setOnClickListener{viewModel.onEqualClicked()}
    }
}