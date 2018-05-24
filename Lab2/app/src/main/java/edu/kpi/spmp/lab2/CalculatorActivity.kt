package edu.kpi.spmp.lab2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

class CalculatorActivity :
        AppCompatActivity(),
        ButtonsFragment.OnCleanButtonClickListener {
    override fun onPlusButtonClick() {
        setOperation(Operation.ADDITION, R.string.addition_sign)
    }

    override fun onMinusButtonClick() {
        setOperation(Operation.SUBTRACTION, R.string.subtraction_sign)
    }

    override fun onMultipleButtonClick() {
        setOperation(Operation.MULTIPLICATION, R.string.multiplication_sign)
    }

    override fun onDivideButtonClick() {
        setOperation(Operation.DIVISION, R.string.division_sign)
    }

    override fun onResultButtonClick() {
        val transaction = manager.beginTransaction()
        val inputFragment = manager.findFragmentById(R.id.fragment_input) as InputFragment
        val arguments = inputFragment.getParams()

        val aText = arguments?.getString("param1")
        val bText = arguments?.getString("param2")
        val warningToast: Toast
        if (aText == null || aText.isEmpty()) {
            warningToast = Toast.makeText(this, getString(R.string.input_warning) + " first operand!", Toast.LENGTH_SHORT)
            warningToast.show()
            return
        } else if (bText == null || bText.isEmpty()) {
            warningToast = Toast.makeText(this, getString(R.string.input_warning) + " second operand!", Toast.LENGTH_SHORT)
            warningToast.show()
            return
        }

        val a = aText.toFloat()
        val b = bText.toFloat()
        val result = performOperation(a, b)
        val size = if (result.length > 9)
            54 * 9f / result.length
        else
            54f

        val resultFragment = manager.findFragmentById(R.id.fragment_result) as ResultFragment
        resultFragment.setResultValue(result, size)
        transaction.commit()

    }

    private fun performOperation(a: Float, b: Float): String {
       return when (currentOperation) {
            Operation.ADDITION -> (a + b).toString()
            Operation.SUBTRACTION -> (a - b).toString()
            Operation.MULTIPLICATION -> (a * b).toString()
            Operation.DIVISION -> String.format("%.5f", (a / b))

        }
    }

    override fun onCleanButtonClick() {
        val transaction = manager.beginTransaction()
        val resultFragment = manager.findFragmentById(R.id.fragment_result) as ResultFragment
        resultFragment.clean()
        transaction.commit()
    }

    private val manager = supportFragmentManager
    private var currentOperation: Operation = Operation.ADDITION


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        showFragments()
    }

    private fun showFragments() {
        val transaction = manager.beginTransaction()
        val inputFragment = InputFragment()
        transaction.replace(R.id.fragment_input, inputFragment)
        val buttonsFragment = ButtonsFragment()
        transaction.replace(R.id.fragment_buttons, buttonsFragment)
        val resultFragment = ResultFragment()
        transaction.replace(R.id.fragment_result, resultFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun setOperation(operation: Operation, signId: Int) {
        val operationSign = getString(signId)
        currentOperation = operation
        val transaction = manager.beginTransaction()
        val inputFragment = manager.findFragmentById(R.id.fragment_input) as InputFragment
        inputFragment.setOperationSign(operationSign)
        transaction.commit()
    }
}
