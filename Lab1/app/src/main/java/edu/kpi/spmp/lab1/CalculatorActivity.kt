package edu.kpi.spmp.lab1

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.TypedValue
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_calculator.*

class CalculatorActivity : AppCompatActivity() {
    private var currentOperation: Operation = Operation.ADDITION

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
    }

    fun setOperation(view: View) {
        val operationSign: String
        when (view.id) {
            R.id.plus_button -> {
                operationSign = getString(R.string.addition_sign)
                currentOperation = Operation.ADDITION
            }
            R.id.minus_button -> {
                operationSign = getString(R.string.subtraction_sign)
                currentOperation = Operation.SUBTRACTION
            }
            R.id.mult_button -> {
                operationSign = getString(R.string.multiplication_sign)
                currentOperation = Operation.MULTIPLICATION
            }
            R.id.divide_button -> {
                operationSign = getString(R.string.division_sign)
                currentOperation = Operation.DIVISION
            }
            else -> operationSign = ""
        }
        operation.text = operationSign
    }

    fun getResult(view: View) {
        val aText = number1.text.toString()
        val bText = number2.text.toString()
        val warningToast: Toast
        if (aText.isEmpty()) {
            warningToast = Toast.makeText(this, getString(R.string.input_warning) + " first operand!", Toast.LENGTH_SHORT)
            warningToast.show()
            return
        } else if (bText.isEmpty()) {
            warningToast = Toast.makeText(this, getString(R.string.input_warning) + " second operand!", Toast.LENGTH_SHORT)
            warningToast.show()
            return
        }

        val a: Double = aText.toDouble()
        val b: Double = bText.toDouble()
        result.text = when (currentOperation) {
            Operation.ADDITION -> (a + b).toString()
            Operation.SUBTRACTION -> (a - b).toString()
            Operation.MULTIPLICATION -> (a * b).toString()
            Operation.DIVISION -> String.format("%.5f", (a / b))

        }
        if(result.text.length>9)
            result.setTextSize(TypedValue.COMPLEX_UNIT_SP,54*9f/result.text.length)
        else
            result.setTextSize(TypedValue.COMPLEX_UNIT_SP,54f)
    }

    fun clearAll(view: View) {
        number1.text.clear()
        number2.text.clear()
        result.text = getString(R.string.result_text)
        result.setTextSize(TypedValue.COMPLEX_UNIT_SP,54f)
    }
}
