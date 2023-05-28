package com.cal.calculatori

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.cal.calcapp.R


class MainActivity : AppCompatActivity() {
    private var textView: TextView? = null
    private var textView2: TextView? = null
    private var currentNumber: String? = null
    private var operator: String? = null
    private var result = 0.0

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            textView2 = findViewById(R.id.textView2)

            textView = findViewById(R.id.textView)
            currentNumber = ""
            operator = ""
            result = 0.0

            val buttonAC = findViewById<Button>(R.id.button1)
            buttonAC.setOnClickListener { clear() }

            val buttonPlusMinus = findViewById<Button>(R.id.button2)
            buttonPlusMinus.setOnClickListener { negateNumber() }

            val buttonBackspace = findViewById<Button>(R.id.button3)
            buttonBackspace.setOnClickListener { deleteLastDigit() }

            val buttonDivide = findViewById<Button>(R.id.button4)
            buttonDivide.setOnClickListener { setOperator("/") }

            val buttonMultiply = findViewById<Button>(R.id.button8)
            buttonMultiply.setOnClickListener { setOperator("*") }

            val buttonSubtract = findViewById<Button>(R.id.button12)
            buttonSubtract.setOnClickListener { setOperator("-") }

            val buttonAdd = findViewById<Button>(R.id.button16)
            buttonAdd.setOnClickListener { setOperator("+") }

            val buttonEquals = findViewById<Button>(R.id.button19)
            buttonEquals.setOnClickListener { calculateResult() }

            val buttonDecimal = findViewById<Button>(R.id.button18)
            buttonDecimal.setOnClickListener { appendNumber(".") }

            val button0 = findViewById<Button>(R.id.button17)
            button0.setOnClickListener { appendNumber("0") }

            val button1 = findViewById<Button>(R.id.button15)
            button1.setOnClickListener { appendNumber("1") }

            val button2 = findViewById<Button>(R.id.button14)
            button2.setOnClickListener { appendNumber("2") }

            val button3 = findViewById<Button>(R.id.button13)
            button3.setOnClickListener { appendNumber("3") }

            val button4 = findViewById<Button>(R.id.button9)
            button4.setOnClickListener { appendNumber("4") }

            val button5 = findViewById<Button>(R.id.button10)
            button5.setOnClickListener { appendNumber("5") }

            val button6 = findViewById<Button>(R.id.button11)
            button6.setOnClickListener { appendNumber("6") }

            val button7 = findViewById<Button>(R.id.button5)
            button7.setOnClickListener { appendNumber("7") }

            val button8 = findViewById<Button>(R.id.button6)
            button8.setOnClickListener { appendNumber("8") }

            val button9 = findViewById<Button>(R.id.button7)
            button9.setOnClickListener { appendNumber("9") }
        }

        private fun clear() {
            currentNumber = ""
            operator = ""
            result = 0.0
            textView2!!.text = "0"
        }

        private fun negateNumber() {
            if (!currentNumber.isNullOrEmpty()) {
                var number = currentNumber!!.toDouble()
                number = -number
                currentNumber = number.toString()
                updateTextView()
            }
        }

        private fun deleteLastDigit() {
            if (!currentNumber.isNullOrEmpty()) {
                currentNumber = currentNumber!!.substring(0, currentNumber!!.length - 1)
                if (currentNumber.isNullOrEmpty()) currentNumber = "0"
                updateTextView()
            }
        }

        private fun setOperator(operator: String) {
            if (!currentNumber.isNullOrEmpty()) {
                this.operator = operator
                result = currentNumber!!.toDouble()
                currentNumber = ""
                updateTextView()
            }
        }

    private fun appendNumber(number: String) {
        if (currentNumber == "0" && number == "0") return
        if (currentNumber == "0") currentNumber = ""
        currentNumber += number
        updateTextView()
    }

        private fun updateTextView() {
            if (!operator.isNullOrEmpty() && !currentNumber.isNullOrEmpty()) {
                textView2!!.text = "$currentNumber"


            } else {
                textView2!!.text = currentNumber
            }
        }


        private fun calculateResult() {
            if (!currentNumber.isNullOrEmpty() && !operator.isNullOrEmpty()) {
                val number = currentNumber!!.toDouble()
                val expression = "${textView2!!.text} $operator $currentNumber" // Create the expression string
                when (operator) {
                    "+" -> result += number
                    "-" -> result -= number
                    "*" -> result *= number
                    "/" -> {
                        if (number != 0.0) {
                            result /= number
                        } else {
                            // Handle division by zero error
                            textView2!!.text = "Error: Division by zero"
                            return
                        }
                    }
                }
                currentNumber = result.toString()
                textView!!.text = "=$currentNumber" // Display the expression and result in textView2
                operator = ""
            }
        }
    }
