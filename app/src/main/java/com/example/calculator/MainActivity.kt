package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var mathOperation: TextView
    private lateinit var resultText: TextView
    private var currentInput: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mathOperation = findViewById(R.id.math_operation)
        resultText = findViewById(R.id.result_text)

        setupButton(R.id.button_clear) { clear() }
        setupButton(R.id.button_div) { appendToInput(" / ") }
        setupButton(R.id.button_multiply) { appendToInput(" * ") }
        setupButton(R.id.button_subtract) { appendToInput(" - ") }
        setupButton(R.id.button_add) { appendToInput(" + ") }
        setupButton(R.id.button7) { appendToInput("7") }
        setupButton(R.id.button8) { appendToInput("8") }
        setupButton(R.id.button9) { appendToInput("9") }
        setupButton(R.id.button4) { appendToInput("4") }
        setupButton(R.id.button5) { appendToInput("5") }
        setupButton(R.id.button6) { appendToInput("6") }
        setupButton(R.id.button1) { appendToInput("1") }
        setupButton(R.id.button2) { appendToInput("2") }
        setupButton(R.id.button3) { appendToInput("3") }
        setupButton(R.id.button0) { appendToInput("0") }
        setupButton(R.id.buttonequals) { calculateResult() }
    }

    private fun setupButton(buttonId: Int, action: () -> Unit) {
        findViewById<Button>(buttonId).setOnClickListener { action() }
    }

    private fun appendToInput(value: String) {
        currentInput += value
        mathOperation.text = currentInput
    }

    private fun clear() {
        currentInput = ""
        mathOperation.text = ""
        resultText.text = ""
    }

    private fun calculateResult() {
        if (currentInput.isNotEmpty()) {
            try {
                val result = eval(currentInput)
                resultText.text = result.toString()
            } catch (e: Exception) {
                resultText.text = "Ошибка"
            }
        }
    }

    private fun eval(expression: String): Double {
        val parts = expression.split(" ")
        var result = parts[0].toDouble()
        var operator = ""

        for (i in 1 until parts.size) {
            if (i % 2 == 1) {
                operator = parts[i]
            } else {
                val value = parts[i].toDouble()
                result = when (operator) {
                    "+" -> result + value
                    "-" -> result - value
                    "*" -> result * value
                    "/" -> if (value != 0.0) result / value else Double.NaN
                    else -> result
                }
            }
        }
        return result
    }
}